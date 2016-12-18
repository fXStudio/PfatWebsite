package cn.fxtech.pfatwebsite.controllers.md;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;

import cn.fxtech.pfatwebsite.models.MDstatus;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDstatusService;

/**
 * 状态信息维护
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class MDstatusController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDstatusController.class);

	private @Autowired IMDstatusService mdstatusService;

	/**
	 * 状态列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "statusList")
	public Object statusList(ConditionFiled cf) {
		PageInfo<MDstatus> pageInfo = new PageInfo<MDstatus>(mdstatusService.findRecords(cf));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", pageInfo.getTotal());// 记录总数
		map.put("items", pageInfo.getList());// 记录行对象

		log.debug("Total Status count is: " + pageInfo.getTotal());

		return map;
	}

	/**
	 * 删除功能菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delStatus")
	public Object delStatus(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("Delete Status: " + id);
		return mdstatusService.del(id);
	}

	/**
	 * 修改菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "statusModify")
	public Object statusModify(MDstatus status) {
		log.debug("User Request SaveOrUpdate status: " + status.getId());
		log.debug("Status is privilege: " + status.getsPrivilege());
		
		return mdstatusService.addOrUpdate(status);
	}
}
