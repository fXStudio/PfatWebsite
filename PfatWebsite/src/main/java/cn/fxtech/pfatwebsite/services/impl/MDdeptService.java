package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fxtech.pfatwebsite.mappers.MDdeptMapper;
import cn.fxtech.pfatwebsite.mappers.OSuserMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.MDdept;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDdeptService;
import cn.fxtech.pfatwebsite.tree.CheckedNode;
import cn.fxtech.pfatwebsite.tree.NormalNode;
import cn.fxtech.pfatwebsite.tree.inters.INode;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 部门信息
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class MDdeptService implements IMDdeptService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDdeptService.class);

	private @Autowired MDdeptMapper mddeptMapper;
	private @Autowired OSuserMapper osuserMapper;

	/**
	 * 部门信息
	 * 
	 * @param fields
	 * @return
	 */
	@Override
	public List<INode> findByDeptid(Integer deptid) {
		List<INode> list = new ArrayList<INode>();
		Example condition = new Example(MDdept.class);
		condition.createCriteria().andEqualTo("id", deptid);

		for (MDdept obj : mddeptMapper.selectByExample(condition)) {
			NormalNode node = new NormalNode();
			node.setSn(obj.getId());
			node.setText(obj.getDeptName());

			list.add(deep(node, obj.getId()));
		}
		log.debug("Find All subdepts by id: " + deptid);
		log.debug("Sub dept count: " + list.size());

		return list;
	}

	/**
	 * 生成部门树
	 * 
	 * @return
	 */
	@Override
	public List<INode> deptTree(Integer userId) {
		List<INode> list = new ArrayList<INode>();
		Example condition = new Example(MDdept.class);
		condition.createCriteria().andEqualTo("id", 1);

		// 部门编号
		Integer depid = getDepIdFromUser(userId);

		for (MDdept obj : mddeptMapper.selectByExample(condition)) {
			CheckedNode node = new CheckedNode();
			node.setSn(obj.getId());
			node.setText(obj.getDeptName());
			node.setChecked(obj.getId().equals(depid));

			list.add(deep(node, obj.getId(), depid));
		}
		log.debug("Find All dept. dept count: " + list.size());

		return list;
	}

	/**
	 * 展开部门树
	 * 
	 * @param node
	 * @param parent
	 */
	private INode deep(CheckedNode parentNode, Integer parent, Integer depId) {
		Example condition = new Example(MDdept.class);
		condition.createCriteria().andEqualTo("parentId", parent);

		for (MDdept obj : mddeptMapper.selectByExample(condition)) { // 子节点
			CheckedNode node = new CheckedNode();
			node.setSn(obj.getId());
			node.setText(obj.getDeptName());
			node.setChecked(parentNode.isChecked() ? true : node.getSn().equals(depId));

			parentNode.addChild(deep(node, obj.getId(), depId));
		}
		return parentNode;
	}

	/**
	 * 展开部门树
	 * 
	 * @param node
	 * @param parent
	 */
	private INode deep(INode parentNode, Integer parent) {
		Example condition = new Example(MDdept.class);
		condition.createCriteria().andEqualTo("parentId", parent);

		for (MDdept obj : mddeptMapper.selectByExample(condition)) {// 子节点
			NormalNode node = new NormalNode();
			node.setSn(obj.getId());
			node.setText(obj.getDeptName());

			parentNode.addChild(deep(node, obj.getId()));
		}
		return parentNode;
	}

	/**
	 * 用户部门编号
	 * 
	 * @param userId
	 * @return
	 */
	private Integer getDepIdFromUser(Integer userId) {
		OSuser user = osuserMapper.selectByPrimaryKey(userId);
		if (user == null) {
			return null;
		}
		return user.getDeptId();
	}

	/**
	 * 查询所有的部门信息
	 */
	@Override
	public List<MDdept> findAll(ConditionFiled cf) {
		Example condition = new Example(MDdept.class);
		condition.createCriteria().andNotEqualTo("id", 1);

		return mddeptMapper.selectByExampleAndRowBounds(condition, new RowBounds(cf.getStart(), cf.getLimit() - cf.getStart()));
	}

	/**
	 * 删除记录
	 */
	@Override
	public FeedBackMessage del(Integer id) {
		return new FeedBackMessage(mddeptMapper.deleteByPrimaryKey(id) > 0);
	}

	/**
	 * 添加或删除部门信息
	 */
	@Override
	public FeedBackMessage addOrUpdate(MDdept dept) {
		Example condition = new Example(MDdept.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", dept.getId());
		criteria.andEqualTo("deptName", dept.getDeptName());

		if (mddeptMapper.selectByExample(condition).size() > 0) {
			log.debug("Dept name duplicate: " + dept.getDeptName());
			return new FeedBackMessage(false, "部门名称重复");
		}

		if (dept.getId() == 0) {
			log.debug("Create new dept name is: " + dept.getDeptName());
			return new FeedBackMessage(mddeptMapper.insert(dept) > 1);
		}
		log.debug("update dept name is: " + dept.getDeptName());
		return new FeedBackMessage(mddeptMapper.updateByPrimaryKey(dept) > 1);
	}
}
