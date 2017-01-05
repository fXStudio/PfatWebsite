package cn.fxtech.pfatwebsite.controllers.em;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatitem;
import cn.fxtech.pfatwebsite.models.OSuser;
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
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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
	public Object pfatitemList(EMpfatitem item, HttpServletRequest request) {
		item.setCreateYear((String) request.getSession(false).getAttribute("createYear"));
		List<EMpfatitem> list = empfatitemService.findRecords(item);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Auto sync: " + item.getDeptId());
		log.debug("Total pfatitem count is: " + list.size());

		return map;
	}

	/**
	 * 考核项目列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "deptitemList")
	public Object deptitemList(HttpServletRequest request, @RequestParam(value = "status") String status) {
		OSuser user = (OSuser) request.getSession(false).getAttribute("pfatUser");

		List<EMpfatitem> list = empfatitemService.findRecordsByDept(user.getDeptId(), status,
				(String) request.getSession(false).getAttribute("createYear"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Total pfatitem of Dept count is: " + list.size());

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
	public Object statusModify(EMpfatitem item, HttpServletRequest request) {
		log.debug("Save | Update pfatitem name is: " + item.getItemName());

		item.setCreateYear((String) request.getSession(false).getAttribute("createYear"));

		return empfatitemService.addOrUpdate(item);
	}

	/**
	 * 排序考核项目
	 *
	 * @param sn
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "pfatitemAdjust")
	public Object pfatitemAdjust(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException,
			IllegalAccessException, InvocationTargetException, ParseException {
		List<EMpfatitem> list = new ArrayList<EMpfatitem>();

		for (LinkedHashMap map : (List<LinkedHashMap>) new ObjectMapper().readValue(request.getParameter("items"), List.class)) {
			EMpfatitem em = new EMpfatitem();
			for (Field field : em.getClass().getDeclaredFields()) {
				if ("compDate".equals(field.getName())) {
					BeanUtils.setProperty(em, field.getName(), df.parse((String) map.get(field.getName())));
				} else {
					BeanUtils.setProperty(em, field.getName(), map.get(field.getName()));
				}
			}
			list.add(em);
		}
		return empfatitemService.adjustPfatitem(list);
	}
}
