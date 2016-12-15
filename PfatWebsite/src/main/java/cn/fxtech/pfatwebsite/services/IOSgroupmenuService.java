package cn.fxtech.pfatwebsite.services;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;

/**
 * 维护部门
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSgroupmenuService {
	/**
	 * 变更部门菜单关系
	 * 
	 * @param sysid
	 * @param menuIds
	 * @return
	 */
	public FeedBackMessage update(Integer id, Integer[] menuIds);
}
