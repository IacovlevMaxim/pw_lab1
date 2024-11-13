package data.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	
	private static Properties properties;

	/**
	 * Loads the files.properties file
	 */
	public ConfigLoader() {
		properties = new Properties();
		InputStream input;
		try {
            input = new FileInputStream("config.properties");
			properties.load(input);
			input.close();
		} catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	/**
	 * Gets a property
	 * @param key The property to get
	 * @return The equivalent value in the files.properties file
	 */
	public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
