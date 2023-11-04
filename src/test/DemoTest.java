package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest;
import objects.InveneWelcomePage;

public class DemoTest extends BaseTest{

	@Test
	public void TC1() throws Exception {
		startTest("https://invene.com/");
		assertTextPresentInElement("//h2[contains(text(),'Healthcare Product Development')]", "xpath", "Healthcare Product Development");
		takescreenshot();
		InveneWelcomePage inv = new InveneWelcomePage(driver);
		Assert.assertEquals(inv.wrapperText(), "Healthcare Software That Empowers Your Users");
		inv.homeClick();
		takescreenshot();
	}
	
}
