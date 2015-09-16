package com.app.model;

public class City {
	private String CityId;
	private String CityName;
	private String ProvinceId;
	
	public City(String CityId,String CityName,String ProvinceId)
	{
		this.CityId=CityId;
		this.CityName=CityName;
		this.ProvinceId=ProvinceId;
	}
	
	public void SetCityId(String CityId)
	{
		this.CityId=CityId;
	}
	
	public String GetCityId()
	{
		return this.CityId;
	}
	
	public void SetCityText(String CityName)
	{
		this.CityName=CityName;
	}
	
	public String GetCityName()
	{
		return this.CityName;
	}
	
	public void SetProvinceId(String ProvinceId)
	{
		this.ProvinceId=ProvinceId;
	}
	
	public String GetProvinceId()
	{
		return this.ProvinceId;
	}
	
}
