/*
 * @project_name:MUFareEngine
 * @Company (开发公司或单位)：印孚瑟斯技术(中国)有限公司
 * @Copyright © 2015 - 2999 CHINA EASTERN Limited
 */
package com.sun.storemanager.common.utils;



import com.sun.tools.javac.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
 * Class History
 * Author          Date               Version    Comments
 * David.Zheng05      Nov 30, 2015          1.00       Added Comments section
 */

/**
 * StringUtil. This class is used for String general operation
 * 
 * @author Infosys
 */
public  class StringUtil  {

	/**
	 * Creates a new instance of StringUtil. This Constructor can't be invoked
	 * by public
	 */
	private StringUtil() {
		super();
	}

	/**
	 * 验证字符串不为空和null值.
	 * 
	 * @param name
	 *            str
	 * @return boolean true empty
	 */
	public static boolean isEmpty(final String name) {
		boolean ret = false;
		if (null != name && !"".equals(name)) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}

	/**
	 * 验证字符串不为空和null值和" ".
	 * 
	 * @param name
	 *            str
	 * @return boolean true empty
	 */
	public static boolean isBlank(String str) {
		boolean ret = false;
		if(!isEmpty(str)){//如果str是带空格去空再进行下面判断。
			str = str.trim();
		}
		if (null != str && !"".equals(str)) {
			ret = false;
		} else {
			ret = true;
		}
		return ret;
	}
	

	public static boolean isBlank(String  str1 , String  str2){
		//判断ifareMu 因子是否为  null 或  空字符
		if(isEmpty(str1)&& isEmpty(str2)){
			return true;
		}
		return false ;
	}

	/**
	 * 验证字符串不为空和null值和" " 和 "0"
	 * @param str
	 * @return
     */
	public static boolean isBlankOrZero(String str) {
		if(isBlank(str)||"0".equals(str)) {
			return true;
		}
		return false ;
	}
	
	public static List<Integer> StringToArray(String str){
		List <Integer> listInt = new ArrayList<Integer>();
		String StrTem = str.substring(1, str.length()-1);
		String[] strArray = null;   
		strArray = StrTem.split(",");
		for(String i:strArray){
			listInt.add(Integer.parseInt(i));
		}
		return listInt;
	}
	
	/**
	 * 
	 * subString
	 * @param str  被截取字符
	 * @param index  截取第位数
	 * @return
	 */
	public static String subString(String str , int index){
		if(isBlank(str) || str.length()<index || (index-1)<0 ){
			return "";
		}
		return str.substring(index-1 , index);
	}
	
	/**
	 * 
	 */
	public static String trimToEmpty(Object str){
		if(null == str){
			return "";
		}
		return String.valueOf(str).trim() ;
	}

	/**
	 * 去掉数字字符串开头的0
	 * @param str
	 * @return
	 */
	public static String remove0(String str){
		if(null == str){
			return null;
		}
		str = str.replaceFirst("^0*", "");
		return str;
	}

	/*方法二：推荐，速度最快
	 * 判断是否为整数
	 * @param str 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */

	public static boolean isInteger(String str) {
		if(null == str){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
