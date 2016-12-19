package cn.fxtech.pfatwebsite.controllers.em;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatitem;
import cn.fxtech.pfatwebsite.protocal.PfatFile;
import cn.fxtech.pfatwebsite.services.IEMpfatitemService;

/**
 * 考核项目维护
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class EMpfatitemController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMpfatitemController.class);

	private @Autowired IEMpfatitemService empfatitemService;

	/**
	 * 考核项目列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "pfatitemList")
	public Object pfatitemList(EMpfatitem item) {
		List<EMpfatitem> list = empfatitemService.findRecords(item);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Auto sync: " + item.getDeptId());
		log.debug("Total pfatitem count is: " + list.size());

		return map;
	}

	/**
	 * 删除考核项目
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delPfatitem")
	public Object delStatus(HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			EMpfatitem item = mapper.readValue(request.getReader(), EMpfatitem.class);

			log.debug("Delete pfatitem name is: " + item.getItemName());

			return empfatitemService.del(item.getId());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new FeedBackMessage(false, "系统异常，请联系管理员");
	}

	/**
	 * 修改考核项目
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "pfatitemModify")
	public Object statusModify(EMpfatitem item) {
		log.debug("Save | Update pfatitem name is: " + item.getItemName());
		
		return empfatitemService.addOrUpdate(item);
	}
	
	/**
	 * 文件上传
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "pfatItemFileUpload")
	public Object pfatItemFileUpload(PfatFile file) {
		log.debug("File Upload name: " + file.getFileName());
		log.debug("File Upload obj: " + file.getPfatFile());

        try {
			FileUtils.copyInputStreamToFile(file.getPfatFile().getInputStream(), new File("d:/mes/", file.getPfatFile().getOriginalFilename()));
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return new FeedBackMessage(true);
	}
}
