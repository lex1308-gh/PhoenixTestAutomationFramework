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

	// Static block will execute only once
	static {

		InputStream input = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("config/config.properties");

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
