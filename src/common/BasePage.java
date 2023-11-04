package common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	/** Timeout for finding page element in seconds */
	// protected static final long FIND_ELEMENT_TIMEOUT = 30L;

	protected static final long waitTime = 30;

	/** The Selenium2 web driver. */
	protected WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement findElement(String elementId, String locType) {
		WebElement element = null;

		try {
			if (locType.equalsIgnoreCase("id")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementId)));

			} else if (locType.equalsIgnoreCase("name")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementId)));

			} else if (locType.equalsIgnoreCase("linktext")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(elementId)));

			} else if (locType.equalsIgnoreCase("partiallinktext")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(elementId)));

			} else if (locType.equalsIgnoreCase("css")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementId)));

			} else if (locType.equalsIgnoreCase("class")) {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementId)));

			} else {
				return new WebDriverWait(driver, Duration.ofSeconds(waitTime))
						.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementId)));
			}
		} catch (Exception e) {
			System.out.println("Element is not found");
			return element;

		}
	}

	public void click(String locator, String locType) {

		WebElement element = findElement(locator, locType);
		element.click();

	}

	public void enterText(String locator, String locType, String value) {

		WebElement element = findElement(locator, locType);
		element.clear();
		element.sendKeys(value);

	}

	public void selectDropdownByVisibleText(String locator, String locType, String value) {

		new Select(findElement(locator, locType)).selectByVisibleText(value);
	}

	public void hover(String locator, String locType) {

		WebElement element = findElement(locator, locType);
		// element.click();
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	
	
	public void verifyDisplayed(String locator, String locType, String objectName, long waitMilliSec) throws Exception {

		WebElement element = findElement(locator, locType);
		element.wait(waitMilliSec);
		element.isDisplayed();
		if (element.isDisplayed()) {
			BaseTest.log(objectName + " is displayed.");
		} else {
			BaseTest.log(objectName + " is not displayed.");
		}

	}

}
