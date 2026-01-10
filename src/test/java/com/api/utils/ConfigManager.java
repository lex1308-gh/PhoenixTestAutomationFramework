// will contain main method

package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	// Program to read .properties file
	public static String getProperty(String key) throws IOException {

		Properties prop = new Properties();
		File configFile = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\config\\config.properties");
		FileReader fileReader = new FileReader(configFile);
		prop.load(fileReader);

		return prop.getProperty("BASE_URI");
	}

}
