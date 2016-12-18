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
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 系统菜单项
 *
 * @author FXStudio.Ajaxfan
 */
@Service
final class EMcategoryService implements IEMcategoryService {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMcategoryService.class);

	/** 根据现在的需求，树的最大深度为3 */
	private static final int LEAF_DEEP = 3;

	private @Autowired EMcategoryMapper emcategoryMapper;

	@Override
	public FeedBackMessage addOrUpdate(EMcategory cate) {
		Example condition = new Example(EMcategory.class);
		Criteria criteria = condition.createCriteria();
		criteria.andNotEqualTo("id", cate.getId());
		criteria.andEqualTo("cateName", cate.getCateName());

		if (emcategoryMapper.selectByExample(condition).size() > 0) {
			log.debug("Category name duplicate: " + cate.getCateName());
			return new FeedBackMessage(false, "考核分类名称重复");
		}

		if (cate.getId() == 0) {
			log.debug("Create new Category name is: " + cate.getCateName());
			return new FeedBackMessage(emcategoryMapper.insert(cate) > 1);
		}
		log.debug("Update Category name is: " + cate.getCateName());
		return new FeedBackMessage(emcategoryMapper.updateByPrimaryKey(cate) > 1);
	}

	/**
	 * 删除分类信息，将连同其子分类信息一同删除掉
	 */
	@Override
	public FeedBackMessage del(Integer id) {
		log.debug("Delete category and it's child category: " + id);

		try {
			emcategoryMapper.delRecursion(id);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new FeedBackMessage(false, e.getMessage());
		}
		return new FeedBackMessage(true);
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

			boolean isLeaf = cate.getLevel() == LEAF_DEEP;

			map.put("leaf", isLeaf);
			map.put("iconCls", isLeaf ? "leaf_node" : "branch_node");

			if (!isLeaf) {// 只有非叶子节点才能有子节点
				map.put("children", getCateTreeModel(cate.getId()));
				map.put("expanded", true);
			}
			result.add(map);
		}
		return result;
	}
}
