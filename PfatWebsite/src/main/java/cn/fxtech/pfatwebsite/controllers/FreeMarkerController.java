package cn.fxtech.pfatwebsite.controllers;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fxtech.pfatwebsite.models.OSuser;

/**
 * 模板引擎拦截器
 *
 * @author Ajaxfan
 */
@Controller
@RequestMapping(method = RequestMethod.GET)
public class FreeMarkerController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(FreeMarkerController.class);

	/**
	 * 捕获系统页面的跳转操作
	 *
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "{path}")
	public String disp(@ModelAttribute("model") ModelMap model, @PathVariable("path") String path,
			HttpServletRequest request) {
		model.addAttribute("modelName", request.getSession().getAttribute("pfatUser") == null ? "exitSystem" : path);

		return "func";
	}

	/**
	 * 捕获系统页面的跳转操作
	 *
	 * @param request
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "preview")
	public String preview(HttpServletRequest request) {
		return "preview";
	}

	/**
	 * 系统登录
	 *
	 * @return
	 */
	@RequestMapping(value = "systemLogout")
	public String systemLogout(HttpServletRequest request) {
		log.debug("User Logout: " + request.getSession().getAttribute("pfatUser"));
		log.debug("Request On: " + Calendar.getInstance().getTime());

		request.getSession().removeAttribute("pfatUser");

		return "redirect:/";
	}

	/**
	 * 管理系统登录页面处理
	 * 
	 * @param model
	 *            模型对象
	 * @return 要加载模板名称
	 */
	@RequestMapping(value = { "/", "index" })
	public String index() {
		return "index";
	}

	/**
	 * 系统主页处理器
	 * 
	 * @param model
	 *            模型对象
	 * @return 要加载模板名称
	 */
	@RequestMapping(value = "main")
	public String main(@ModelAttribute("model") ModelMap model, HttpServletRequest request) {
		OSuser user = (OSuser) request.getSession().getAttribute("pfatUser");

		if (user == null) {
			log.debug("User no login, Now redirect to index page.");
			return "index";
		}
		log.debug("Current Access Main Page User is: " + user.getUsername());
		model.put("username", user.getUsername());

		return "main";
	}

	/**
	 * 系统描述页
	 * 
	 * @param model
	 *            模型对象
	 * @return 要加载模板名称
	 */
	@RequestMapping(value = "describe")
	public String describe() {
		return "describe";
	}
}
