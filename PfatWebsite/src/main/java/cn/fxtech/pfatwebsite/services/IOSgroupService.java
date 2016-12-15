package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSusergroup;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 系统用户组
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSgroupService {
	/**
	 * 添加用户组
	 * 
	 * @param menuItem
	 * @return
	 */
	public FeedBackMessage add(OSusergroup group, Integer[] menuIds);

	/**
	 * 修改用户组
	 * 
	 * @param menuItem
	 * @return
	 */
	public FeedBackMessage update(OSusergroup group, Integer[] menuIds);

	/**
	 * 删除用户组
	 * 
	 * @param id
	 * @return
	 */
	public FeedBackMessage del(Integer id);

	public List<OSusergroup> findRecords(ConditionFiled cf);

	/**
	 * 系统菜单
	 * 
	 * @param groupId
	 * @return
	 */
	public List<INode> getOSmenu(Integer id);
}
