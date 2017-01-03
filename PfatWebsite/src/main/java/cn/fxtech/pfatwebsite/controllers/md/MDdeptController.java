package cn.fxtech.pfatwebsite.controllers.md;

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

import cn.fxtech.pfatwebsite.models.MDdept;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDdeptService;

/**
 * 部门管理
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = { RequestMethod.POST })
public class MDdeptController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDdeptController.class);

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
		return mddeptService.findByDeptid(((OSuser) request.getSession().getAttribute("pfatUser")).getDeptId());
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
	
	/**
	 * 机构部门
	 *
	 * @param limit
	 *            开始索引
	 * @param start
	 *            结束索引
	 * @return
	 */
	@RequestMapping(value = "combDeptList")
	public Object combDeptList() {
		return mddeptService.findAll(new ConditionFiled());
	}

	/**
	 * 列出所有的部门信息
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "mdDeptList")
	public Object mdDeptList(ConditionFiled cf) {
		List<MDdept> list = mddeptService.findAll(cf);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Has dept items: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 删除部门信息
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delMdDept")
	public Object delMdDept(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("Delete Dept: " + id);

		return mddeptService.del(id);
	}

	/**
	 * 修改部门信息
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "mdDeptModify")
	public Object mdDeptModify(MDdept dept) {
		return mddeptService.addOrUpdate(dept);
	}
}
