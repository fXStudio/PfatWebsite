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
import cn.fxtech.pfatwebsite.models.OSusergroup;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSgroupService;
import cn.fxtech.pfatwebsite.services.IOSuserService;

/**
 * 计重设备异常异常
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class OSusergroupController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSusergroupController.class);

	private @Autowired IOSgroupService systemGroupService;
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
	@RequestMapping(value = "userGroupList")
	public Object userGroupList(ConditionFiled cf) {
		List<OSusergroup> list = systemGroupService.findRecords(cf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Contains usergroup: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 菜单列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "groupUserList")
	public Object groupUserList(ConditionFiled cf) {
		List<OSuser> list = oSuserService.findRecords(cf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Contains user: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 删除用户组
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delUserGroup")
	public FeedBackMessage delUserGroup(@RequestParam(value = "id", required = true) Integer id) {
		return systemGroupService.del(id);
	}

	/**
	 * 添加新的用户组
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "userGroupModify")
	public FeedBackMessage userGroupModify(OSusergroup vo,
			@RequestParam(value = "menus", required = false, defaultValue = "") Integer[] menus) {
		if (vo.getId() == 0) {
			return systemGroupService.add(vo, menus);
		}
		return systemGroupService.update(vo, menus);
	}

	/**
	 * 部门可访问菜单
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "userGroupMenu")
	public Object userGroupMenu(
			@RequestParam(value = "groupId", required = false, defaultValue = "0") Integer groupId) {
		return systemGroupService.getOSmenu(groupId);
	}
}
