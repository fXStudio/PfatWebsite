package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.MDdept;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 部门信息统计
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IMDdeptService {
	public List<INode> findByDeptid(Integer deptid);

	public List<INode> deptTree(Integer userid);

	public List<MDdept> findAll(ConditionFiled cf);

	public FeedBackMessage addOrUpdate(MDdept dept);

	public FeedBackMessage del(Integer id);
}
