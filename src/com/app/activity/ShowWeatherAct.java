package com.app.activity;


import java.security.spec.MGF1ParameterSpec;

import com.app.model.WeatherInfo;
import com.app.transfer.WeekdayAndInt;
import com.myweatherfirst.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowWeatherAct extends Activity {
	
	private TextView text_date;
	private TextView text_weekday;
	private TextView text_area;
	private TextView text_temperature;
	private TextView text_wind;
	private TextView text_index;
	private TextView text_index_d;
	private ImageView img;
	
	
	private TextView text_temp1_weekday;
	private TextView text_temp1_temperature;
	private TextView text_temp1_index;
	private TextView text_temp1_wind;
	private ImageView img1;
	
	private TextView text_temp2_weekday;
	private TextView text_temp2_temperature;
	private TextView text_temp2_index;
	private TextView text_temp2_wind;
	private ImageView img2;
	
	private TextView text_temp3_weekday;
	private TextView text_temp3_temperature;
	private TextView text_temp3_index;
	private TextView text_temp3_wind;
	private ImageView img3;
	
	private TextView text_temp4_weekday;
	private TextView text_temp4_temperature;
	private TextView text_temp4_index;
	private TextView text_temp4_wind;
	private ImageView img4;
	
	
	
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		this.LoadView(savedInstanceState);
		Intent intent=getIntent();
		Bundle bundle=intent.getBundleExtra("bundle");
		if(null==bundle)
		{
			return;
		}
		WeatherInfo winfo=(WeatherInfo) bundle.getSerializable("weatherinfo");
		this.SetViewValue(winfo);
		
		/*
		setContentView(R.layout.weather_show);
		text_date=(TextView)findViewById(R.id.text_date);
		text_weekday=(TextView)findViewById(R.id.text_weekday);
		text_area=(TextView)findViewById(R.id.text_area);
		text_temperature=(TextView)findViewById(R.id.text_temperature);
		text_wind=(TextView)findViewById(R.id.text_wind);
		text_index=(TextView)findViewById(R.id.text_index);
		text_index_d=(TextView)findViewById(R.id.text_index_d);
		
		
		Intent intent=getIntent();
		Bundle bundle=intent.getBundleExtra("bundle");
		if(null==bundle)
		{
			return;
		}
		WeatherInfo winfo=(WeatherInfo) bundle.getSerializable("weatherinfo");
		
		text_date.setText(winfo.Date);
		text_weekday.setText(winfo.WeekDay);
		text_temperature.setText(winfo.temp1);
		text_area.setText(winfo.AreaName);
		text_wind.setText(winfo.wind1);
		text_index.setText(winfo.index);
		text_index_d.setText(winfo.index_d);
		*/
		return;
	}
	
	private void LoadView(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.weather_layout);
		
		text_date=(TextView)findViewById(R.id.text_date);
		//text_weekday=(TextView)findViewById(R.id.text_weekday);
		text_area=(TextView)findViewById(R.id.text_area);
		text_temperature=(TextView)findViewById(R.id.text_temperature);
		text_wind=(TextView)findViewById(R.id.text_wind);
		text_index=(TextView)findViewById(R.id.text_index);
		text_index_d=(TextView)findViewById(R.id.text_index_d);
		img=(ImageView)findViewById(R.id.img);
		/*
		View v=findViewById(R.layout.weather_otherdat) ;
		if(null==v)
		{
			return;
		}
		*/
		text_temp1_index=(TextView)findViewById(R.id.text_index1);
		text_temp1_temperature=(TextView)findViewById(R.id.text_temperature1);
		text_temp1_weekday=(TextView)findViewById(R.id.text_weekday1);
		text_temp1_wind=(TextView)findViewById(R.id.text_wind1);
		img1=(ImageView)findViewById(R.id.img_1);
		
		text_temp2_index=(TextView)findViewById(R.id.text_index2);
		text_temp2_temperature=(TextView)findViewById(R.id.text_temperature2);
		text_temp2_weekday=(TextView)findViewById(R.id.text_weekday2);
		text_temp2_wind=(TextView)findViewById(R.id.text_wind2);
		img2=(ImageView)findViewById(R.id.img_2);
		
		text_temp3_index=(TextView)findViewById(R.id.text_index3);
		text_temp3_temperature=(TextView)findViewById(R.id.text_temperature3);
		text_temp3_weekday=(TextView)findViewById(R.id.text_weekday3);
		text_temp3_wind=(TextView)findViewById(R.id.text_wind3);
		img3=(ImageView)findViewById(R.id.img_3);
		
		text_temp4_index=(TextView)findViewById(R.id.text_index4);
		text_temp4_temperature=(TextView)findViewById(R.id.text_temperature4);
		text_temp4_weekday=(TextView)findViewById(R.id.text_weekday4);
		text_temp4_wind=(TextView)findViewById(R.id.text_wind4);
		img4=(ImageView)findViewById(R.id.img_4);
		
		
		/*
		text_temp2_weekday;
		text_temp2_temperature;
		text_temp2_index;
		text_temp2_wind;
		*/
		
		//text_temp2_weekday=(TextView)findViewById(R.id.)
	}
	
	private void SetViewValue(WeatherInfo winfo)
	{
		text_area.setText(winfo.AreaName);
		String strdate="";
		strdate=String.format("%s  %s", winfo.Date,winfo.WeekDay);
		text_date.setText(strdate);
		text_index.setText(winfo.weather1);
		text_index_d.setText(winfo.index_d);
		text_wind.setText(winfo.wind1);
		//text_weekday.setText(winfo.WeekDay);
		text_temperature.setText(winfo.temp1);
		SetImgByWeather(img,winfo.weather1);
		
		
		text_temp1_index.setText(winfo.weather2);
		text_temp1_temperature.setText(winfo.temp2);
		text_temp1_weekday.setText(
				WeekdayAndInt.IntToWeekday((WeekdayAndInt.WeekdayToInt(winfo.WeekDay)+1)%7));
		text_temp1_wind.setText(winfo.wind2);
		SetImgByWeather(img1,winfo.weather2);
		
		text_temp2_index.setText(winfo.weather3);
		text_temp2_temperature.setText(winfo.temp3);
		text_temp2_weekday.setText(
				WeekdayAndInt.IntToWeekday((WeekdayAndInt.WeekdayToInt(winfo.WeekDay)+2)%7));
		text_temp2_wind.setText(winfo.wind3);
		SetImgByWeather(img2,winfo.weather3);
		
		text_temp3_index.setText(winfo.weather4);
		text_temp3_temperature.setText(winfo.temp4);
		text_temp3_weekday.setText(
				WeekdayAndInt.IntToWeekday((WeekdayAndInt.WeekdayToInt(winfo.WeekDay)+3)%7));
		text_temp3_wind.setText(winfo.wind4);
		SetImgByWeather(img3,winfo.weather4);
		
		text_temp4_index.setText(winfo.weather5);
		text_temp4_temperature.setText(winfo.temp5);
		text_temp4_weekday.setText(
				WeekdayAndInt.IntToWeekday((WeekdayAndInt.WeekdayToInt(winfo.WeekDay)+4)%7));
		text_temp4_wind.setText(winfo.wind5);
		SetImgByWeather(img4,winfo.weather5);
		
	}
	
	private void SetImgByWeather(ImageView img,String weather)
	{
		final AnimationDrawable ad;
		if(weather.equalsIgnoreCase("Çç")||weather.equalsIgnoreCase("Òõ×ªÇç"))
		{
			img.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
		}
		else if(weather.equalsIgnoreCase("¶àÔÆ×ªÇç")||weather.equalsIgnoreCase("Çç×ª¶àÔÆ")
				||weather.equalsIgnoreCase("Òõ"))
		{
			img.setImageDrawable(getResources().getDrawable(R.drawable.cloudy));
		}
		else {
			//img.setImageDrawable(getResources().getDrawable(R.drawable.rain));
			img.setImageDrawable(getResources().getDrawable(R.drawable.rain_animation));
			ad=(AnimationDrawable)img.getDrawable();
			img.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ad.start();
				}
			});
		}
	}
}
