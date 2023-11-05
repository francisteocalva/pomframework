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

	
	String textContainer = "//h2[contains(text(),'Healthcare Product Development')]";
	By welcomeTextContaner = By.xpath("//h1[@style='text-align:center;white-space:pre-wrap;']");
	
	//check if the description "Healthcare Product Development" present in the weblement
	public void checkTextisPresent(String verifyText) throws Exception {
		assertTextPresentInElement(textContainer, "xpath", verifyText);
	}
	
	//click text link
	public void clickServices(String linkText) {
		click(linkText, "linktext");
		BaseTest.log("Hyperlink clicked");
	}
	
	//verify main welcomeText
	public String welcomeText() {
		String retVal = null;
		int sizeElement = driver.findElements(welcomeTextContaner)
				.size();
		for (int i = 0; sizeElement > i; i++) {
			retVal = driver.findElements(welcomeTextContaner).get(i)
					.getText();
			if (retVal.contentEquals("Healthcare Software That Empowers Your Users")) {
				break;
				
			}
		}
		return retVal;
	}

}
