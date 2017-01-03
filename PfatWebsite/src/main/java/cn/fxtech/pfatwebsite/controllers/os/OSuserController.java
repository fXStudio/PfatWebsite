package cn.fxtech.pfatwebsite.controllers.os;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSuserService;

/**
 * 维护系统用户服务接口
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class OSuserController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSusergroupController.class);
	
	private @Autowired IOSuserService oSuserService;

	/**
	 * 菜单列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "systemUserList")
	public Object systemUserList(ConditionFiled cf) {
		List<OSuser> pageInfo = oSuserService.findRecords(cf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", pageInfo.size());// 记录总数
		map.put("items", pageInfo);// 记录行对象
		
		log.debug("Contains user: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 用户组列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "delSystemUser")
	public Object delSystemUser(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("Delete User: " + id);
		
		return oSuserService.del(id);
	}

	/**
	 * 维护系统用户
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "systemUserModify")
	public FeedBackMessage systemUserModify(OSuser vo) {
		log.debug("User SaveOrModify: " + vo.getUsername());
		
		return oSuserService.addOrUpdate(vo);
	}

	/**
	 * 用户组列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "userGroupArray", method={RequestMethod.GET, RequestMethod.POST})
	public Object userGroupArray(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("items", oSuserService.getUserGroups(userId));// 记录行对象
		
		return map;
	}
}
