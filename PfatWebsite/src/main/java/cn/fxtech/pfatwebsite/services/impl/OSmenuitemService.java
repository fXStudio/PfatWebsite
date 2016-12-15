package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.system.OSmenuitemMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSmenumenuitemMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSmenuitem;
import cn.fxtech.pfatwebsite.models.OSmenumenuitem;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSmenuitemService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 系统菜单项
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSmenuitemService implements IOSmenuitemService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSmenuitemService.class);

	private @Autowired OSmenuitemMapper systemMenuItemMapper;
	private @Autowired OSmenumenuitemMapper systemMenuMenuitemMapper;

	/**
	 * 通过主键来删除数据
	 *
	 * @param sysid
	 *            系统逐渐
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage del(Integer id) {
		if (relationExists(id)) {// 删除前需要判断菜单项是否被关联
			return new FeedBackMessage(false, "菜单项被引用，不能删除!");
		}

		if (systemMenuItemMapper.deleteByPrimaryKey(id) > 0) {// 删除菜单项
			return new FeedBackMessage(true);
		}
		return new FeedBackMessage(false, "没有找到要删除的数据!");
	}

	/**
	 * 数据的添加或删除
	 *
	 * @param menu_item
	 * @return
	 */
	@Override
	@Transactional
	public FeedBackMessage addOrUpdate(OSmenuitem menuitem) {
		if (itemDuplicate(menuitem)) {
			log.debug("Duplicate menuitem is: " + menuitem.getItemName());
			return new FeedBackMessage(false, "菜单名重复");
		}

		if (menuitem.getId() == 0) {// ID 为0代表信息数据(因为ID是要在写入到缓存的时候才生成)
			log.debug("Create new menuitem: " + menuitem.getItemName());
			systemMenuItemMapper.insertSelective(menuitem);
		} else {
			log.debug("Update exists menuitem: " + menuitem.getItemName());
			systemMenuItemMapper.updateByPrimaryKey(menuitem);
		}
		return new FeedBackMessage(true);
	}

	/**
	 * 所有的菜单项记录
	 * 
	 * @param cf
	 * @return
	 */
	@Override
	public List<OSmenuitem> findRecords(ConditionFiled cf) {
		return systemMenuItemMapper.selectByExampleAndRowBounds(null, new RowBounds(cf.getStart(), cf.getLimit() - cf.getStart()));
	}

	// -------------------------------------------------- Private Methond
	/**
	 * 是否存在关联
	 * 
	 * @param sysid
	 * @return
	 */
	private boolean relationExists(Integer id) {
		Example condition = new Example(OSmenumenuitem.class);
		condition.createCriteria().andEqualTo("itemid", id);

		// 判断菜单项是否存在关联
		return systemMenuMenuitemMapper.selectByExample(condition).size() > 0;
	}

	/**
	 * 是否存在关联
	 * 
	 * @param sysid
	 * @return
	 */
	private boolean itemDuplicate(OSmenuitem menu_item) {
		Example condition = new Example(OSmenuitem.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", menu_item.getId());
		criteria.andEqualTo("itemName", menu_item.getItemName());

		// 判断菜单项是否存在关联
		return systemMenuItemMapper.selectByExample(condition).size() > 0;
	}
}
