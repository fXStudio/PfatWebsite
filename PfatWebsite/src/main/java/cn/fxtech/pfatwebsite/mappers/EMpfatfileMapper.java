package cn.fxtech.pfatwebsite.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.fxtech.pfatwebsite.models.EMpfatfile;

/**
 * 分类信息
 * 
 * @author FXStudio.Ajaxfan
 */
public interface EMpfatfileMapper {
	@Select("SELECT file_name, file_path FROM em_pfatfile WHERE id = #{id}")
	@Results({ @Result(property = "fileName", column = "file_name"), @Result(property = "filePath", column = "file_path") })
	public EMpfatfile findRecordById(Integer id);

	@Insert("INSERT INTO em_pfatfile (file_name, file_path, pfatitem_id, created, cate) VALUES (#{fileName}, #{filePath}, #{pfatitemId}, current_timestamp, #{cate})")
	public int insert(EMpfatfile file);

	@Select("SELECT id, file_name, created FROM em_pfatfile WHERE pfatitem_id = #{pfatitemId} AND cate = #{cate}")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "fileName", column = "file_name"),
			@Result(property = "created", column = "created") })
	public List<EMpfatfile> findRecords(EMpfatfile file);
	
	@Select("SELECT count(id) FROM em_pfatfile WHERE pfatitem_id = #{pfatitemId} AND file_name = #{fileName}")
	public Integer isExists(EMpfatfile file);
	
	@Delete("DELETE FROM em_pfatfile WHERE id = #{id}")
	public int del(Integer id);
}
