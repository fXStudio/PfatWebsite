package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.helper.StringHelper;
import cn.fxtech.pfatwebsite.mappers.system.OSgroupMapper;
import cn.fxtech.pfatwebsite.mappers.system.OSuserMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.OSuser;
import cn.fxtech.pfatwebsite.models.OSusergroup;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IOSuserService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 系统用户
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class OSuserService implements IOSuserService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(OSuserService.class);

	private @Autowired OSuserMapper systemUserMapper;
	private @Autowired OSgroupMapper systemGroupMapper;

	/**
	 * @param user
	 * @return
	 */
	@Override
	public FeedBackMessage addOrUpdate(OSuser user) {
		Example condition = new Example(OSuser.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", user.getId());
		criteria.andEqualTo("username", user.getUsername());
		
		log.debug("Check duplicate user id is: " + user.getId());
		log.debug("Check user duplicate username is: "  + user.getUsername());

		if (systemUserMapper.selectByExample(condition).size() > 0) {
			return new FeedBackMessage(false, "录入的用户名重复");
		}

		if (user.getId() == 0) {
			log.debug("Create new User: " + user.getUsername());
			user.setPassword(user.getPassword());
			systemUserMapper.insert(user);
		} else {
			systemUserMapper.updateByPrimaryKey(user);
		}
		return new FeedBackMessage(true);
	}

	/**
	 * 删除用户信息
	 *
	 * @param sysid
	 *            用户Id
	 * @return 消息
	 */
	@Override
	@Transactional
	public FeedBackMessage del(Integer sysid) {
		return new FeedBackMessage(systemUserMapper.deleteByPrimaryKey(sysid) > 0);
	}

	/**
	 * 待选用户组
	 *
	 * @param userID
	 * @return
	 */
	@Override
	public List<OSusergroup> getUserGroups(Integer userId) {
		// 用户信息
		OSuser user = systemUserMapper.selectByPrimaryKey(userId);

		// 获得全部的用户组信息
		List<OSusergroup> list = systemGroupMapper.selectAll();

		// 判断用户组是否被选中
		if (user != null) {
			for (OSusergroup group : list) {
				if (group.getId().equals(user.getGroupId())) {
					group.setRemark("true");
				}
			}
		}
		return list;
	}

	/**
	 * 查询用户登录信息
	 * 
	 * @param fields
	 * @return
	 */
	@Override
	public List<OSuser> findRecords(ConditionFiled cf) {
		Example condition = new Example(OSuser.class);
		Criteria criteria = condition.createCriteria();

		if (!StringHelper.isNullOrEmpty(cf.getSysid())) {// 按组搜索用户
			log.debug("Search Users In Group " + cf.getSysid());
			criteria.andEqualTo("groupId", cf.getSysid());
		} else if (StringHelper.isNullOrEmpty(cf.getUsername()) && StringHelper.isNullOrEmpty((cf.getPassword()))) {
			log.debug("Search All Users Except Administrator");
		} else {// 查找特定的用户信息
			criteria.andEqualTo("username", cf.getUsername());
			criteria.andEqualTo("password", cf.getPassword());
		}
		return systemUserMapper.selectByExampleAndRowBounds(condition,
				new RowBounds(cf.getStart(), cf.getLimit() - cf.getStart()));
	}
}
