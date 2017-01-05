package cn.fxtech.pfatwebsite.helper.excel.inters;

import java.util.List;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;
import cn.fxtech.pfatwebsite.models.STpfatdata;

public interface IConvertor {
	public List<ExportModel> convertMapToModelList(List<STpfatdata> list);
}
