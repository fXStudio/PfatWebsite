package cn.fxtech.pfatwebsite.controllers.md;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.fxtech.pfatwebsite.models.MDdatadictory;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDdatadictoryService;

/**
 * 计重设备异常异常
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class MDdatadictoryController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDdatadictoryController.class);

	private @Autowired IMDdatadictoryService mddatadictoryService;

	/**
	 * 字典列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 */
	@RequestMapping(value = "systemDataList")
	public Object systemDataList(ConditionFiled cf) {
		List<MDdatadictory> list = mddatadictoryService.findRecords(cf);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", list.size());// 记录总数
		map.put("items", list);// 记录行对象

		log.debug("Data dictionary items: " + map.get("totalCount"));

		return map;
	}

	/**
	 * 修改菜单项
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "systemDataModify")
	public Object systemDataModify(MDdatadictory sdata) {
		return mddatadictoryService.update(sdata);
	}
}
