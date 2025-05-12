package com.hexaware.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	//Two static variables created 
		private static final String File_Path ="resources/db.properties";
		private static Properties properties;
	
		static {
			
			// try - catch -- exception handling   
			
			try {
				
				// For reading the file 
				FileInputStream fis = new FileInputStream(File_Path);
				properties = new Properties();
				// to load the file which is property file so we are loading with the help of object of Properties class
				properties.load(fis);
				
			}
			catch(IOException e)
			{
				e.printStackTrace();  // jvm 
			}
			
    }
    
public static String get(String key) {
     
    	return properties.getProperty(key);
    	}

}


