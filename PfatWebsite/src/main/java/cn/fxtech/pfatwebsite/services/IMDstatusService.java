package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.MDstatus;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;

/**
 * 状态
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IMDstatusService {
	public FeedBackMessage addOrUpdate(MDstatus menu_item);

	public FeedBackMessage del(Integer id);

	public List<MDstatus> findRecords(ConditionFiled cf);
}
