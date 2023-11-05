package common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class BaseTest {

	public static WebDriver driver;

	public void openBrowser() {
		driver = new ChromeDriver();
		System.out.println("Test in running in Google");

	}

	public void startTest(String webappUrl) {

		String TestCaseName = this.getClass().getSimpleName();

		log("");
		log(">>>>> Executing " + TestCaseName + " <<<<<");
		log("");

		try {

			log("Running test in Google");
			driver = new ChromeDriver();
			driver.get(webappUrl);

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			driver.manage().window().maximize();

			log("Opened " + webappUrl);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@AfterClass
	public void endTest() {
		String TestCaseName = this.getClass().getSimpleName();
		log("");
		log(">>>>> Terminating " + TestCaseName + " <<<<<");
		log("");
		driver.quit();

	}

	public static void log(String message) {
		System.out.println(message);
		Reporter.log(message);
	}

	public void takescreenshot() throws IOException {
		String path = System.getProperty("user.dir").replace("\\", "/") + "/test-output/screenshots/";
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			FileUtils.copyFile(scrFile, new File("C:\\Workspace\\SeleniumProject\\test-reports\\screenshots\\"
//					+ getFileName(this.getClass().getSimpleName())));
			FileUtils.copyFile(scrFile, new File(path + getFileName(this.getClass().getSimpleName())));

		} catch (Exception e) {
			log("Screenshot is not created.");
			e.printStackTrace();
		}
	}

	private String getFileName(String nameTest) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
		Date date = new Date();
		return dateFormat.format(date) + "_" + nameTest + ".png";
	}

	public void assertTextPresentInElement(String locator, String locType, String valueToCheck) throws Exception {
		System.out.println(valueToCheck);
		// driver.manage().timeouts().implicitlyWait(D);
		try {

			if (locType.equalsIgnoreCase("id")) {
				Assert.assertTrue(driver.findElement(By.id(locator)).getText().equals(valueToCheck));
				//System.out.println(driver.findElement(By.xpath(locator)).getText());
				log("The text " + valueToCheck + " is present in the web element.");

			} else if (locType.equalsIgnoreCase("name")) {
				Assert.assertTrue(driver.findElement(By.name(locator)).getText().equals(valueToCheck));
				
				log("The text " + valueToCheck + " is present in the web element.");
			} else if (locType.equalsIgnoreCase("class")) {
				Assert.assertTrue(driver.findElement(By.className(locator)).getText().equals(valueToCheck));
				
				log("The text " + valueToCheck + " is present in the web element.");
			} else {
				Assert.assertTrue(driver.findElement(By.xpath(locator)).getText().equals(valueToCheck));
				
				log("The text " + valueToCheck + " is present in the web element.");

			}
		} catch (Exception e) {

			log("Text is not present in the web element.");
			e.printStackTrace();
			Assert.fail();

		}

	}
	
	
}
