package cn.fxtech.pfatwebsite.controllers.em;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
	 * @throws IOException
	 */
	@RequestMapping(value = "preview")
	public Object preview(EMpfatfile pfatfile, HttpServletRequest req) throws IOException {
		log.debug("Request Preview pfatfile. id: " + pfatfile.getId());

		req.getSession(false).setAttribute("outFilePath", empfatfileService.preview(pfatfile.getId()));

		return "{success: true}";
	}

	/**
	 *
	 * @param sn
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "binary", method = RequestMethod.GET)
	public void binary(HttpServletRequest req, HttpServletResponse response) throws IOException {
		String outFilePath = (String) req.getSession().getAttribute("outFilePath");

		log.debug("Binary path: " + outFilePath);

		File file = new File(outFilePath);

		String fileName = new String(file.getName().getBytes("gb2312"), "iso8859-1");

		response.setContentType("application/force-download");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);

		try {
			FileUtils.copyFile(file, response.getOutputStream());
		} catch (Exception e) {
			log.error(e.getMessage());
			response.getOutputStream().write(new byte[0]);
		}
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
	 * @throws IOException
	 */
	@RequestMapping(value = "pfatfileDownload", method = RequestMethod.GET)
	public void pfatfileDownload(Integer id, HttpServletResponse response) throws IOException {
		EMpfatfile pfatfile = empfatfileService.findRecordById(id);

		response.setContentType("application/force-download");
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(pfatfile.getFileName().getBytes("gb2312"), "iso8859-1"));

		try {
			FileUtils.copyFile(new File(pfatfile.getFilePath()), response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
