package cn.fxtech.pfatwebsite.helper.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.fxtech.pfatwebsite.helper.excel.inters.IConvertor;
import cn.fxtech.pfatwebsite.helper.excel.inters.IExcelExporter;
import cn.fxtech.pfatwebsite.helper.excel.inters.IExcelHelper;
import cn.fxtech.pfatwebsite.models.STpfatdata;

/**
 * Excel 解析工具类
 *
 * @author AjaxFan
 */
@Component
final class ExcelHelper implements IExcelHelper {
	private IExcelExporter exporter = new ExcelExporterImpl();
	private IConvertor convertor = new ConvertorImpl();

	/**
	 * 导出Excel到文件流
	 * 
	 * @param obj
	 * @param out
	 * @throws IOException
	 */
	@Override
	public synchronized void export2Stream(List<STpfatdata> list, OutputStream out) throws IOException {
		exporter.export(convertor.convertMapToModelList(list), out);
	}
}
