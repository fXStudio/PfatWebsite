package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSmenu;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 系统菜单维护
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSmenuService {
	public List<OSmenu> findRecords(ConditionFiled cf);

	public FeedBackMessage del(Integer sysid);

	/**
	 * 添加新的菜单及维护与菜单项的关系
	 * 
	 * @param menu
	 * @return
	 */
	public FeedBackMessage add(OSmenu menu, Integer[] itemIds);

	/**
	 * 更新菜单
	 * 
	 * @param menuComplex
	 * @return
	 */
	public FeedBackMessage update(OSmenu menu, Integer[] itemIds);

	/**
	 * 展开菜单
	 * 
	 * @param sysid
	 * @return
	 */
	public List<INode> extratMenus(Integer id);
}
