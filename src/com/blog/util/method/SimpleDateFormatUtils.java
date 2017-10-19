package com.blog.util.method;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatUtils {

	private static final SimpleDateFormat fullSF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 获得yyyy-MM-dd HH:mm:ss的SimpleDateFormat
	 * 
	 * @return
	 */
	public static SimpleDateFormat getFullSF() {
		return (SimpleDateFormat) fullSF.clone();
	}
	/**
	 * 传入时间的当天开始时间
	 * @param strdate
	 * @param addDay
	 * @return
	 */
	public static Date convert(String strdate, boolean addDay) {
		if (!StringUtil.isEmpty(strdate)) {
			try {
				return fullSF.parse(StringUtil.dateAddPart(strdate, addDay));
			} catch (ParseException e) {
			}
		}
		return null;
	}
	/**
	 * 传入时间的当天结束时间
	 * @param strdate
	 * @param addDay
	 * @return
	 */
	public static Date convertForce(String strdate, boolean addDay) {
		if (!StringUtil.isEmpty(strdate)) {
			try {
				return fullSF.parse(StringUtil.dateAddPart(strdate, (addDay ? "23:59:59.999" : null)));
			} catch (ParseException e) {
			}
		}
		return null;
	}
}
