package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fxtech.pfatwebsite.mappers.system.OSgroupmenuMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSmenuMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSmenuitemMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSmenumenuitemMapper;
import cn.fxtech.pfatwebsite.models.OSgroupmenu;
import cn.fxtech.pfatwebsite.models.OSmenu;
import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.models.OSmenumenuitem;
import cn.fxtech.pfatwebsite.services.IOSmenutreeService;
import cn.fxtech.pfatwebsite.tree.NormalNode;
import cn.fxtech.pfatwebsite.tree.inters.INode;
import tk.mybatis.mapper.entity.Example;

/**
 * 系统功能树
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSmenutreeService implements IOSmenutreeService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSmenutreeService.class);
	
	private @Autowired OSgroupmenuMapper systemGroupMenuMapper;
	private @Autowired OSmenuMapper systemMenuMapper;
	private @Autowired OSmenumenuitemMapper systemMenuMenuitemMapper;
	private @Autowired OSmenuitemMapper systemMenuItemMapper;

	/**
	 * 生成系统功能树结构
	 *
	 * @param groupid
	 *            用户组ID
	 * @return
	 */
	@Override
	public List<INode> getOSmenu(Integer groupid) {
		List<INode> list = new ArrayList<INode>();
		log.debug("Search Menu&Menuitem By Usergroupid: " + groupid);
		
		for (OSgroupmenu dm : getDeptMenus(groupid)) {
			OSmenu sm = systemMenuMapper.selectByPrimaryKey(dm.getMenuid());// 菜单对象
			NormalNode menuNode = new NormalNode();// 生成节点对象
			menuNode.setText(sm.getMenuName());// 设置节点名称

			for (OSmenumenuitem smmi : getSystemMenuItems(sm.getId())) {// 生成菜单项
				OSmenuitem smi = systemMenuItemMapper.selectByPrimaryKey(smmi.getItemid());
				NormalNode node = new NormalNode();
				node.setSn(smi.getId());
				node.setText(smi.getItemName());
				node.setUrl(smi.getItemPath());

				menuNode.addChild(node);
			}
			list.add(menuNode);
		}
		return list;
	}

	/**
	 * 部门 <-> 系统功能菜单对应关系
	 *
	 * @param depid
	 * @return
	 */
	private List<OSgroupmenu> getDeptMenus(Integer groupid) {
		Example condition = new Example(OSgroupmenu.class);
		condition.createCriteria().andEqualTo("usergroupid", groupid);

		return systemGroupMenuMapper.selectByExample(condition);
	}

	/**
	 * 菜单，菜单项对应关系
	 *
	 * @param depid
	 * @return
	 */
	private List<OSmenumenuitem> getSystemMenuItems(Integer menuid) {
		Example condition = new Example(OSmenumenuitem.class);
		condition.createCriteria().andEqualTo("menuid", menuid);

		return systemMenuMenuitemMapper.selectByExample(condition);
	}
}
