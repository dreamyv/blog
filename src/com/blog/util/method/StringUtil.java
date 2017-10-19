package com.blog.util.method;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

/**
 * 字符串帮助工具类 StringUtile.java 创建人: jinmengyu 时间: 2016-4-21 下午5:14:25
 */
public class StringUtil {
	/**
	 * 返回后缀名
	 * 
	 * @param name
	 *            文件名称
	 * @param flag
	 *            ture:带点 false:不带点
	 * @return 后缀名
	 */
	public static String getStringExt(String name, boolean flag) {
		if (name != "" || name != null) {
			String ext = null;
			if (flag) {
				ext = name.substring(name.lastIndexOf("."), name.length());// 有点
			} else {
				ext = name.substring(name.lastIndexOf(".") + 1, name.length());// 没点
			}
			return ext;
		} else {
			return "";
		}
	}

	/**
	 * 判断文件中是否有点 创建人: jinmengyu 时间: 2016-8-4 下午10:04:40
	 * 
	 * @param name
	 * @return true带点 false不带点
	 */
	public static boolean isExePoint(String name) {
		int index = name.indexOf(".");
		if (index != -1) {
			return true;
		}
		return false;
	}

	/**
	 * 获取系统毫秒返回时间
	 * 
	 * @param time
	 *            传入System.currentTimeMillis()
	 * @return 返回 年月日 时分秒
	 */
	public static String getTime(long time) {
		String retStrFormatNowDate = "";
		try {
			Date nowTime = new Date(time);
			SimpleDateFormat sdFormatter = new SimpleDateFormat(
					" yy-MM-dd HH:mm:ss ");
			retStrFormatNowDate = sdFormatter.format(nowTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retStrFormatNowDate;
	}

	/**
	 * 空判断 创建人: jinmengyu 时间: 2016-4-21 下午5:11:31
	 * 
	 * @param str
	 *            传入字符串
	 * @return 空返回 true
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0 || "".equals(str)
				|| str.matches("\\s*");
	}

	/**
	 * 非空判断 创建人: jinmengyu 时间: 2016-4-22 下午8:00:24
	 * 
	 * @param str
	 *            字符串
	 * @return 非空返回true
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	/**
	 * 将数字转换成对应的中文 创建人: jinmengyu 时间: 2016-4-21 下午6:22:07
	 * 
	 * @param num
	 *            传入数字
	 * @return 返回中文如：1236 ==> 一千二百三十六
	 */
	public static String intToChnNumConverter(int num) {
		String resultNumber = null;
		if (num > 10000 || num < 0) {
			return "";
		}
		HashMap chnNumbers = new HashMap();
		chnNumbers.put(0, "零");
		chnNumbers.put(1, "一");
		chnNumbers.put(2, "二");
		chnNumbers.put(3, "三");
		chnNumbers.put(4, "四");
		chnNumbers.put(5, "五");
		chnNumbers.put(6, "六");
		chnNumbers.put(7, "七");
		chnNumbers.put(8, "八");
		chnNumbers.put(9, "九");

		HashMap unitMap = new HashMap();
		unitMap.put(1, "");
		unitMap.put(10, "十");
		unitMap.put(100, "百");
		unitMap.put(1000, "千");
		int[] unitArray = { 1000, 100, 10, 1 };

		StringBuilder result = new StringBuilder();
		int i = 0;
		while (num > 0) {
			int n1 = num / unitArray[i];
			if (n1 > 0) {
				result.append(chnNumbers.get(n1)).append(
						unitMap.get(unitArray[i]));
			}
			if (n1 == 0) {
				if (result.lastIndexOf("零") != result.length() - 1) {
					result.append("零");
				}
			}
			num = num % unitArray[i++];
			if (num == 0) {
				break;
			}
		}
		resultNumber = result.toString();
		if (resultNumber.startsWith("零")) {
			resultNumber = resultNumber.substring(1);
		}
		if (resultNumber.startsWith("一十")) {
			resultNumber = resultNumber.substring(1);
		}
		return resultNumber;
	}

	/**
	 * 传入阿拉伯数字1~26返回对应的字母 创建人: jinmengyu 时间: 2016-4-21 下午7:45:13
	 * 
	 * @param num
	 *            1~26数字
	 * @return A~Z
	 */
	public static String getCharacter(int num) {
		String cweek = "";
		if (num == 1)
			cweek = "A";
		if (num == 2)
			cweek = "B";
		if (num == 3)
			cweek = "C";
		if (num == 4)
			cweek = "D";
		if (num == 5)
			cweek = "E";
		if (num == 6)
			cweek = "F";
		if (num == 7)
			cweek = "G";
		if (num == 8)
			cweek = "H";
		if (num == 9)
			cweek = "I";
		if (num == 10)
			cweek = "J";
		if (num == 11)
			cweek = "K";
		if (num == 12)
			cweek = "M";
		if (num == 13)
			cweek = "L";
		if (num == 14)
			cweek = "N";
		if (num == 15)
			cweek = "O";
		if (num == 16)
			cweek = "P";
		if (num == 17)
			cweek = "Q";
		if (num == 18)
			cweek = "R";
		if (num == 19)
			cweek = "S";
		if (num == 20)
			cweek = "T";
		if (num == 21)
			cweek = "U";
		if (num == 22)
			cweek = "V";
		if (num == 23)
			cweek = "W";
		if (num == 24)
			cweek = "X";
		if (num == 25)
			cweek = "Y";
		if (num == 26)
			cweek = "Z";
		return cweek;
	}

	/**
	 * 生成随机字符串 创建人: jinmengyu 时间: 2016-4-30 下午12:07:56
	 * 
	 * @param length
	 *            随机字符串的长度
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		String[] arr = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a",
				"b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "e", "m",
				"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
				"z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "Z" };
		Random random = new Random();
		while (sb.length() < length) {
			String temp = arr[random.nextInt(arr.length)];
			if (sb.indexOf(temp) == -1) {
				sb.append(temp);
			}
		}
		return sb.toString();
	}

	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * 
	 * @param strValue
	 *            String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue) {
		if (null == strValue) {
			return 0;
		}
		int iValue = 0;
		try {
			iValue = new java.lang.Integer(strValue.trim()).intValue();
		} catch (Exception ex) {
			iValue = 0;
		}
		return iValue;
	}

	
	public static String conversionSpecialCharacters(String string) {
		return string.replaceAll("\\\\", "/");
	}
	
	public static String dateAddPart(String strdate, boolean addDay) {
		if (strdate.contains(" ")) {
			return strdate;
		} else {
			if (addDay) {
				return strdate + " 23:59:59.999";
			} else {
				return strdate + " 00:00:00";
			}
		}
	}

	public static String dateAddPart(String strdate, String timePart) {
		if (strdate.contains(" ")) {
			strdate = strdate.split(" ")[0];
		}
		if (timePart != null) {
			return strdate + " " + timePart;
		} else {
			return strdate + " 00:00:00";
		}
	}
}
