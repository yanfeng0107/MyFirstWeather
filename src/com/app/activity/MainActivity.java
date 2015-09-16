package com.app.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;

import com.app.db.WeatherDB;
import com.app.httpurl.httpurl;
import com.app.model.City;
import com.app.model.Province;
import com.app.model.WeatherInfo;
import com.app.transfer.EncodeDemo;
import com.app.transfer.ParseJsonData;
import com.myweatherfirst.R;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int PROVINCE_LEVEL=0;
	private final int CITY_LEVEL=1;
	private final int COUNT_LEVEL=2;
	private int CurrentLevel=0;
	
	
	private String CurrentProvinceId="";
	private String CurrentCityId="";
	private String CurrentCountId="";
	
	private WeatherDB weatherdb;
	//private Province CurrentPro=new Province("","",false);
	private List<Province> Provincelist=new ArrayList<Province>();
	private List<City> Citylist=new ArrayList<City>();
	
	private List<String> showlist=new ArrayList<String>();
	private ArrayAdapter<String> adapter_show;
	private ListView listview;
	//private Button btn; 
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_layout);
		weatherdb=new WeatherDB(this,WeatherDB.DBname,null,1);
		
		this.InitDB();
		db=weatherdb.getWritableDatabase();
		this.CurrentLevel=this.PROVINCE_LEVEL;
		
		adapter_show=new ArrayAdapter<String>(
				MainActivity.this,android.R.layout.simple_list_item_1,this.showlist);
		
		listview=(ListView)findViewById(R.id.list_view);
		listview.setAdapter(adapter_show);
		listview.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String ClickName=showlist.get(position);
				String AreaId=QueryId(CurrentLevel, ClickName);
				RecordId(CurrentLevel, AreaId);
				int NextLevel=(CurrentLevel+1)%3;
				
				ArrayList<String> tlist=QueryList(NextLevel, AreaId);
				if(tlist.isEmpty())
				{
					
					WeatherInfo winfo=null;
					winfo=getAreaWeatherInfo(AreaId);
					if(null!=winfo)
					{
						StartShowWeatherAct(winfo);
						//Log.d("winfo",winfo.Date);
						//Log.d("winfo",winfo.AreaName);
						//Log.d("winfo",winfo.temp1);
					}
					else {
						Toast.makeText(MainActivity.this, ClickName, Toast.LENGTH_LONG).show();
					}
				}
				else {
					Log.d("isEmpty", "no Empty");
					/*
					showlist.clear();
					for(int i=0;i<tlist.size();i++)
					{
						Log.d("tlist", tlist.get(i));
						showlist.add(tlist.get(i));
					}
					adapter_show.notifyDataSetChanged();
					listview.setSelection(0);
					*/
					UpdateList(tlist);
					CurrentLevel=NextLevel;
				}
			}
		}
		
		);
		
		/*btn=(Button)findViewById(R.id.create_database);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TestQueryDB();
			}
		});*/
	}
	
	public void onBackPressed()
	{
		ArrayList<String> list=null;
		switch (this.CurrentLevel) {
		case PROVINCE_LEVEL:
			finish();
			break;
		case CITY_LEVEL:
			list=this.QueryList(PROVINCE_LEVEL, CurrentProvinceId);
			break;
		case COUNT_LEVEL:
			list=this.QueryList(CITY_LEVEL, CurrentCityId);
			break;
		default:
			break;
		}
		CurrentLevel--;
		if(null!=list)
		{
			UpdateList(list);
		}
	}
	
	private void RecordId(int level,String id)
	{
		switch (level) {
		case PROVINCE_LEVEL:
			CurrentProvinceId=id;
			break;
		case CITY_LEVEL:
			CurrentCityId=id;
			break;
		case COUNT_LEVEL:
			CurrentCountId=id;
			break;
		default:
			break;
		}
	}
	
	private void UpdateList(ArrayList<String> list)
	{
		showlist.clear();
		for(int i=0;i<list.size();i++)
		{
			Log.d("tlist", list.get(i));
			showlist.add(list.get(i));
		}
		adapter_show.notifyDataSetChanged();
		listview.setSelection(0);
	}
	
	public void InitDB()
	{
		
		SQLiteDatabase db=weatherdb.getWritableDatabase();
		
		Cursor cursor=db.query("ProvinceTable", null, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			do{
				String id=cursor.getString(cursor.getColumnIndex("ProvinceId"));
				String name=cursor.getString(cursor.getColumnIndex("ProvinceName"));
				//Log.d("query","id"+id);
				//Log.d("query","name"+name);
				Province p=new Province(name,id,false);
				this.Provincelist.add(p);
				this.showlist.add(name);
			}while(cursor.moveToNext());
			
		}
		
		cursor=db.query("CityTable", null, null, null,null,null,null);
		if(cursor.moveToFirst())
		{
			do
			{
				String id=cursor.getString(cursor.getColumnIndex("CityId"));
				String name=cursor.getString(cursor.getColumnIndex("CityName"));
				String Pid=cursor.getString(cursor.getColumnIndex("ProvinceId"));
				//Log.d("query","CityId:"+id+"\n");
				//Log.d("query","CityText:"+text+"\n");
				//Log.d("query","ProvinceId:"+Pid+"\n");
				City c=new City(id,name,Pid);
				this.Citylist.add(c);
			}while(cursor.moveToNext());
		}
	}
	
	
	public WeatherInfo getAreaWeatherInfo(String AreaId)
	{
		WeatherInfo winfo=new WeatherInfo();
		String res=null;
		try {

			String url="http://m.weather.com.cn/atad/"+AreaId+".html";
			InputStream in=httpurl.sendHttpGetRequest(url);
			BufferedReader reader=new BufferedReader(new InputStreamReader(in));
			StringBuilder response=new StringBuilder();
			String line;
			while((line=reader.readLine())!=null)
			{
				response.append(line);
			}
			res=response.toString();
			Log.d("http get",res);
			
			
			ParseJsonData.parseJSON2WeatherInfo(res, winfo);
			return winfo;
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return winfo;
	}
	
	private ArrayList<String> QueryList(int TargetLevel,String AreaId)
	{
		Cursor cursor=null;
		ArrayList<String> list=new ArrayList<String>();
		list.clear();
		
		//SQLiteDatabase db=weatherdb.getWritableDatabase();
		switch (TargetLevel) {
		case PROVINCE_LEVEL:
			cursor=db.query("ProvinceTable", null, null, null, null, null, null);
			break;
		case CITY_LEVEL:
			cursor=db.query("CityTable", null, "ProvinceId=?", new String[]{AreaId}, null, null, null);
			break;
		case COUNT_LEVEL:
			cursor=db.query("CountTable", null, "CityId=?", new String[]{AreaId}, null, null, null);
			break;
		default:
			break;
		}
		
		if(cursor.moveToFirst())
		{
			do{
				String name="";
				switch (TargetLevel) {
				case PROVINCE_LEVEL:
					name=cursor.getString(cursor.getColumnIndex("ProvinceName"));
					break;
				case CITY_LEVEL:
					name=cursor.getString(cursor.getColumnIndex("CityName"));
					break;
				case COUNT_LEVEL:
					name=cursor.getString(cursor.getColumnIndex("CountName"));
					break;
				default:
					break;
				}
				
				list.add(name);
			}while(cursor.moveToNext());
			
		}
		return list;
	}
	
	private String QueryId(int level,String name)
	{
		Log.d("queryid",name);
		//return "101010100";
		
		Cursor cursor=null;
		String id=null;
		switch (level) {
		case PROVINCE_LEVEL:
			cursor=db.query("ProvinceTable", null, "ProvinceName=?", new String[]{name}, null, null, null);
			break;
		case CITY_LEVEL:
			cursor=db.query("CityTable", null, "CityName=?", new String[]{name}, null, null, null);
			break;
		case COUNT_LEVEL:
			cursor=db.query("CountTable", null, "CountName=?", new String[]{name}, null, null, null);
			break;
		default:
			break;
		}
		if(cursor.moveToFirst())
		{
			switch (level) {
			case PROVINCE_LEVEL:
				id=cursor.getString(cursor.getColumnIndex("ProvinceId"));
				break;
			case CITY_LEVEL:
				id=cursor.getString(cursor.getColumnIndex("CityId"));
				break;
			case COUNT_LEVEL:
				id=cursor.getString(cursor.getColumnIndex("CountId"));
				break;
			default:
				break;
			}
		}
		return id;
	}
	
	
	
	
	private void TestQueryDB()
	{
		Cursor cursor=null;
		ArrayList<String> list=new ArrayList<String>();
		list.clear();
		Log.d("Test queryDb", "Start");
		//cursor=db.query("CityTable", null, "ProvinceId=?", new String[]{"101010100"}, null, null, null);
		//cursor=db.query("CityTable", null, null, null, null, null, null);
		cursor=db.query("ProvinceTable", null, "ProvinceName=?", new String[]{"±±¾©"}, null, null, null);
		if(cursor.moveToFirst())
		{
			do{
				String name="";
				
				//name=cursor.getString(cursor.getColumnIndex("CityName"));
				name=cursor.getString(cursor.getColumnIndex("ProvinceId"));
				Log.d("test query ProvinceId", name);
				list.add(name);
			}while(cursor.moveToNext());
			
		}
		return;
	}
	
	private void StartShowWeatherAct(WeatherInfo winfo)
	{
		Intent intent=new Intent(MainActivity.this, ShowWeatherAct.class);
		//intent.setClass(MainActivity.this, ShowWeatherAct.class);
		Bundle bundle=new Bundle();
		bundle.putSerializable("weatherinfo", winfo);
		intent.putExtra("bundle", bundle);
		if(null!=intent)
		{
			startActivity(intent);
		}
		//Log.d("tag", "StartAct");
		return;
	}
	
	private void EverTest()
	{
		/*
		SimpleDateFormat dataformat=new SimpleDateFormat("yyyyMMddHHmm");
		String strtime=dataformat.format(new Date());
		Log.d("time", strtime);
	
		//String data="http://open.weather.com.cn/data/?areaid=101010100&type=index_f&date=xxxxxxxxx&appid=xxxxxxx";
		String data=null;
		String str_areaid="101010100";
		String str_type="index_f";
		String str_appid="c35e1f396d660cac";
		data=String.format("http://open.weather.com.cn/data/?areaid=%s&type=%s&date=%s&appid=%s",
				str_areaid,str_type,strtime,str_appid);
		
		String key="3cbdd4_SmartWeatherAPI_8fd0353";
		String str_key=EncodeDemo.standardURLEncoder(data, key);
		Log.d("Encode key", str_key);
		
		String str_en_key="";
		str_en_key=java.net.URLEncoder.encode(str_key);
		String strurl=null;
		strurl=String.format("%s&key=%s",data,str_en_key);
		Log.d("strurl", strurl);
		Log.d("tag","--------");
		try {
			
		
		HttpClient hclient=new DefaultHttpClient();
		
		HttpGet hget=new HttpGet(strurl);
		HttpResponse hresponse=hclient.execute(hget);
		if(hresponse.getStatusLine().getStatusCode()==200)
		{
			HttpEntity entity=hresponse.getEntity();
			try {
				String response=EntityUtils.toString(entity,"utf-8");
				Log.d("json", response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		} catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
		}*/
		
	}
}
