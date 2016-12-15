package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fxtech.pfatwebsite.mappers.system.MDdeptMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSuserMapper;
import cn.fxtech.pfatwebsite.models.MDdept;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.services.IMDdeptService;
import cn.fxtech.pfatwebsite.tree.CheckedNode;
import cn.fxtech.pfatwebsite.tree.NormalNode;
import cn.fxtech.pfatwebsite.tree.inters.INode;
import tk.mybatis.mapper.entity.Example;

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
	public List<INode> findAll(Integer deptid) {
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
		condition.createCriteria().andEqualTo("deptType", 1);

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
}
