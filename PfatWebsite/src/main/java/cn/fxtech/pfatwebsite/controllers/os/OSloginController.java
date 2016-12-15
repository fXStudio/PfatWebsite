package cn.fxtech.pfatwebsite.controllers.os;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSuserService;

/**
 * 计重设备异常异常
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class OSloginController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSloginController.class);

	/** 用户服务接口类 */
	private @Autowired IOSuserService oSuserService;

	/**
	 * @return 系统登录
	 */
	@RequestMapping("systemLogin")
	public Object systemLogin(HttpServletRequest request, ConditionFiled cf) {
		List<OSuser> users = oSuserService.findRecords(cf);
		log.debug("Login User: " + cf.getUsername());
		log.debug("Login Pass: " + cf.getPassword());
		log.debug("Total Matched Users: " + users.size());

		if (users.size() == 0) {
			return new FeedBackMessage(false, "用户名或密码不正确");
		}
		log.debug("Put Login User Information to Session.");
		log.debug("Request On: " + Calendar.getInstance().getTime());
		request.getSession().setAttribute("pfatUser", users.get(0));

		return new FeedBackMessage(true);
	}
}
