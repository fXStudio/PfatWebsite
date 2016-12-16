package cn.fxtech.pfatwebsite.providers;

import org.apache.log4j.Logger;

import cn.fxtech.pfatwebsite.models.MDdatadictory;

public class MDdatadictoryProvider {
	/** 日志工具 */
	private Logger log = Logger.getLogger(MDdatadictoryProvider.class);

	/** 数据更新语句 */
	private static final String UPDATE_DQL = "UPDATE os_dictionary SET item_val = #{itemVal} WHERE id = #{id}";

	/**
	 * 更新数据
	 * 
	 * @param record
	 * @return
	 */
	public String updateByPrimaryKey(final MDdatadictory record) {
		log.debug(UPDATE_DQL);
		log.debug("Update dictionary item id is: " + record.getId());
		log.debug("Update dictionary item value is: " + record.getItemVal());

		return UPDATE_DQL;
	}
}
