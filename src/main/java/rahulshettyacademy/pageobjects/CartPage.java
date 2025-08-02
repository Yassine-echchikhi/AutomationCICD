package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.AbstractComponents.SeleniumUtils;

public class CartPage extends AbstractComponent {
	
	// New commments are added
	// Second comment Added to Test Github push and Jenkins run
	
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List <WebElement> cartProducts;
	
	public CartPage(WebDriver driver)
	{
		// initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		SeleniumUtils.safeClick(driver, checkoutEle);
		//checkoutEle.click();
		return new CheckoutPage(driver);
		
	}
}
	

	
	
	
	
	
	
