package cn.fxtech.pfatwebsite.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.OSgroupmenuMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSgroupmenu;
import cn.fxtech.pfatwebsite.services.IOSgroupmenuService;
import tk.mybatis.mapper.entity.Example;

/**
 * 维护部门
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSgroupmenuService implements IOSgroupmenuService {
	private @Autowired OSgroupmenuMapper deptMenuMapper;

	/**
	 * 变更部门菜单关系
	 *
	 * @param sysid
	 * @param menuIds
	 * @return
	 */
	@Override
	@Transactional
	public FeedBackMessage update(Integer id, Integer[] menuIds) {
		// 删除以前维护的关系记录
		Example condition = new Example(OSgroupmenu.class);
		condition.createCriteria().andEqualTo("groupid", id);
		deptMenuMapper.deleteByExample(condition);

		// 部门在系统中可视为等同于【用户组】，这里就是用来维护不能对于系统的可操作权限
		for (Integer menuId : menuIds) {
			OSgroupmenu deptMenu = new OSgroupmenu();
			deptMenu.setUsergroupid(id);
			deptMenu.setMenuid(menuId);

			deptMenuMapper.insertSelective(deptMenu);
		}
		return new FeedBackMessage(true);
	}
}
