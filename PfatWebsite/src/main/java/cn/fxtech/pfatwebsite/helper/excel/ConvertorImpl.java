package cn.fxtech.pfatwebsite.helper.excel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;
import cn.fxtech.pfatwebsite.helper.excel.inters.IConvertor;
import cn.fxtech.pfatwebsite.models.STpfatdata;

final class ConvertorImpl implements IConvertor {
	/**
	 * Map转换为导出数据模型
	 */
	@Override
	public List<ExportModel> convertMapToModelList(List<STpfatdata> beans) {
		List<ExportModel> list = new ArrayList<ExportModel>();

		for (STpfatdata bean : beans) {// 数据转换
			ExportModel model = new ExportModel();

			try {
				BeanUtils.copyProperties(model, bean);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			list.add(model);
		}
		return list;
	}
}
