package cn.fxtech.pfatwebsite.helper.excel.inters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.fxtech.pfatwebsite.helper.excel.entities.ExportModel;

/**
 * Excel 文件导出接口
 */
public interface IExcelExporter {
    /**
     * 导出文件
     *
     * @param out 输出文件流
     */
    public void export(List<ExportModel> list, OutputStream out) throws IOException;
}
