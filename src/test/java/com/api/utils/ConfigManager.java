// will contain main method

package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static Properties prop = new Properties();
	private static String path = "config/config.properties";
	
	// Static block will execute only once
	static {

		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(path);

		if(input==null) {
			throw new RuntimeException("file not found at the given path" + path);
		}
		
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {

		return prop.getProperty("BASE_URI");
	}

}
