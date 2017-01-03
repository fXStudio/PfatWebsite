package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.OSmenuMapper;
import cn.fxtech.pfatwebsite.mappers.OSmenuitemMapper;
import cn.fxtech.pfatwebsite.mappers.OSmenumenuitemMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSmenu;
import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.models.OSmenumenuitem;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSmenuService;
import cn.fxtech.pfatwebsite.tree.CheckedNode;
import cn.fxtech.pfatwebsite.tree.inters.INode;
import tk.mybatis.mapper.entity.Example;

/**
 * 菜单管理
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSmenuService implements IOSmenuService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSmenuService.class);
	
	private @Autowired OSmenuMapper systemMenuMapper;
	private @Autowired OSmenumenuitemMapper systemMenuMenuitemMapper;
	private @Autowired OSmenuitemMapper systemMenuItemMapper;

	/**
	 * 添加新的菜单
	 *
	 * @param menu
	 *            菜单对象
	 * @param itemIds
	 *            菜单项Id集合
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage add(OSmenu menu, Integer[] itemIds) {
		systemMenuMapper.insertSelective(menu);
		addRelative(menu.getId(), itemIds);

		return new FeedBackMessage(true);
	}

	/**
	 * 删除菜单
	 *
	 * @param sysid
	 *            菜单ID
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage del(Integer sysid) {
		systemMenuMapper.deleteByPrimaryKey(sysid);
		delRelative(sysid);

		return new FeedBackMessage(true);
	}

	/**
	 * 修改菜单信息
	 *
	 * @param menu
	 *            菜单对象
	 * @param itemIds
	 *            菜单项ID集合
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage update(OSmenu menu, Integer[] itemIds) {
		systemMenuMapper.updateByPrimaryKey(menu);
		delRelative(menu.getId());
		addRelative(menu.getId(), itemIds);

		return new FeedBackMessage(true);
	}

	/**
	 * 展开菜单
	 *
	 * @param sysid
	 * @return
	 */
	@Override
	public List<INode> extratMenus(Integer id) {
		List<INode> list = new ArrayList<INode>();
		Example condition = new Example(OSmenumenuitem.class);
		condition.createCriteria().andEqualTo("menuid", id);
		List<OSmenumenuitem> checkeds = systemMenuMenuitemMapper.selectByExample(condition);

		for (OSmenuitem smi : systemMenuItemMapper.selectAll()) {
			CheckedNode node = new CheckedNode();// 带有Check状态的树节点
			node.setSn(smi.getId());// 节点ID
			node.setText(smi.getItemName());// 节点名称
			node.setChecked(isChecked(smi.getId(), checkeds));// 节点被选中

			list.add(node);
		}
		return list;
	}

	/**
	 * @param cf
	 * @return
	 */
	@Override
	public List<OSmenu> findRecords(ConditionFiled cf) {
		return systemMenuMapper.selectAll();
	}

	// ---------------------------------------------------- 私有方法
	/**
	 * 删除菜单和菜单项对应关系记录
	 *
	 * @param sysid
	 *            菜单主键
	 */
	private void delRelative(Integer sysid) {
		Example condition = new Example(OSmenumenuitem.class);
		condition.createCriteria().andEqualTo("menuid", sysid);
		systemMenuMenuitemMapper.deleteByExample(condition);
		
		log.debug("Delete relation items to menu.");
	}

	/**
	 * 添加关系记录
	 *
	 * @param menuId
	 *            菜单ID
	 * @param itemIds
	 *            菜单项ID集合
	 */
	private void addRelative(Integer menuId, Integer[] itemIds) {
		log.debug("Add items to Menu. Items count: " + itemIds.length);
		
		for (Integer itemId : itemIds) {
			OSmenumenuitem relation = new OSmenumenuitem();
			relation.setMenuid(menuId);
			relation.setItemid(itemId);

			systemMenuMenuitemMapper.insertSelective(relation);
		}
	}

	/**
	 * 节点是否被选中
	 *
	 * @param sysid
	 * @param items
	 * @return
	 */
	private boolean isChecked(Integer sysid, List<OSmenumenuitem> items) {
		for (OSmenumenuitem smmi : items) {
			if (sysid == smmi.getItemid()) {
				return true;
			}
		}
		return false;
	}
}
