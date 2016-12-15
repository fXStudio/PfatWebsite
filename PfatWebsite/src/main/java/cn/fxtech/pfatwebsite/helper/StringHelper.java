package cn.fxtech.pfatwebsite.helper;

/**
 * 字符串工具
 * 
 * @author FXStudio.Ajaxfan
 */
public final class StringHelper {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNullOrEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
