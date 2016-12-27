package cn.fxtech.pfatwebsite.controllers.st;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.models.STpfatdata;
import cn.fxtech.pfatwebsite.services.ISTpfatdataService;

/**
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = { RequestMethod.POST })
public class STpfatdataController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(STpfatdataController.class);

	private @Autowired ISTpfatdataService stpfatdataservice;

	/**
	 * 总表信息
	 *
	 * @return
	 */
	@RequestMapping(value = "stpfatdataList")
	public Object stpfatdataList(HttpServletRequest request) {
		List<STpfatdata> list = stpfatdataservice.findAll();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("STpfatdata count is: " + map.get("totalCount"));

		return map;

	}
}
