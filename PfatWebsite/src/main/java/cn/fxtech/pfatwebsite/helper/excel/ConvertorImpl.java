package cn.fxtech.pfatwebsite.helper.excel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;
import cn.fxtech.pfatwebsite.helper.excel.inters.IConvertor;

final class ConvertorImpl implements IConvertor {
	/**
	 * Map转换为导出数据模型
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public List<ExportModel> convertMapToModelList(List<LinkedHashMap> maps) {
		List<ExportModel> list = new ArrayList<ExportModel>();

		for (LinkedHashMap map : maps) {// 数据转换
			ExportModel model = new ExportModel();

			for (Object key : map.keySet()) {
				try {
					BeanUtils.setProperty(model, (String) key, map.get(key));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			list.add(model);
		}
		return list;
	}
}
