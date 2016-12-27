package cn.fxtech.pfatwebsite.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.helper.Office2Swf;
import cn.fxtech.pfatwebsite.mappers.EMpfatfileMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatfile;
import cn.fxtech.pfatwebsite.services.IEMpfatfileService;

/**
 * 考核文件
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class EMpfatfileService implements IEMpfatfileService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMpfatfileService.class);

	/** 服务器文件存放地址 */
	private @Value("${server_file_dir}") String SERVER_FILE_DIRECTORY;

	/** 文件地址分隔符 */
	private static final String PATH_SEPARATOR = "/";

	private @Autowired EMpfatfileMapper empfatfileMapper;
	private @Autowired Office2Swf office2swf;

	@Override
	@Transactional
	public FeedBackMessage add(EMpfatfile file) {
		try {
			file.setFileName(file.getFileStream().getOriginalFilename());

			if (empfatfileMapper.isExists(file) > 0) {
				return new FeedBackMessage(false, "文件名称重复");
			}
			file.setFilePath(PATH_SEPARATOR + file.getPfatitemId());

			FileUtils.copyInputStreamToFile(file.getFileStream().getInputStream(),
					new File(SERVER_FILE_DIRECTORY + file.getFilePath() + PATH_SEPARATOR + file.getFileName()));

			log.debug("Add pfatfile name: " + file.getFileName());
			log.debug("Add file cate: " + file.getCate());
			log.debug("Write file to server directory: " + SERVER_FILE_DIRECTORY + file.getFilePath());

			return new FeedBackMessage(empfatfileMapper.insert(file) > 0, "文件保存失败，请重新尝试文件上传.如仍有问题可联系管理员.");
		} catch (Exception e) {
			log.error(e.getMessage());
			return new FeedBackMessage(false, e.getMessage());
		}
	}

	@Override
	@Transactional
	public FeedBackMessage del(Integer id) {
		log.debug("Delete pfatfile by id: " + id);
		return new FeedBackMessage(empfatfileMapper.del(id) > 0);
	}

	@Override
	public List<EMpfatfile> findRecords(EMpfatfile file) {
		log.debug("Query pfatfile condition[pfatitemId]: " + file.getPfatitemId());
		log.debug("Query pfatfile condition[cate]: " + file.getCate());

		return empfatfileMapper.findRecords(file);
	}

	@Override
	public EMpfatfile findRecordById(Integer id) {
		EMpfatfile obj = empfatfileMapper.findRecordById(id);
		obj.setFilePath(
				SERVER_FILE_DIRECTORY + PATH_SEPARATOR + obj.getFilePath() + PATH_SEPARATOR + obj.getFileName());

		log.debug("Download file name: " + obj.getFileName());
		log.debug("Download file server path: " + obj.getFilePath());

		return obj;
	}

	/**
	 * 生成预览图
	 */
	@Override
	public String preview(Integer id) throws IOException {
		EMpfatfile pfatfile = findRecordById(id);

		String inputFilePath = pfatfile.getFilePath();
		String outFilePath = inputFilePath.replace(new File(inputFilePath).getName(),
				System.currentTimeMillis() + ".swf");
		
		return office2swf.office2Swf(inputFilePath, outFilePath);
	}
}
