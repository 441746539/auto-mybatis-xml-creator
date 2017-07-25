package my.framework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 字符串处理类.
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StringUtil {

	private static Log log = LogFactory.getLog(StringUtil.class);

	/**
	 * 是否为空判断
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 是否为空判断
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (null != str) {
			str = trim(str);
			return str.trim().length() == 0;
		} else {
			return true;
		}
	}

	/**
	 * 去除字符串中的空格
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		String[] spaceUnicode = { "\u0020", "\u3000", "\240", "\\u000d", "\\u000a" };
		for (int i = 0; i < spaceUnicode.length; i++) {
			str = str.replaceAll(spaceUnicode[i], "");
		}
		return str;
	}

	/**
	 * null字符串转换""
	 * @param str
	 * @return
	 */
	public static String nullToSpace(String str) {
		if (null == str || "null".equals(str.toLowerCase())) {
			return "";
		}
		return str.toString().trim();
	}

	/**
	 * null字符串转换""
	 * @param str
	 * @return
	 */
	public static String nullToSpace(Object o) {
		if (null == o || "null".equals(o.toString().toLowerCase())) {
			return "";
		}
		return o.toString().trim();
	}

	/**
	 * 字符串转List
	 * @param str 以逗号分隔所有的值
	 * @return
	 */
	public static List strToList(String str) {
		if (isNull(str)) {
			return null;
		}
		List result = new ArrayList();
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length; i++) {
			result.add(strArr[i]);
		}
		return result;
	}

	/**
	 * 字符串转List
	 * @param list
	 * @param symbol 指定分隔符
	 * @return
	 */
	public static List<String> strToList(String str, String symbol) {
		if (isNull(str) || isNull(symbol)) {
			return null;
		}
		List<String> result = new ArrayList<String>();
		String[] strArr = str.split(symbol);
		for (int i = 0; i < strArr.length; i++) {
			if (!result.contains(String.valueOf(strArr[i]))) {
				result.add(String.valueOf(strArr[i]));
			}
		}
		return result;
	}

	/**
	 * 数组转字符串
	 * @param list
	 * @return
	 */
	public static String listToStr(List list) {
		if (null == list || list.size() <= 0) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			result.append(list.get(i)).append(",");
		}
		if (result.lastIndexOf(",") != -1) {
			return result.substring(0, result.lastIndexOf(","));
		}
		return result.toString();
	}

	/**
	 * 字符串转Integer型List
	 * @param str 以逗号分隔所有的值
	 * @return
	 */
	public static List<Integer> strToIntList(String str) {
		if (null == str || str.equals("")) {
			return null;
		}
		List<Integer> result = new ArrayList<Integer>();
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length; i++) {
			result.add(Integer.valueOf(strArr[i]));
		}
		return result;
	}

	/**
	 * 字符串转Long型List
	 * @param str 以逗号分隔所有的值
	 * @return
	 */
	public static List<Long> strToLongList(String str) {
		if (null == str || str.equals("")) {
			return null;
		}
		List<Long> result = new ArrayList<Long>();
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length; i++) {
			result.add(Long.valueOf(strArr[i]));
		}
		return result;
	}

	/**
	 * 字符串转Long型List并去除重复的值
	 * @param str 以逗号分隔所有的值
	 * @return
	 */
	public static List<Long> noRepeatStr(String str) {
		if (null == str || str.equals("")) {
			return null;
		}
		List<Long> result = new ArrayList<Long>();
		String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length; i++) {
			if (!result.contains(Long.valueOf(strArr[i]))) {
				result.add(Long.valueOf(strArr[i]));
			}
		}
		return result;
	}

	/**
	 * List转换成去除重复的值
	 * @param 
	 * @return
	 */
	public static List<Long> noRepeatList(List<Long> list) {
		if (null == list || list.size() <= 0) {
			return null;
		}
		List<Long> result = new ArrayList<Long>();
		for (Long val : list) {
			if (!result.contains(val)) {
				result.add(val);
			}
		}
		return result;
	}

	/**
	 * 字符串转换为数组
	 * @param str
	 * @return
	 */
	public static String[] strToStrs(String str) {
		if (isNull(str)) {
			return null;
		}
		String[] strs = new String[0];
		if (StringUtils.hasLength(str)) {
			strs = str.split(",");
		}
		return strs;
	}

	/**
	 * 数组转换为字符串
	 * @param str
	 * @return
	 */
	public static String strsToStr(Object[] vals) {
		if (null == vals || vals.length <= 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		for (Object o : vals) {
			if (null != o && !o.equals("")) {
				result.append(o).append(",");
			}
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String firstLowerCase(String str) {
		String targetStr = "";
		if (StringUtil.isNotNull(str)) {
			String first_word = str.substring(0, 1);
			targetStr = str.replaceFirst(first_word, first_word.toLowerCase());
		}
		return targetStr;
	}

	/**
	 * sql属性设置专用
	 * insert(a,b,c) values ([PARAM]0,[PARAM]1,[PARAM]2)输入参数替换
	 * @param str
	 * @param o
	 * @return
	 */
	public static String setParameter(String str, Object... o) {
		if (!isNull(str) && null != o && o.length > 0) {
			for (int i = 0; i < o.length; i++) {
				if (null != o[i]) {
					str = str.replace("[PARAM]" + i, nullToSpace(o[i]));
				}
			}
		}
		return str;
	}

	/**
	 * 字符格式转换
	 * @param fileName
	 * @return
	 */
	public static String convertEncode(String fileName, String origEncode, String targEncode) {
		String fileName_ = "";
		try {
			fileName_ = new String(fileName.getBytes(origEncode), targEncode);
		} catch (UnsupportedEncodingException e) {
			log.error("-- convertEncode error --", e);
		}
		return fileName_;
	}

	/**
	 * 比较两个字符串有没有变化过
	 * @param previous
	 * @param current
	 * @return
	 */
	public static boolean isStrChanged(String previous, String current) {
		boolean flag = true;
		if (isNull(previous) && isNull(current)) {
			flag = false;
		} else if (isNotNull(previous) && isNotNull(current) && previous.equals(current)) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 防止sql注入简易替换符号
	 * @param sql
	 * @return
	 */
	public static String injectionDef(String str) {
		String str_ = nullToSpace(str);
		if (StringUtil.isNotNull(str)) {
			str_ = str.replace("'", "\"").replace(";", ".");
		}
		return str_;
	}
}