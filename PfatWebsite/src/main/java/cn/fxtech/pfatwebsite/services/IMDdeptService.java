package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 部门信息统计
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IMDdeptService {
	/**
	 * @return
	 */
	public List<INode> findAll(Integer deptid);

	/**
	 * @return
	 */
	public List<INode> deptTree(Integer userid);
}
