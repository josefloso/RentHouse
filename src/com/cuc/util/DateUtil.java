package com.cuc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static DateUtil instance = null;

	public static synchronized DateUtil getInstance() {
		if (instance == null) {
			instance = new DateUtil();
		}
		return instance;
	}
	
	public boolean isValidDate(String sdate,String sd,String sp){
		SimpleDateFormat sdf=new SimpleDateFormat(sd);
		try {
			Date date=sdf.parse(sdate);
			int year,month,day,y,m,d;
			String s[]=sdate.split(sp);
			year=Integer.parseInt(s[0]);
			month=Integer.parseInt(s[1]);
			day=Integer.parseInt(s[2]);
			Calendar cal =Calendar.getInstance();
			cal.setTime(date);
			y=cal.get(Calendar.YEAR);
			m=cal.get(Calendar.MONTH)+1;
			d=cal.get(Calendar.DAY_OF_MONTH);
			if(year!=y||month!=m||day!=d){
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public Date getDate(String sdate,String sd){
		SimpleDateFormat sdf=new SimpleDateFormat(sd);
		Date date;
		try {
			date = sdf.parse(sdate);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}
	public String getStringDate(Date date,String sd){
		SimpleDateFormat sdf=new SimpleDateFormat(sd);
		return sdf.format(date);
	}
}
