package cn.fxtech.pfatwebsite.controllers.os;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.services.IMDdeptService;

/**
 * 部门管理
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = { RequestMethod.POST })
public class MDdeptController {
	private @Autowired IMDdeptService mddeptService;

	/**
	 * 部门列表
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "fsOrgDeptList")
	public Object fsOrgDeptList(HttpServletRequest request) {
		return mddeptService.findAll(((OSuser) request.getSession().getAttribute("pfatUser")).getDeptId());
	}

	/**
	 * 机构部门
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "departmentList")
	public Object departmentList(@RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId) {
		return mddeptService.deptTree(userId);
	}
}
