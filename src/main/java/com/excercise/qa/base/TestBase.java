package com.excercise.qa.base;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.excercise.qa.util.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static Logger logger = Logger.getLogger(TestBase.class);
	public static WebDriver driver;
	static Properties prop;

	public TestBase() {
		try {
			prop = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			prop.load(inputStream);
		} catch (Exception e) {
			logger.info("Property not found");
		}

	}

	public static void initialization() {
		String browsername = prop.getProperty("browser");
		if (browsername.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().version("2.43").setup();
			driver = new ChromeDriver();
		} else if (browsername.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().version("2.43").setup();
			driver = new FirefoxDriver();
		}
		// maximize the browser window
		driver.manage().window().fullscreen();
		// Added implicit wait
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		// Added to wait till page gets loaded completely
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("Url"));
	}

}
