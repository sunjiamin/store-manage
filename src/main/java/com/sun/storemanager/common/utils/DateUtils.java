/*
 * @project_name:MUFareEngine
 * @Company (开发公司或单位)：印孚瑟斯技术(中国)有限公司
 * @Copyright © 2015 - 2999 CHINA EASTERN Limited
 */

package com.sun.storemanager.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/*
 * Class History
 * Author          Date               Version    Comments
 * Min.Zhang      Dec 1, 2015          1.00       Added Comments section
 * WangWenlong     20180919           1.01       Added Method utilDate2SqlDate
 * WangWenlong     20180919           1.02       Added Method formatUSDate
 */

/**
 * This class is used for Date format
 * 
 * @author Infosys
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	/**
	 * 默认构造器.
	 */
	private DateUtils() {
		super();
	}


	/**
	 * 系统内部日期处理格式
	 */
	public final static String DB_DATE_FORMAT = "MM/dd/yyyy";

	/**
	 * ECFare 导出文件名日期格式
	 */
	public final static String EXPORT_DATE_FORMAT = "yyyyMMdd-HHmmss";
	public final static String EXPORT_DATE_FORMAT1 = "yyyyMMddHHmm";
	public final static String EXPORT_DATE_FORMAT2 = "yyyy-MM-dd HH:mm";
	public final static String EXPORT_DATE_FORMAT3 = "yyyyMMddHHmmss";
	public final static String EXPORT_DATE_FORMAT4 = "ddMMMMyyyy";
	public final static String EXPORT_DATE_FORMAT5 = "ddMMMyyyy";
	public final static String EXPORT_DATE_FORMAT6 = "ddMMM";


	/**
	 * 指定语言日期格式
	 */
	public final static String LOCAL_DATE_FORMAT = "dd-MMM-yyyy";

	/**
	 * 简单日期格式.
	 */
	public final static String SIMPLE_DATE_FORMAT ="yyMMdd";
	public final static String M2D2_FORMAT ="MMdd";
	public final static String M2_FORMAT ="MM";
	public final static String D2_FORMAT ="dd";

	/**
	 * 缺省日期格式.
	 */
	public final static String DEFAULT_DATE_FORMAT ="yyyyMMdd";

	/**
	 * 日期格式.
	 */
	public final static String OTHER_DATE_FORMAT ="yyyy/MM/dd";

	/**
	 * ICE日期格式
	 */
	public final static String ICE_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 含秒的时间处理格式
	 */
	public final static String DEFAULT_TIME_FORMAT = "HHmmss";

    public final static String  DATE_FORMAT_YYYYMM ="yyyyMM";
    public final static String  DATE_FORMAT_YYYY ="yyyy";

	/**
	 * 字符串格式转日期
	 *
	 * @param source
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date formatDate(String source, String dateFormat) throws ParseException {
		if (StringUtil.isBlank(source)) {
			return null;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		return simpleDateFormat.parse(source);
	}

    public static String formatSdfDate(Date source, String dateFormat,Locale local)  {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat,local);
        return simpleDateFormat.format(source);
    }

	/**
	 * 日期转换字符串
	 *
	 * @param source
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static String formatDate(Date source, String dateFormat) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
		return simpleDateFormat.format(source);
	}
	/**
	 * 日期转换指定语言字符串
	 *
	 * @param source
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static String formatDate(Date source, String dateFormat,Locale local) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat,local);
		return simpleDateFormat.format(source);
	}

	/**
	 *
	 * 获取一年后的日期
	 *
	 * @return
	 */
	public static String getOneYearLaterDate(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.YEAR, 1);
		Date resultDate = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(resultDate);

	}

	/**
	 *
	 * 获取十三月后的日期
	 *
	 * @return
	 */
	public static String getThirteenMonthDate(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.MONTH, 13);
		Date resultDate = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(resultDate);
	}

	public static String getSysYear() {
		Calendar date = Calendar.getInstance();
		String year = String.valueOf(date.get(Calendar.YEAR));
		return year;
	}


	/**
	 * 比较日期大小
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 * @throws ParseException
	 */
	public static int compareDate(String dateStr1,String dateStr2) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(OTHER_DATE_FORMAT);
		Date date1=sdf.parse(dateStr1);
		Date date2=sdf.parse(dateStr2);
		int i=date1.compareTo(date2);
		return i;
	}

    /**
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int compareDate(Date date1,Date date2) throws ParseException{
        int i=date1.compareTo(date2);
        return i;
    }
	/**
	 * 比较日期大小(默认日期格式)
	 * @param dateStr1
	 * @param dateStr2
	 * @return
	 * @throws ParseException
	 */
	public static int compareDateD(String dateStr1,String dateStr2) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		Date date1=sdf.parse(dateStr1);
		Date date2=sdf.parse(dateStr2);
		int i=date1.compareTo(date2);
		return i;
	}
	/**
	 *
	 * 获取当天后一天的日期
	 *
	 * @return
	 */
	public static String getOneDayLaterDate(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new Date());
		ca.add(Calendar.DATE, 1);
		Date resultDate = ca.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return sdf.format(resultDate);

	}
	/**
	 * 字符串格式转日期
	 *
	 * @param source
	 * @throws ParseException
	 */
	public static Date formatDate(String source) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return simpleDateFormat.parse(source);
	}

	/**
	 * 日期格式转字符串
	 *
	 * @param date
	 * @throws ParseException
	 */
	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return simpleDateFormat.format(date);
	}

	/**
	 * 比较日期大小
	 *
	 * @param date1
	 * @param date2
	 * @return true表示date1小于等于date2 false表示date1大于date2
	 */
	public static boolean dateCompareBefore(Date date1, Date date2) {
		if (date1.before(date2) || date1.equals(date2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 比较日期大小
	 *
	 * @param date1
	 * @param date2
	 * @return true表示date1大于等于date2 false表示date1小于date2
	 */
	public static boolean dateCompareAfter(Date date1, Date date2) {
		if (date1.after(date2) || date1.equals(date2)) {
			return true;
		} else {
			return false;
		}
	}

	/**
     * 获取当天的时间（时分秒为00:00:00)
     * @return
     */
    public static Date getToday() {
        Date today = null;
        try {
            String todayStr = formatDate(new Date(), DEFAULT_DATE_FORMAT);
            today = formatDate(todayStr, DEFAULT_DATE_FORMAT);
        } catch (ParseException e) {
            LOGGER.error(DateUtils.class.getName() , e);
        }
        return today;
    }

	/**
	 * 获取当天的时间字符串（时分秒为00:00:00)
	 * @return
	 */
	public static String getTodayStr() {
		String todayStr  = null;
		try {
			  todayStr = formatDate(new Date(), DEFAULT_DATE_FORMAT);
		} catch (ParseException e) {
			LOGGER.error(DateUtils.class.getName() , e);
		}
		return todayStr;
	}

	/**
	 *
	 * @param startDate
	 * @param endDate
	 * @param startComp
	 * @param endComp
	 * @return
	 */
	public static boolean compareDate(Date startDate , Date endDate
				, Date startComp , Date endComp){

		if(startComp == null && endComp == null){
			return true ;
    	}

		if(startDate == null && endDate == null){
			return true ;
    	}

		if(startDate == null && endDate != null){
			if(startComp == null &&  endDate.getTime() < endComp.getTime()){
				return true ;
	    	}
	    	if(endComp == null && endDate.getTime() > startComp.getTime()){
	    		return true ;
	    	}
	    	if( startComp != null && endComp != null
	    			&& endDate.getTime() > startComp.getTime()
	    			&& endDate.getTime() < endComp.getTime() ){
	    		return true ;
	    	}
		}else if(startDate != null && endDate == null){
			if(startComp == null &&  startDate.getTime() < endComp.getTime()){
				return true ;
	    	}
	    	if(endComp == null && startDate.getTime() > startComp.getTime()){
	    		return true ;
	    	}
	    	if( startComp != null && endComp != null
	    			&& startDate.getTime() > startComp.getTime()
	    			&& startDate.getTime() < endComp.getTime() ){
	    		return true ;
	    	}
		}else{
			if(startComp == null &&  endDate.getTime() < endComp.getTime()){
				return true ;
	    	}else

	    	if(endComp == null && startDate.getTime() > startComp.getTime()){
	    		return true ;
	    	}

	    	if( startComp != null && endComp != null
	    			&& startDate.getTime() > startComp.getTime()
	    			&& endDate.getTime() < endComp.getTime() ){
	    		return true ;
	    	}
		}
		return false;
	}


	/**
	 * 字符串格式转日期
	 *
	 * @param source
	 * @param dateFormat
	 * @throws ParseException
	 */
	public static Date formatDate(String source, String dateFormat, Date defaultDate) {
		SimpleDateFormat simpleDateFormat = getDateFormat(dateFormat);
		Date rs;
		try {
			rs = simpleDateFormat.parse(source);
		} catch (ParseException e) {
			rs = defaultDate;
		}

		return rs;
	}

	/**
	 * 获取SimpleDateFormat
	 * @param pattern 日期格式
	 * @return SimpleDateFormat对象
	 * @throws RuntimeException 异常：非法日期格式
	 */
	private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		dateFormat.applyPattern(pattern);
		return dateFormat;
	}

	/**
	 *
	 * 当前系统日期转为字符串格式
	 *
	 * @param @return
	 * @param
	 * @return String
	 */
	public static String formatCurDate() {
		Date curDate = new Date();
		return getDateFormat(DEFAULT_DATE_FORMAT).format(curDate);
	}

	/**
	 *
	 * 当前系统时间转为字符串格式HHmm
	 *
	 * @param @return
	 * @param
	 * @return String
	 */
	public static String formatDefaultTime() {
		Date curDate = new Date();
		return getDateFormat(DEFAULT_TIME_FORMAT).format(curDate);
	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public static boolean isNotNullOr2999(Date date){

		Calendar calendar = Calendar.getInstance();
		if(null != date  ){
			calendar.setTime(date);
			return calendar.get(Calendar.YEAR) != 2999;
		}else {
			return false;
		}
	}



    /**
     * Change Util.{@link Date Date} To Sql.{@link java.sql.Date Date}
     * @param date Util.{@link Date Date}
     * @return Sql.{@link java.sql.Date Date}
     */
    public static java.sql.Date utilDate2SqlDate(Date date) {
        return utilDate2SqlDate(date, null);
    }

    /**
     * Change Util.{@link Date Date} To Sql.{@link java.sql.Date Date}<br/>
     * If Date is Null and Default-Date is Not Empty, will use Default-Date.
     * @param date Util.{@link Date Date}
     * @param defaultDate Util.{@link Date Date}
     * @return Sql.{@link java.sql.Date Date}
     */
    public static java.sql.Date utilDate2SqlDate(Date date, Date defaultDate) {
        if (null != date) {
            return new java.sql.Date(date.getTime());
        }
        if (null != defaultDate) {
            return new java.sql.Date(defaultDate.getTime());
        }
        return null;
    }

    /**
     * Format {@link String} {@link Date} to {@link Date} with Locale.US
     * @param source {@link String} Type {@link Date}
     * @param dateFormat {@link Date} Pattern
     * @return {@link Date}
     * @throws ParseException
     */
    public static Date formatUSDate(String source, String dateFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
        return simpleDateFormat.parse(source);
    }

	/**
	 * 日期类型 转换 时间类型
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static Calendar getCal(Date date) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 字符串日期类型  转换时间类型
	 * @param dateStr
	 * @return
	 * @throws Exception
	 */
	public static Calendar getCal(String dateStr) throws Exception {
		Date date = formatDate(dateStr , DEFAULT_DATE_FORMAT );
		return getCal(date);
	}






	/**
	 * 获取月份缩写
	 * @param month
	 * @return
	 */
	public static String getMonth(String month){
		String m = month;
		switch (month){
			case "01":
				m = "Jan";
				break;
			case "02":
				m = "Feb";
				break;
			case "03":
				m = "Mar";
				break;
			case "04":
				m = "Apr";
				break;
			case "05":
				m = "May";
				break;
			case "06":
				m = "June";
				break;
			case "07":
				m = "July";
				break;
			case "08":
				m = "Aug";
				break;
			case "09":
				m = "Sept";
				break;
			case "10":
				m = "Oct";
				break;
			case "11":
				m = "Nov";
				break;
			case "12":
				m = "Dec";
				break;
			default:
				m=month;
		}
		return m;
	}


	/**
	 * 日期加一天
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date AddDay(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, n);
		return c.getTime();
	}

	/**
	 * 日期加一月
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date AddMonth(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, n);
		return c.getTime();
	}

	/**
	 * 日期加一年
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date AddYear(Date date,int n){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, n);
		return c.getTime();
	}


}
