package com.app.httpurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class httpurl {
	static public InputStream sendHttpGetRequest(String strurl)
	{
		HttpURLConnection connection=null;
		URL url;
		try {
			url = new URL(strurl);
			connection=(HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(8000);
			connection.setReadTimeout(8000);
			return connection.getInputStream();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
