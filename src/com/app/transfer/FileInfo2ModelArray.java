package com.app.transfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.app.model.Province;

public class FileInfo2ModelArray {

	private String ProvinceFilePath;
	
	public void SetProvinceFilePath(String ProvinceFilePath)
	{
		this.ProvinceFilePath=ProvinceFilePath;
	}
	
	public String GetProvinceFilePath()
	{
		return this.ProvinceFilePath;
	}
	
	public ArrayList GetProvincesFromFile() throws IOException
	{
		ArrayList ProvinceList=new ArrayList();
		FileInputStream infile = new FileInputStream(this.ProvinceFilePath);
		int filelen = infile.available();
		byte[] b = new byte[filelen];
		infile.read(b, 0, filelen);
		infile.close();
		String str=new String(b);
		String values[]=str.split(";");
		for(int i=0;i<values.length;i++)
		{
			String Provalues[]=values[i].split(",");
			Province p=new Province(Provalues[0],Provalues[1],true);
			ProvinceList.add(p);
		}
		
		return ProvinceList;
	}
}
