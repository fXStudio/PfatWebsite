package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;

/**
 * 系统菜单项管理
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSmenuitemService {

	/**
	 * 数据新增或修改
	 * 
	 * @param menu_item
	 *            数据对象
	 * @return 处理的结果
	 */
	public FeedBackMessage addOrUpdate(OSmenuitem menu_item);
	public FeedBackMessage del(Integer sysid);
	public List<OSmenuitem> findRecords(ConditionFiled cf) ;
}
