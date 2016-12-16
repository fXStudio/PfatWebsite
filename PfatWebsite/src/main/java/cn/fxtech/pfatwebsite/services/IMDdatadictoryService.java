package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.models.MDdatadictory;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;

/**
 * @author FXStudio.Ajaxfan
 */
public interface IMDdatadictoryService {
	public Object update(MDdatadictory sdata);

	public List<MDdatadictory> findRecords(ConditionFiled cf);
}
