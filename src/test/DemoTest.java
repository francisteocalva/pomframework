package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import common.BaseTest;
import objects.InveneWelcomePage;

public class DemoTest extends BaseTest{

	@Test
	public void TC1() throws Exception  {
		startTest("https://invene.com/");
		InveneWelcomePage inv = new InveneWelcomePage(driver);
		inv.checkTextisPresent("Healthcare Product Development");
		takescreenshot();
		Assert.assertEquals(inv.welcomeText(), "Healthcare Software That Empowers Your Users");
		inv.clickServices("Services");
		takescreenshot();
	}
	
}
