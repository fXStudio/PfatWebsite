package cn.fxtech.pfatwebsite.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.fxtech.pfatwebsite.models.EMpfatitem;
import tk.mybatis.mapper.common.Mapper;

/**
 * 考核项
 * 
 * @author FXStudio.Ajaxfan
 */
public interface EMpfatitemMapper extends Mapper<EMpfatitem>{
	@Select("SELECT * FROM em_pfatitem WHERE dept_id = #{deptId} AND find_in_set(status, '0, 3')")
	@Results({
		@Result(property="itemName", column="item_name"),
		@Result(property="cateId", column="cate_id"),
		@Result(property="deptId", column="dept_id"),
		@Result(property="compDate", column="comp_date"),
		@Result(property="itemScore", column="item_score"),
		@Result(property="docName", column="doc_name"),
		@Result(property="officeName", column="office_name"),
		@Result(property="personName", column="person_name"),
		@Result(property="telPhone", column="tel_phone")
	})
	public List<EMpfatitem> findRecordsByDept(Integer deptId);
}
