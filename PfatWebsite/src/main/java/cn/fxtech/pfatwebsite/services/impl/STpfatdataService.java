package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fxtech.pfatwebsite.mappers.STpfatdataMapper;
import cn.fxtech.pfatwebsite.models.STpfatdata;
import cn.fxtech.pfatwebsite.services.ISTpfatdataService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class STpfatdataService implements ISTpfatdataService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(STpfatdataService.class);

	private @Autowired STpfatdataMapper stpfatdatamapper;

	@Override
	public List<STpfatdata> findAll(String createYear) {
		log.debug("Query All STpfatdata");

		Example condition = new Example(STpfatdata.class);
		Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("createYear", createYear);

		return stpfatdatamapper.selectByExample(condition);
	}
}
