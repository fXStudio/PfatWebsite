package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.EMpfatitemMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatitem;
import cn.fxtech.pfatwebsite.services.IEMpfatitemService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 系统菜单项
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class EMpfatitemService implements IEMpfatitemService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMpfatitemService.class);

	private @Autowired EMpfatitemMapper empfatitemMapper;

	/**
	 * 删除考核项
	 *
	 * @param sysid
	 *            系统逐渐
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage del(Integer id) {
		return new FeedBackMessage(empfatitemMapper.deleteByPrimaryKey(id) > 0);
	}

	/**
	 * 数据的添加或删除
	 *
	 * @param menu_item
	 * @return
	 */
	@Override
	@Transactional
	public FeedBackMessage addOrUpdate(EMpfatitem pfatitem) {
		Example condition = new Example(EMpfatitem.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", pfatitem.getId());
		criteria.andEqualTo("cateId", pfatitem.getCateId());
		criteria.andEqualTo("itemName", pfatitem.getItemName());

		if (empfatitemMapper.selectByExample(condition).size() > 0) {
			log.debug("Duplicate pfatItem is: " + pfatitem.getItemName());
			return new FeedBackMessage(false, "状态名称重复");
		}

		if (pfatitem.getId() == 0) {// ID 为0代表信息数据(因为ID是要在写入到缓存的时候才生成)
			log.debug("Create new pfatItem: " + pfatitem.getItemName());
			empfatitemMapper.insertSelective(pfatitem);
		} else {
			log.debug("Update pfatItem status: " + pfatitem.getItemName());
			empfatitemMapper.updateByPrimaryKey(pfatitem);
		}
		return new FeedBackMessage(true);
	}

	/**
	 * 所有的考核项
	 * 
	 * @param cf
	 * @return
	 */
	@Override
	public List<EMpfatitem> findRecords(EMpfatitem pfatitem) {
		Example condition = new Example(EMpfatitem.class);
		Criteria criteria = condition.createCriteria();

		if (pfatitem.getCateId() != null) {// 按分类搜索
			log.debug("Search pfatitems by cateid: " + pfatitem.getCateId());
			criteria.andEqualTo("cateId", pfatitem.getCateId());
		} else if (pfatitem.getDeptId() != null) {// 按部门搜索
			log.debug("Search pfatitems by deptId: " + pfatitem.getDeptId());
			criteria.andEqualTo("deptId", pfatitem.getCateId());
		} else if (pfatitem.getStatusId() != null) {// 安状态搜索
			log.debug("Search pfatitems by statusId: " + pfatitem.getStatusId());
			criteria.andEqualTo("statusId", pfatitem.getCateId());
		}
		return empfatitemMapper.selectByExample(condition);
	}
}
