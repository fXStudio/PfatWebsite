package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.models.OSusergroup;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;

/**
 * 系统用户
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSuserService {
	/**
	 * @param user
	 * @return
	 */
	public FeedBackMessage addOrUpdate(OSuser user);

	/**
	 * 待选用户组
	 * 
	 * @param userID
	 * @return
	 */
	public List<OSusergroup> getUserGroups(Integer id);

	public FeedBackMessage del(Integer sysid);

	public List<OSuser> findRecords(ConditionFiled cf);
}
