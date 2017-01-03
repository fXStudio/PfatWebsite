package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatitem;

/**
 * 考核项
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IEMpfatitemService {
	public FeedBackMessage addOrUpdate(EMpfatitem pfatitem);

	public FeedBackMessage del(Integer id);

	public List<EMpfatitem> findRecords(EMpfatitem pfatitem);

	public List<EMpfatitem> findRecordsByDept(Integer deptId, String status);

	public Object adjustPfatitem(List<EMpfatitem> list);
}
