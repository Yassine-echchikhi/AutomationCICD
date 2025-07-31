package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.AbstractComponents.SeleniumUtils;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		// initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css=".action__submit")
	private WebElement submitBtn;
	
	@FindBy(css=".ta-item:nth-of-type(1)")
	private WebElement selectCountry;
	
	private By resultsBy = By.cssSelector(".ta-results");
	
	public void SelectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(resultsBy);
		
		SeleniumUtils.safeClick(driver, selectCountry);
		//selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		//submitBtn.click(); // Not working because selenium can't find the element so we  use JavaScrip Executor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", submitBtn);
		return new ConfirmationPage(driver);
	}
	

	
}
