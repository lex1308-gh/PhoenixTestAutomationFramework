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
	private static String myEnv;

	// Static block will execute only once
	static {

		myEnv = System.getProperty("env", "qa");
		myEnv = myEnv.toLowerCase().trim();

		System.out.println("");
		System.out.println("Running test in environment --------> " + myEnv);
		System.out.println("");

		switch (myEnv) {

		case "dev" -> path = "config/config.dev.properties";

		case "qa" -> path = "config/config.qa.properties";

		case "uat" -> path = "config/config.uat.properties";

		default -> path = "config/config.properties";

		}

		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			throw new RuntimeException("file not found at the given path" + path);
		}

		try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getProperty(String key) {

		return prop.getProperty("BASE_URI");
	}

}
