package cn.fxtech.pfatwebsite.helper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	/**
	 * 将Bean中的数据拷贝到Map中
	 * 
	 * @param source
	 * @param target
	 */
	public static Map<String, Object> createMap(Object source) {
		Map<String, Object> target = new HashMap<String, Object>();

		for (Field field : source.getClass().getDeclaredFields()) {
			try {
				target.put(field.getName(), getProperty(source, field.getName()));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return target;
	}
}
