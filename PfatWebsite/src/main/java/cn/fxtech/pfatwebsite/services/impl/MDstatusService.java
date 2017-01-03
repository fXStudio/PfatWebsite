package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.MDstatusMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.MDstatus;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDstatusService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 状态
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class MDstatusService implements IMDstatusService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDstatusService.class);

	private @Autowired MDstatusMapper msstatusMapper;

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
		return new FeedBackMessage(msstatusMapper.deleteByPrimaryKey(id) > 0);
	}

	/**
	 * 数据的添加或删除
	 *
	 * @param menu_item
	 * @return
	 */
	@Override
	@Transactional
	public FeedBackMessage addOrUpdate(MDstatus status) {
		Example condition = new Example(MDstatus.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", status.getId());
		criteria.andEqualTo("statusName", status.getStatusName());

		if (msstatusMapper.selectByExample(condition).size() > 0) {
			log.debug("Duplicate status is: " + status.getStatusName());
			return new FeedBackMessage(false, "状态名称重复");
		}

		if (status.getId() == 0) {// ID 为0代表信息数据(因为ID是要在写入到缓存的时候才生成)
			log.debug("Create new status: " + status.getStatusName());
			msstatusMapper.insertSelective(status);
		} else {
			log.debug("Update exists status: " + status.getStatusName());
			msstatusMapper.updateByPrimaryKey(status);
		}
		return new FeedBackMessage(true);
	}

	/**
	 * 所有状态信息
	 * 
	 * @param cf
	 * @return
	 */
	@Override
	public List<MDstatus> findRecords(ConditionFiled cf) {
		return msstatusMapper.selectByExample(null);
	}
}
