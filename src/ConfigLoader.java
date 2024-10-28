import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private static Properties properties;

	public ConfigLoader() {
		properties = new Properties();
		InputStream input;
		try {
			input = ConfigLoader.class.getClassLoader().getResourceAsStream("files.properties");
			properties.load(input);
		} catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
