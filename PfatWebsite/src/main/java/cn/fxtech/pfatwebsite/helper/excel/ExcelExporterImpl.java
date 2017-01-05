package cn.fxtech.pfatwebsite.helper.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;
import cn.fxtech.pfatwebsite.helper.excel.inters.IExcelExporter;

/**
 * 文件的导出操作
 */
final class ExcelExporterImpl implements IExcelExporter {
	/** 模板文件路径 */
	private static final String FILE_PAHT = "/cn/fxtech/pfatwebsite/helper/excel/resources/data.xls";

	/**
	 * 导出模板Excel文件
	 * 
	 * @param out
	 */
	@Override
	public void export(List<ExportModel> list, OutputStream out) throws IOException {
		// 加载电子表格模板
		HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(this.getClass().getResourceAsStream(FILE_PAHT)));
		// 创建电子表格
		makeSheet(workbook.getSheetAt(0), list);

		// 写出电子表格到客户端
		workbook.write(out);
	}

	/**
	 * 制作电子表格
	 * 
	 * @param sheet
	 * @param list
	 */
	private void makeSheet(HSSFSheet sheet, List<ExportModel> list) {
		// 用来缓存已读取的数据，通过它可以实现表格的合并
		Map<String, Integer> cache = new HashMap<String, Integer>();

		int rowIndex = 1;// 数据行行索引，第一行为表格头，所以数据从第二行开始

		for (ExportModel obj : list) {
			int cellIndex = 0;// 列索引，从第一列开始复制
			HSSFRow row = sheet.createRow(rowIndex);// 创建数据行

			// 按顺序填充表格信息
			for (Field field : obj.getClass().getDeclaredFields()) {
				try {
					String name = field.getName();
					String val = BeanUtils.getProperty(obj, field.getName());
					String key = cellIndex + val;

					if (cellIndex < 3 && val != null && val.trim().length() > 0 && cache.containsKey(key)) {// 前三列需要执行合并操作
						sheet.addMergedRegion(new CellRangeAddress(cache.get(key), rowIndex, cellIndex, cellIndex));
					} else if ("status".equalsIgnoreCase(name)) {// 状态列需要特殊处理
						makeStatusCell(row.createCell(cellIndex), val, sheet.getWorkbook().createCellStyle());
					} else {// 设置单元格内容，并记录要合并单元格出现的位置
						row.createCell(cellIndex).setCellValue(val);
						cache.put(key, rowIndex);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				cellIndex++;
			}
			rowIndex++;
		}
	}

	/**
	 * 构建状态列
	 * 
	 * @param cell
	 * @param val
	 * @param style
	 */
	private void makeStatusCell(HSSFCell cell, String val, HSSFCellStyle style) {
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		if ("0".equals(val)) {
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			val = "新建项目";
		} else if ("1".equals(val)) {
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.GOLD.index);
			val = "申请审核";
		} else if ("2".equals(val)) {
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.LIME.index);
			val = "审核通过";
		} else if ("3".equals(val)) {
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			style.setFillForegroundColor(HSSFColor.RED.index);
			val = "审核未通过";
		}
		cell.setCellStyle(style);
		cell.setCellValue(new HSSFRichTextString(val));
	}
}
