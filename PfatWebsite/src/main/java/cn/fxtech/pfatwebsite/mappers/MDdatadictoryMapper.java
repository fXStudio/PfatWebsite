package cn.fxtech.pfatwebsite.mappers;

import org.apache.ibatis.annotations.Update;

import cn.fxtech.pfatwebsite.models.MDdatadictory;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author FXStudio.Ajaxfan
 */
public interface MDdatadictoryMapper extends Mapper<MDdatadictory> {
	@Update("UPDATE os_dictionary SET item_val = #{itemVal} WHERE id = #{id}")
	int update(MDdatadictory record);
}
