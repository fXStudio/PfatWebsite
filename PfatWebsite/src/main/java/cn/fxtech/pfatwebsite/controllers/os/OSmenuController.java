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
import cn.fxtech.pfatwebsite.models.OSmenu;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSmenuService;

/**
 * 系统菜单管理
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class OSmenuController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSmenuController.class);

	private @Autowired IOSmenuService oSmenuService;

	/**
	 * 菜单列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "menuList")
	public Object menuList(ConditionFiled cf) {
		List<OSmenu> list = oSmenuService.findRecords(cf);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Menu count: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 删除功能菜单
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delMenu")
	public FeedBackMessage deleteMenu(@RequestParam(value = "sysid", required = true) Integer id) {
		log.debug("User delete menu: " + id);

		return oSmenuService.del(id);
	}

	/**
	 * 添加新的菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "menuModify")
	public FeedBackMessage menuModify(OSmenu vo,
			@RequestParam(value = "items", required = false, defaultValue = "") Integer[] items) {
		if (vo.getId() == 0) {
			log.debug("Create new menu. Contains items: " + items.length);

			return oSmenuService.add(vo, items);
		}
		log.debug("Update Exists menu: " + vo.getId());
		return oSmenuService.update(vo, items);
	}

	/**
	 * 列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "itemList")
	public Object menuItemList(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
		log.debug("Expand Menu: " + id);

		return oSmenuService.extratMenus(id);
	}
}
