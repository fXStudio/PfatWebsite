package cn.fxtech.pfatwebsite.helper.excel.inters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.fxtech.pfatwebsite.models.STpfatdata;

public interface IExcelHelper {
	public void export2Stream(List<STpfatdata> list, OutputStream out) throws IOException;
}
