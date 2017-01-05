package cn.fxtech.pfatwebsite.controllers.st;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.fxtech.pfatwebsite.helper.excel.inters.IExcelHelper;
import cn.fxtech.pfatwebsite.models.STpfatdata;
import cn.fxtech.pfatwebsite.services.ISTpfatdataService;

/**
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = { RequestMethod.POST })
public class STpfatdataController {
	private Logger log = Logger.getLogger(STpfatdataController.class);

	private @Autowired ISTpfatdataService stpfatdataservice;
	private @Autowired IExcelHelper excelHelper;

	/**
	 * 总表信息
	 *
	 * @return
	 */
	@RequestMapping(value = "stpfatdataList")
	public Object stpfatdataList(HttpServletRequest request) {
		List<STpfatdata> list = stpfatdataservice.findAll((String)request.getSession(false).getAttribute("createYear"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("STpfatdata count is: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 文件下载
	 * 
	 * @param sn
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "statisExport", method = RequestMethod.GET)
	public void statisExport(HttpServletRequest request, HttpServletResponse response) throws JsonParseException, JsonMappingException,
			IOException, IllegalAccessException, InvocationTargetException, ParseException {
		log.debug("User Request Export Statis Data.");
		log.debug("Begin Export Statis Data.");
		
		response.reset();
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-disposition", "attachment;filename=statistics.xls");

		excelHelper.export2Stream((List<LinkedHashMap>) new ObjectMapper().readValue(request.getParameter("items"), List.class),
				response.getOutputStream());

		log.debug("End Export Statis Data.");
	}
}
