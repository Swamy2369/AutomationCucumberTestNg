package frameworkBase;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

/**
 * The Utilities class has all the usual methods to implement in the framework
 * according to our use.
 * 
 * @author Swamy
 */

public class Utilities {

	public String getRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = Integer.parseInt(System.getProperty("randomStringCount"));
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	public String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(cal.getTime());

	}

	public String getPropertyFromFile(String configFileName, String key) {
		Properties prop = new Properties();
		String value = null;
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config\\"
					+ configFileName + ".properties"));
			value = prop.getProperty(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return value;

	}

}