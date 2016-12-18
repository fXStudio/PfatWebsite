package cn.fxtech.pfatwebsite.controllers.em;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatitem;
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
		List list = empfatitemService.findRecords(item);

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
	public Object delStatus(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("Delete pfatitem: " + id);
		return empfatitemService.del(id);
	}

	/**
	 * 修改考核项目
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "pfatitemModify")
	public Object statusModify(EMpfatitem item, HttpServletRequest request) {
		try {
			BufferedReader reader = request.getReader();

			StringBuilder sb = new StringBuilder();
			char[] buff = new char[1024];
			int len = -1;

			while((len = reader.read(buff)) != -1) {
			    sb.append(buff, 0, len);
			}

			reader.close();

			// example
			log.debug( sb.toString() ); // {"name":"zhangsan", "age": 28}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("User Request SaveOrUpdate pfatitem: " + item.getId());
		
		return new FeedBackMessage(true);

//		return empfatitemService.addOrUpdate(item);
	}
}
