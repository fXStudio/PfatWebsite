package cn.fxtech.pfatwebsite.controllers.em;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.fxtech.pfatwebsite.models.EMcategory;
import cn.fxtech.pfatwebsite.services.IEMcategoryService;

/**
 * 分类信息
 *
 * @author Ajaxfan
 */
@RestController
@RequestMapping(value = "services", method = RequestMethod.POST)
public class EMcategoryController {
	/** 日志工具 */
	private Logger log = Logger.getLogger(EMcategoryController.class);

	private @Autowired IEMcategoryService emcategoryService;

	/**
	 * 菜单项列表
	 *
	 * @param limit
	 *            单页数据量
	 * @param start
	 *            数据页码
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "cateTreeList")
	public Object cateTreeList(@RequestParam(value = "cateName", required = false) String catename) throws JsonProcessingException {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("cateName", ".");
		res.put("children", emcategoryService.getCateTreeModel(0));

		log.debug("Request category tree list: " + new ObjectMapper().writeValueAsString(res));

		return res;
	}

	/**
	 * 删除分类信息
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "delCategory")
	public Object delCategory(@RequestParam(value = "id", required = true) Integer id) {
		log.debug("Delete delCategory: " + id);
		return emcategoryService.del(id);
	}

	/**
	 * 修改分类信息
	 *
	 * @param sn
	 * @return
	 */
	@RequestMapping(value = "categoryModify")
	public Object categoryModify(EMcategory cate) {
		log.debug("Request save&modify category: " + cate.getCateName());
		log.debug("Request save&modify category: " + cate.getDepth());
		log.debug("Request save&modify category: " + cate.getIndex());

		return emcategoryService.addOrUpdate(cate);
	}

	/**
	 * 调整树结构
	 *
	 * @param sn
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "categoryAdjust")
	public Object categoryAdjust(HttpServletRequest request)
			throws JsonParseException, JsonMappingException, IOException, IllegalAccessException, InvocationTargetException {
		List<EMcategory> list = new ArrayList<EMcategory>();

		for (LinkedHashMap map : (List<LinkedHashMap>) new ObjectMapper().readValue(request.getParameter("cates"), List.class)) {
			EMcategory em = new EMcategory();
			for (Field field : em.getClass().getDeclaredFields()) {
				BeanUtils.setProperty(em, field.getName(), map.get(field.getName()));
			}
			list.add(em);
		}

		return emcategoryService.adjustCateTree(list);
	}
}
