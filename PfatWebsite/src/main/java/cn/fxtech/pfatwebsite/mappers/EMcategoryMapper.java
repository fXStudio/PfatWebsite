package cn.fxtech.pfatwebsite.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
	@Select("SELECT id, cate_name, cate_score, parent_id, remark, depth, cate_no, create_year FROM em_category WHERE parent_id = #{parentId} AND create_year = #{createYear} ORDER BY cate_no")
	@Results({ @Result(property = "cateName", column = "cate_name"), @Result(property = "cateScore", column = "cate_score"),
			@Result(property = "parentId", column = "parent_id"), @Result(property = "index", column = "cate_no"),
			@Result(property = "createYear", column = "create_year") })
	public List<EMcategory> findByParentId(@Param("parentId") Integer parentId, @Param("createYear") String createYear);

	@Delete("DELETE FROM em_category WHERE FIND_IN_SET(id, query_categorytree(#{id}))")
	public void delRecursion(Integer id);
}
