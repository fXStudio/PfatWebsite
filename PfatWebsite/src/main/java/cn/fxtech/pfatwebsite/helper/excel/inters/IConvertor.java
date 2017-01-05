package cn.fxtech.pfatwebsite.helper.excel.inters;

import java.util.LinkedHashMap;
import java.util.List;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;

public interface IConvertor {
	public List<ExportModel> convertMapToModelList(@SuppressWarnings("rawtypes") List<LinkedHashMap> list);
}
