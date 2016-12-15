package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 系统导航树服务接口
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IOSmenutreeService {

	/**
	 * 生成系统功能树结构
	 * 
	 * @param depid 用户组ID
	 * @return
	 */
	public List<INode> getOSmenu(Integer id);
}
