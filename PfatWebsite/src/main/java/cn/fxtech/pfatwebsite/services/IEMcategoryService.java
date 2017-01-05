package cn.fxtech.pfatwebsite.services;

import java.util.List;
import java.util.Map;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMcategory;

/**
 * 分类管理
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IEMcategoryService {
	public FeedBackMessage addOrUpdate(EMcategory cate);

	public FeedBackMessage del(Integer id);

	public List<Map<String, Object>> getCateTreeModel(Integer parentId, String createYear);

	public Object adjustCateTree(List<EMcategory> list);
}
