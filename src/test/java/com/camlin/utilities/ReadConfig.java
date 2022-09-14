package com.camlin.utilities;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties property;

	public ReadConfig() {

		File src = new File("./Configuration/config.properties");

		try {

			FileInputStream file = new FileInputStream(src);
			property = new Properties();
			property.load(file);

		}
		catch (Exception e) {
			System.out.println("Excetion is" + e.getMessage());
		}
	}
	
	public String getChromePath() {

		String chromePath = property.getProperty("chromepath");
		return chromePath;
	}
	
	public String getChromeDriver() {

		String chromeDriver = property.getProperty("chromedriver");
		return chromeDriver;
	}

	public String getBaseURL() {

		String url = property.getProperty("baseurl");
		return url;
	}	
	

	public String getUserName() {

		String userName = property.getProperty("username");
		return userName;
	}

	public String getPassword() {

		String password = property.getProperty("password");
		return password;
	}
	
	
}
