package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.SeleniumUtils;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		// initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*=flyInOut]")
	WebElement errorMessage;

	
	public ProductCatalog loginApplication(String email,String password) throws InterruptedException
	{
		waitForWebElementToAppear(userEmail);
		waitForWebElementToAppear(passwordEle);
		waitForWebElementToAppear(submit);
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", submit);
		SeleniumUtils.safeClick(driver, submit);
		//submit.click();

		ProductCatalog productcatalog = new ProductCatalog(driver);
		return productcatalog;

	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTO()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
	
}
