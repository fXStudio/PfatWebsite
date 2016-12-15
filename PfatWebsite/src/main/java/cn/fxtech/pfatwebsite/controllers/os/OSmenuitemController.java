package cn.fxtech.pfatwebsite.controllers.os;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSmenuitemService;

/**
 * 计重设备异常异常
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class OSmenuitemController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSmenuitemController.class);

	private @Autowired IOSmenuitemService systemMenuItemService;

	/**
	 * 菜单项列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "menuItemList")
	public Object menuItemList(ConditionFiled cf) {
		PageInfo<OSmenuitem> pageInfo = new PageInfo<OSmenuitem>(systemMenuItemService.findRecords(cf));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", pageInfo.getTotal());// 记录总数
		map.put("items", pageInfo.getList());// 记录行对象

		log.debug("Total Menuitem count is: " + pageInfo.getTotal());

		return map;
	}

	/**
	 * 删除功能菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delMenuItem")
	public Object delMenuItem(@RequestParam(value = "sysid", required = true) Integer id) {
		log.debug("Delete Menuitem: " + id);
		return systemMenuItemService.del(id);
	}

	/**
	 * 修改菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "menuItemModify")
	public Object menuItemModify(OSmenuitem menuitem) {
		log.debug("User Request SaveOrUpdate Menuitem: " + menuitem.getId());
		log.debug("Current menuitem islock: " + menuitem.getIslock());
		
		return systemMenuItemService.addOrUpdate(menuitem);
	}
}
