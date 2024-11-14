package data.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class in charge of loading the config.properties file
 */
public class ConfigLoader {
	
	private static Properties properties;

	/**
	 * Loads the config.properties file
	 */
	public ConfigLoader() {
		properties = new Properties();
		InputStream input;
		try {
            input = new FileInputStream("config.properties"); // SINCE IT IS NOT INSIDE /src WE USE FILEINPUTSTREAM
			properties.load(input);
			input.close();
		} catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	/**
	 * Gets a property
	 * @param key The property to get
	 * @return The equivalent value in the config.properties file
	 */
	public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
