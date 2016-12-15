package cn.fxtech.pfatwebsite.helper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSon转换工具
 * 
 * @author FXStudio.Ajaxfan
 */
public final class JSONConvertor {
	/** Jackson工具 */
	private static ObjectMapper om = new ObjectMapper();

	static {
		om.setSerializationInclusion(Include.NON_EMPTY);
	}

	/**
	 * java对象转化为Json串
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2Json(Object obj) {
		try {
			return om.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
