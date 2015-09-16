package com.app.db;

import com.app.model.City;
import com.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class WeatherDB extends SQLiteOpenHelper{
	
	private Context mContext;
	
	public static final String DBname="MyDb";
	
	public static final String book="create table book ("
			+"id integer primary key autoincrement, "
			+"author text)";
	
	public static final String ProvinceTable="create table ProvinceTable ("
			+ "id integer primary key autoincrement, "
			+ "ProvinceId text, "
			+ "ProvinceName text)";
	
	public static final String CityTable="create table CityTable ("
			+ "id integer primary key autoincrement, "
			+ "CityId text, "
			+ "CityName text, "
			+ "ProvinceId text)";
	
	public static final String CountTable="create table CountTable ("
			+ "id integer primary key autoincrement, "
			+ "CountId text, "
			+ "CountNmae text, "
			+ "CityId text, "
			+ "ProvinceId text)";
	

	public WeatherDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		this.mContext=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(this.ProvinceTable);
		db.execSQL(this.CityTable);
		db.execSQL(this.CountTable);
		this.InitDB(db);
		Toast.makeText(mContext, "Create Database", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	private void InitDB(SQLiteDatabase db)
	{
		//ContentValues values=new ContentValues();
		//values.put();
		this.InsertBj(db);
		this.InsertPro(db);
	}
	
	public void InsertProvince(Province p,SQLiteDatabase db)
	{
		ContentValues value=new ContentValues();
		value.put("ProvinceId", p.GetProvinceId());
		value.put("ProvinceName", p.GetProvinceName());
		db.insert("ProvinceTable",null,value);
	}
	
	public void InsertCity(City c,SQLiteDatabase db)
	{
		ContentValues value=new ContentValues();
		value.put("CityId", c.GetCityId());
		value.put("CityName", c.GetCityName());
		value.put("ProvinceId", c.GetProvinceId());
		db.insert("CityTable", null, value);
	}
	
	public void InsertBj(SQLiteDatabase db)
	{
		Province bj=new Province("北京","101010100",false);
		this.InsertProvince(bj, db);
		
		City c1=new City("101010200","海淀","101010100");
		this.InsertCity(c1, db);
		City c2=new City("101010300","朝阳","101010100");
		this.InsertCity(c2, db);
		City c3=new City("101010400","顺义","101010100");
		this.InsertCity(c3, db);
		City c4=new City("101010500","怀柔","101010100");
		this.InsertCity(c4, db);
		City c5=new City("101010600","通州","101010100");
		this.InsertCity(c5, db);
		City c6=new City("101010700","昌平","101010100");
		this.InsertCity(c6, db);
		City c7=new City("101010800","延庆","101010100");
		this.InsertCity(c7, db);
		City c8=new City("101010900","丰台","101010100");
		this.InsertCity(c8, db);
		City c9=new City("101011000","石景山","101010100");
		this.InsertCity(c9, db);
		City c10=new City("101011100","大兴","101010100");
		this.InsertCity(c10, db);
		City c11=new City("101011200","房山","101010100");
		this.InsertCity(c11, db);
		City c12=new City("101011300","密云","101010100");
		this.InsertCity(c12, db);
		City c13=new City("101011400","门头沟","101010100");
		this.InsertCity(c13, db);
		City c14=new City("101011500","平谷","101010100");
		this.InsertCity(c14, db);
		
	}
	
	public void InsertPro(SQLiteDatabase db)
	{
		Province temp=new Province("山西","101100101",false);
		this.InsertProvince(temp, db);
		temp=new Province("上海","101020100",false);
		this.InsertProvince(temp, db);
		temp=new Province("天津","101030100",false);
		this.InsertProvince(temp, db);
		temp=new Province("重庆","101040100",false);
		this.InsertProvince(temp, db);
		temp=new Province("黑龙江","101050101",false);
		this.InsertProvince(temp, db);
		temp=new Province("吉林","101060101",false);
		this.InsertProvince(temp, db);
		temp=new Province("辽宁","101070101",false);
		this.InsertProvince(temp, db);
		temp=new Province("内蒙古","101080101",false);
		this.InsertProvince(temp, db);
		temp=new Province("河北","101090101",false);
		this.InsertProvince(temp, db);
		
	}
	
}
