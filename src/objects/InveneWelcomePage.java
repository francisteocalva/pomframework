package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.BasePage;
import common.BaseTest;

public class InveneWelcomePage extends BasePage {

	public InveneWelcomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void homeClick() {
		click("Services", "linktext");
		BaseTest.log("Hyperlink clicked");
	}

	public String wrapperText() {
		String retVal = null;
		int sizeElement = driver.findElements(By.xpath("//h1[@style='text-align:center;white-space:pre-wrap;']"))
				.size();

		for (int i = 0; sizeElement > i; i++) {
			retVal = driver.findElements(By.xpath("//h1[@style='text-align:center;white-space:pre-wrap;']")).get(i)
					.getText();
			if (retVal.contentEquals("Healthcare Software That Empowers Your Users")) {
				break;
				
			}
		}
		return retVal;

	}

}
