package cn.fxtech.pfatwebsite.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.fxtech.pfatwebsite.models.EMcategory;
import tk.mybatis.mapper.common.Mapper;

/**
 * 分类信息
 * 
 * @author FXStudio.Ajaxfan
 */
public interface EMcategoryMapper extends Mapper<EMcategory> {
	@Select("SELECT id, cate_name, cate_score, parent_id, remark, level FROM em_category WHERE parent_id = #{parentId}")
	@Results({ @Result(property = "cateName", column = "cate_name"),
			@Result(property = "cateScore", column = "cate_score", javaType = Integer.class),
			@Result(property = "parentId", column = "parent_id", javaType = Integer.class),
			@Result(property = "level", column = "level", javaType=Integer.class)})
	public List<EMcategory> findByParentId(Integer parentId);
	
	@Delete("DELETE FROM em_category WHERE FIND_IN_SET(id, query_categorytree(#{id}))")
	public void delRecursion(Integer id);
}
