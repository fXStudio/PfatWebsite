package cn.fxtech.pfatwebsite.controllers.tree;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.services.IOSmenutreeService;

/**
 * 系统所有树菜单的控制器
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class TreeController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(TreeController.class);

	private @Autowired IOSmenutreeService osmenutreeService;

	/**
	 * 楼栋列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "systemMenuList")
	public Object systemMenuList(HttpServletRequest request) {
		OSuser userVo = (OSuser) request.getSession().getAttribute("pfatUser");

		if (userVo == null) {
			log.debug("Current Login User invalid. Please contact admin.");
			return null;
		}
		log.debug("Loaded current login user's menu list.");
		return osmenutreeService.getOSmenu(userVo.getGroupId());
	}
}
