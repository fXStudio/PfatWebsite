package cn.fxtech.pfatwebsite.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fxtech.pfatwebsite.mappers.MDdatadictoryMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.MDdatadictory;
import cn.fxtech.pfatwebsite.protocal.ConditionFiled;
import cn.fxtech.pfatwebsite.services.IMDdatadictoryService;

/**
 * 数据字典
 * 
 * @author FXStudio.Ajaxfan
 */
@Service
final class MDdatadictoryService implements IMDdatadictoryService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDdatadictoryService.class);

	private @Autowired MDdatadictoryMapper mddataMapper;

	/**
	 * @param cf
	 * @return
	 */
	@Override
	public List<MDdatadictory> findRecords(ConditionFiled cf) {
		log.debug("Search datadictionary.");
		return mddataMapper.selectAll();
	}

	/**
	 * @param sdata
	 * @return
	 */
	@Override
	@Transactional
	public Object update(MDdatadictory sdata) {
		return new FeedBackMessage(mddataMapper.update(sdata) > 0);
	}
}
