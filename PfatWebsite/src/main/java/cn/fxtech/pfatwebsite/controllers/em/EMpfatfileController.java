package cn.fxtech.pfatwebsite.controllers.em;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "pfatfileUpload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public Object pfatfileUpload(EMpfatfile file, HttpServletResponse response) throws JsonProcessingException {
		log.debug("User request add pfatfile.");
		
		return new ObjectMapper().writeValueAsString(empfatfileService.add(file));
	}

	/**
	 * 文件下载
	 * 
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "pfatfileDownload", method = RequestMethod.GET)
	public void pfatfileDownload(Integer id, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");

		log.debug("User request pfatfile download. id: " + id);

		empfatfileService.writeFileToClient(id, response);
	}
}
