package cn.fxtech.pfatwebsite.controllers.em;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.fxtech.pfatwebsite.models.EMpfatfile;
import cn.fxtech.pfatwebsite.services.IEMpfatfileService;

/**
 * 考核文件
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class EMpfatfileController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMpfatfileController.class);

	private @Autowired IEMpfatfileService empfatfileService;

	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "pfatfileList")
	public Object pfatfileList(EMpfatfile file) throws JsonProcessingException {
		List<EMpfatfile> list = empfatfileService.findRecords(file);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("User request seach pfatfile.");

		return map;
	}

	/**
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delPfatfile")
	public Object delPfatfile(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("User request delete pfatfile.");
		return empfatfileService.del(id);
	}

	/**
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "pfatfileUpload")
	public Object EMpfatfile(EMpfatfile file) {
		log.debug("User request add pfatfile.");

		return empfatfileService.add(file);
	}
}
