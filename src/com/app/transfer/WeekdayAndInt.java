package com.app.transfer;

public class WeekdayAndInt {
	public static int WeekdayToInt(String weekday) 
 	{
	
		if(weekday.equalsIgnoreCase("星期一")||weekday.equalsIgnoreCase("周一"))
		{
			return 1;
		}
		if(weekday.equalsIgnoreCase("星期二")||weekday.equalsIgnoreCase("周二"))
		{
			return 2;
		}
		if(weekday.equalsIgnoreCase("星期三")||weekday.equalsIgnoreCase("周三"))
		{
			return 3;
		}
		if(weekday.equalsIgnoreCase("星期四")||weekday.equalsIgnoreCase("周四"))
		{
			return 4;
		}
		if(weekday.equalsIgnoreCase("星期五")||weekday.equalsIgnoreCase("周五"))
		{
			return 5;
		}
		if(weekday.equalsIgnoreCase("星期六")||weekday.equalsIgnoreCase("周六"))
		{
			return 6;
		}
		if(weekday.equalsIgnoreCase("星期日")||weekday.equalsIgnoreCase("周日")
				||weekday.equalsIgnoreCase("星期天")||weekday.equalsIgnoreCase("周天"))
		{
			return 0;
		}
		return 0;
 	}
	
	public static String IntToWeekday(int day)
	{
		switch (day) {
		case 1:
			return "周一";
		case 2:
			return "周二";
		case 3:
			return "周三";
		case 4:
			return "周四";
		
		case 5:
			return "周五";
		case 6:
			return "周六";
		case 0:
			return "周日";
		default:
			break;
		}
		return "Weekday Error";
	}
	
}
