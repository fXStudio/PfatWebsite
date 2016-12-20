package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private @Autowired EMpfatfileMapper empfatfileMapper;

	@Override
	@Transactional
	public FeedBackMessage add(EMpfatfile file) {
		try {
			file.setFileName(file.getFileStream().getOriginalFilename());
			
			if(empfatfileMapper.isExists(file) > 0){
				return new FeedBackMessage(false, "文件名称重复");
			}
			file.setFilePath("/fx/mes/");

			log.debug("Add pfatfile name: " + file.getFileName());
			log.debug("Add file cate: " + file.getCate());

			return new FeedBackMessage(empfatfileMapper.insert(file) > 0);
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
		log.debug("Search pfatfile by id: " + id);
		
		return empfatfileMapper.findRecordById(id);
	}
}
