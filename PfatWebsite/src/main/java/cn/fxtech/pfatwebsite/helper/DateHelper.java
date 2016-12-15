package cn.fxtech.pfatwebsite.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * 
 * @author FXStudio.Ajaxfan
 */
public final class DateHelper {
	/** 日期格式化工具类 */
	private static DateFormat DATE_FMT = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * @return 当日
	 */
	public static String firstDayOfMonth() {
		return DATE_FMT.format(GregorianCalendar.getInstance().getTime());
	}

	/**
	 * @return 下一天
	 */
	public static String firstDayOfNextMonth() {
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, 1);

		return DATE_FMT.format(cal.getTime());
	}
}
