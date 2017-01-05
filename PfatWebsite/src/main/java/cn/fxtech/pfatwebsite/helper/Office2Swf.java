package cn.fxtech.pfatwebsite.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author hwl_sz
 *
 * @desc 需要swftools第三插件的支持 ,支持window\linux\mac等系统
 */
@Component
public class Office2Swf {
	private @Value("${swf_tool}") String SWF_TOOL;
	private @Autowired Office2PDF office2pdf;

	/**
	 * 根据操作系统的名称，获取执行pdf->swf文件的命令
	 * 
	 * @param pdfFile
	 *            转换的pdf源文件路径
	 * @param swfOutFilePath
	 *            输出的swf文件路径
	 * @return
	 */
	private String getCommand(String pdfFile, String swfOutFilePath) {
		String command = null;
		String osName = System.getProperty("os.name");
		if (null == swfOutFilePath || "".equals(swfOutFilePath.trim())) {
			swfOutFilePath = pdfFile.toLowerCase().replaceAll(".pdf", ".swf");
		}

		if (Pattern.matches("Linux.*", osName)) {
			command = "pdf2swf -f " + pdfFile + " " + swfOutFilePath;
		} else if (Pattern.matches("Windows.*", osName)) {
			command = SWF_TOOL + " -t " + pdfFile + " -o " + swfOutFilePath + " -T 9";
		} else if (Pattern.matches("Mac.*", osName)) {
		}
		return command;
	}

	/**
	 * 将pdf转换swf文件，在线预览
	 * 
	 * @param pdfInputFilePath
	 *            待转换的pdf源文件路径
	 * @param swfOutFilePath
	 *            输出的swf目标文件路径，如果未指定(null)，则按在源文件当前目录生成同名的swf文件
	 * @return swf目标文件路径
	 */
	public String pdf2Swf(String pdfInputFilePath, String swfOutFilePath) {
		String command = getCommand(pdfInputFilePath, swfOutFilePath);
		try {
			Process pro = Runtime.getRuntime().exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while (bufferedReader.readLine() != null);
			pro.waitFor();
			return pdfInputFilePath.replaceAll("." + getPostfix(pdfInputFilePath), ".swf");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 将office文件直接转换为swf文件
	 * 
	 * @param inputFilePath
	 *            待转换的源office文件路径
	 * @param outputSwfPath
	 *            输出的swf目标文件路径，如果未指定(null)，则按在源文件当前目录生成同名的swf文件
	 * @return swf目标文件路径
	 */
	public String office2Swf(String inputFilePath, String outputSwfPath) {
		String outputPdfPath = null;
		if (null == outputSwfPath || "".equals(outputSwfPath.trim())) {
			outputPdfPath = inputFilePath.replace("." + getPostfix(inputFilePath), ".pdf");
		} else {
			outputPdfPath = outputSwfPath.replace("." + getPostfix(outputSwfPath), ".pdf");
		}

		boolean isSucc = office2pdf.openOffice2Pdf(inputFilePath, outputPdfPath);

		if (isSucc) {
			outputSwfPath = pdf2Swf(outputPdfPath, outputSwfPath);
		}
		return outputSwfPath;
	}

	/**
	 * 获取文件的后缀名
	 */
	private String getPostfix(String inputFilePath) {
		String postfix = null;
		if (null != inputFilePath && !"".equals(inputFilePath.trim())) {
			int idx = inputFilePath.lastIndexOf(".");
			if (idx > 0) {
				postfix = inputFilePath.substring(idx + 1, inputFilePath.trim().length());
			}
		}
		return postfix;
	}
}
