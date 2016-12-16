package cn.fxtech.pfatwebsite.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fxtech.pfatwebsite.helper.BeanUtils;
import cn.fxtech.pfatwebsite.mappers.EMcategoryMapper;
import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMcategory;
import cn.fxtech.pfatwebsite.services.IEMcategoryService;

/**
 * 系统菜单项
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class EMcategoryService implements IEMcategoryService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMcategoryService.class);

	private @Autowired EMcategoryMapper emcategoryMapper;

	@Override
	public FeedBackMessage addOrUpdate(EMcategory cate) {
		log.debug("Create new category: " + cate.getCateName());
		return null;
	}

	/**
	 * 删除分类信息，将连同其子分类信息一同删除掉
	 */
	@Override
	public FeedBackMessage del(Integer id) {
		log.debug("Delete category and it's child category: " + id);
		return new FeedBackMessage(emcategoryMapper.deleteByPrimaryKey(id) > 0);
	}

	/**
	 * 分类树模型
	 */
	@Override
	public List<Map<String, Object>> getCateTreeModel(Integer parentId) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		for (EMcategory cate : emcategoryMapper.findByParentId(parentId)) {
			// 用DynaBean来做树节点对象(利用Json的灵活性特点，不需要单独声明类)
			Map<String, Object> map = BeanUtils.createMap(cate);

			List<Map<String, Object>> subnodes = getCateTreeModel(cate.getId());
			boolean isLeaf = subnodes.size() == 0;

			map.put("leaf", isLeaf);
			map.put("iconCls", isLeaf ? "leaf_node" : "branch_node");

			if (!isLeaf) {// 只有非叶子节点才能有子节点
				map.put("children", subnodes);
				map.put("expanded", true);
			}
			result.add(map);
		}
		return result;
	}
}
