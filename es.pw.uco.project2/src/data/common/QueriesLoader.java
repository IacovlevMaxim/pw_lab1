package data.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class QueriesLoader {
	private static Properties properties;

	/**
	 * Loads the files.properties file
	 */
	public QueriesLoader() {
		properties = new Properties();
		InputStream input;
		try {
			input =QueriesLoader.class.getClassLoader().getResourceAsStream("sql.properties");  // SINCE IT IS INSIDE /src WE USE GETRESOURCEASSTREAM
			properties.load(input);
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
