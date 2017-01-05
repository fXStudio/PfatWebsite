package cn.fxtech.pfatwebsite.helper.excel.inters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

public interface IExcelHelper {
	public void export2Stream(@SuppressWarnings("rawtypes") List<LinkedHashMap> list, OutputStream out) throws IOException;
}
