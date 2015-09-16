package com.app.model;

public class Province {

	private String ProvinceName;
	private String ProvinceId;
	private boolean IsHaveNextLevel;
	
	public Province(String ProvinceName,String ProvinceId,boolean IsHaveNextLevel)
	{
		this.ProvinceName=ProvinceName;
		this.ProvinceId=ProvinceId;
		this.IsHaveNextLevel=IsHaveNextLevel;
	}
	
	public void SetProvinceName(String ProvinceName)
	{
		this.ProvinceName=ProvinceName;
	}
	
	public String GetProvinceName()
	{
		return this.ProvinceName;
	}
	
	public  void SetProvinceId(String ProvinceId)
	{
		this.ProvinceId=ProvinceId;
	}
	
	public String GetProvinceId()
	{
		return this.ProvinceId;
	}
	
	public boolean IsHaveNextLevel()
	{
		return this.IsHaveNextLevel;
	}
}
