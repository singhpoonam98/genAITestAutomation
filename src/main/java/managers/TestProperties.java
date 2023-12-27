package managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestProperties {
	private static final Logger logger = LogManager.getLogger(TestProperties.class);
	private static TestProperties INSTANCE;
	private Properties envProperties;
	private Properties userProperties;


	public static TestProperties getInstance() {
		if (Objects.isNull(INSTANCE)) {
			INSTANCE = new TestProperties();
		}
		return INSTANCE;
	}
	public String getEnvProperty(String propertyKey) {
		envProperties = initProperties(System.getProperty("user.dir")+ "/src/main/resources/properties/demo/environment.properties", envProperties);
		return envProperties.getProperty(propertyKey);
	}
	
	public String getUserProperty(String propertyKey) {
		userProperties = initProperties(System.getProperty("user.dir")+ "/src/main/resources/properties/demo/user.properties", userProperties);
		return userProperties.getProperty(propertyKey);
	}

	private Properties initProperties(String filePath, Properties testProperties) {
		if (Objects.isNull(testProperties)) {
			logger.info("FilePath:" + filePath);
			testProperties = new Properties();
			tryLoadPropertiesFromFile(filePath, testProperties);
		}
		return testProperties;
	}
	private void tryLoadPropertiesFromFile(String propertiesFilePath, Properties properties) {
		try {
			properties.load(new FileInputStream(propertiesFilePath));
		} catch (IOException e) {
			logger.error("Problem with a properties file", e);
		}
	}
	private TestProperties() {
	}
}