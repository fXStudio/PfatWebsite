package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.OSgroupMapper;
import cn.fxtech.pfatwebsite.mappers.OSgroupmenuMapper;
import cn.fxtech.pfatwebsite.mappers.OSmenuMapper;
import cn.fxtech.pfatwebsite.mappers.OSmenuitemMapper;
import cn.fxtech.pfatwebsite.mappers.OSmenumenuitemMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSusergroup;
import cn.fxtech.pfatwebsite.models.OSgroupmenu;
import cn.fxtech.pfatwebsite.models.OSmenu;
import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.models.OSmenumenuitem;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSgroupService;
import cn.fxtech.pfatwebsite.tree.CheckedNode;
import cn.fxtech.pfatwebsite.tree.NormalNode;
import cn.fxtech.pfatwebsite.tree.inters.INode;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户组
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSgroupService implements IOSgroupService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSgroupService.class);

	private @Autowired OSgroupMapper systemGroupMapper;
	private @Autowired OSgroupmenuMapper systemGroupMenuMapper;
	private @Autowired OSmenuMapper systemMenuMapper;
	private @Autowired OSmenumenuitemMapper systemMenuMenuitemMapper;
	private @Autowired OSmenuitemMapper systemMenuItemMapper;

	@Override
	@Transactional
	public FeedBackMessage add(OSusergroup group, Integer[] menuIds) {
		systemGroupMapper.insertSelective(group);
		addRelative(group.getId(), menuIds);
		log.debug("Create new Usergroup:" + group.getGroupName());

		return new FeedBackMessage(true);
	}

	@Override
	@Transactional
	public FeedBackMessage del(Integer id) {
		systemGroupMapper.deleteByPrimaryKey(id);
		delRelative(id);
		log.debug("Delete usergroup: " + id);

		return new FeedBackMessage(true);
	}

	@Override
	@Transactional
	public FeedBackMessage update(OSusergroup group, Integer[] menuIds) {
		systemGroupMapper.updateByPrimaryKey(group);
		delRelative(group.getId());
		addRelative(group.getId(), menuIds);
		log.debug("Update usergroup: " + group.getGroupName());

		return new FeedBackMessage(true);
	}

	/**
	 * 系统菜单
	 *
	 * @param groupId
	 * @return
	 */
	@Override
	public List<INode> getOSmenu(Integer groupId) {
		List<INode> tree = new ArrayList<INode>();
		List<OSgroupmenu> groupMenus = getGroupMenus(groupId);

		// 菜单
		for (OSmenu sm : systemMenuMapper.selectAll()) {
			CheckedNode menuNode = new CheckedNode();
			menuNode.setSn(sm.getId());
			menuNode.setText(sm.getMenuName());
			menuNode.setChecked(isChecked(sm.getId(), groupMenus));

			// 菜单项
			for (OSmenumenuitem smmi : getSystemMenuItems(sm.getId())) {
				OSmenuitem smi = systemMenuItemMapper.selectByPrimaryKey(smmi.getItemid());

				NormalNode node = new NormalNode();
				node.setSn(smi.getId());
				node.setText(smi.getItemName());

				menuNode.addChild(node);
			}
			tree.add(menuNode);
		}

		return tree;
	}

	/**
	 * 删除用户组和菜单对应关系记录
	 *
	 * @param sysid
	 *            菜单主键
	 */
	private void delRelative(Integer id) {
		Example condition = new Example(OSgroupmenu.class);
		condition.createCriteria().andEqualTo("usergroupid", id);
		systemGroupMenuMapper.deleteByExample(condition);
	}

	/**
	 * 添加关系记录
	 *
	 * @param menuId
	 *            菜单ID
	 * @param itemIds
	 *            菜单项ID集合
	 */
	private void addRelative(Integer groupId, Integer[] menuIds) {
		for (Integer menuId : menuIds) {
			OSgroupmenu relation = new OSgroupmenu();
			relation.setUsergroupid(groupId);
			relation.setMenuid(menuId);

			systemGroupMenuMapper.insertSelective(relation);
		}
	}

	/**
	 * @param groupId
	 * @return
	 */
	private List<OSgroupmenu> getGroupMenus(Integer groupId) {
		Example condition = new Example(OSgroupmenu.class);
		condition.createCriteria().andEqualTo("usergroupid", groupId);

		return systemGroupMenuMapper.selectByExample(condition);
	}

	/**
	 * @param sysid
	 * @param list
	 * @return
	 */
	private boolean isChecked(Integer id, List<OSgroupmenu> list) {
		for (OSgroupmenu sgm : list) {
			if (id == sgm.getMenuid()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param menuid
	 * @return
	 */
	private List<OSmenumenuitem> getSystemMenuItems(Integer menuid) {
		Example condition = new Example(OSmenumenuitem.class);
		condition.createCriteria().andEqualTo("menuid", menuid);

		return systemMenuMenuitemMapper.selectByExample(condition);
	}

	/**
	 * @param cf
	 * @return
	 */
	@Override
	public List<OSusergroup> findRecords(ConditionFiled cf) {
		return systemGroupMapper.selectByExampleAndRowBounds(null,
				new RowBounds(cf.getStart(), cf.getLimit() - cf.getStart()));
	}
}
