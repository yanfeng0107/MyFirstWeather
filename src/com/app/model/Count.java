package com.app.model;

public class Count {
	private String CountId;
	private String CountName;
	private String CityId;
	
	public Count(String CountId,String CountName,String CityId)
	{
		this.CountId=CountId;
		this.CountName=CountName;
		this.CityId=CityId;
	}
	
	public void SetCountId(String CountId)
	{
		this.CountId=CountId;
	}
	
	public String GetCountId()
	{
		return this.CountId;
	}
	
	public void SetCountName(String CountName)
	{
		this.CountName=CountName;
	}
	
	public String GetCountName()
	{
		return this.CountName;
	}
	
	
	public void SetCityId(String CityId)
	{
		this.CityId=CityId;
	}
	
	public String GetCityId()
	{
		return this.CityId;
	}
}
