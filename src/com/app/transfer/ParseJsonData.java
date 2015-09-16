package com.app.transfer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.app.model.WeatherInfo;

import android.R.integer;

public class ParseJsonData {
	public static void parseJSON2WeatherInfo(String jsondata,WeatherInfo winfo)
	{
		try {
			JSONObject wobj=new JSONObject(jsondata);
			String weather=wobj.getString("weatherinfo");
			
			JSONObject obj=new JSONObject(weather);
			winfo.AreaId=obj.getString("cityid");
			winfo.AreaName=obj.getString("city");
			winfo.Date=obj.getString("date_y");
			winfo.WeekDay=obj.getString("week");
			
			winfo.temp1=obj.getString("temp1");
			winfo.temp2=obj.getString("temp2");
			winfo.temp3=obj.getString("temp3");
			winfo.temp4=obj.getString("temp4");
			winfo.temp5=obj.getString("temp5");
			winfo.temp6=obj.getString("temp6");
			
			winfo.weather1=obj.getString("weather1");
			winfo.weather2=obj.getString("weather2");
			winfo.weather3=obj.getString("weather3");
			winfo.weather4=obj.getString("weather4");
			winfo.weather5=obj.getString("weather5");
			winfo.weather6=obj.getString("weather6");
			
			winfo.wind1=obj.getString("wind1");
			winfo.wind2=obj.getString("wind2");
			winfo.wind3=obj.getString("wind3");
			winfo.wind4=obj.getString("wind4");
			winfo.wind5=obj.getString("wind5");
			winfo.wind6=obj.getString("wind6");
			
			winfo.index=obj.getString("index");
			winfo.index_d=obj.getString("index_d");
			

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
