package com.app.transfer;

public class WeekdayAndInt {
	public static int WeekdayToInt(String weekday) 
 	{
	
		if(weekday.equalsIgnoreCase("����һ")||weekday.equalsIgnoreCase("��һ"))
		{
			return 1;
		}
		if(weekday.equalsIgnoreCase("���ڶ�")||weekday.equalsIgnoreCase("�ܶ�"))
		{
			return 2;
		}
		if(weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����"))
		{
			return 3;
		}
		if(weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����"))
		{
			return 4;
		}
		if(weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����"))
		{
			return 5;
		}
		if(weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����"))
		{
			return 6;
		}
		if(weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����")
				||weekday.equalsIgnoreCase("������")||weekday.equalsIgnoreCase("����"))
		{
			return 0;
		}
		return 0;
 	}
	
	public static String IntToWeekday(int day)
	{
		switch (day) {
		case 1:
			return "��һ";
		case 2:
			return "�ܶ�";
		case 3:
			return "����";
		case 4:
			return "����";
		
		case 5:
			return "����";
		case 6:
			return "����";
		case 0:
			return "����";
		default:
			break;
		}
		return "Weekday Error";
	}
	
}
