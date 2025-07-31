package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productcatalog;
	public ConfirmationPage confirmationPage ;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) throws InterruptedException 
	{
		productcatalog = landingPage.loginApplication(username,password);
	}
	
    @When ("^I add product (.+) to Cart$")
    
    public void i_add_product_to_cart(String productName) throws InterruptedException
    {
    	List <WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
    }
    
 @When ("^Checkout (.+) and submit the order$")
    
    public void checkout_submit_order(String productName) throws InterruptedException
    {
	 CartPage cartPage = productcatalog.goToCartPage();
		
		Boolean matchCheck = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(matchCheck);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.SelectCountry("France");
		checkoutPage.submitOrder();
		confirmationPage = checkoutPage.submitOrder();
    }
   
 @Then ("{string} message is displayed on ConfirmationPage")
 	public void message_displayed_confirmationPage(String string)
 	{
	 String confirmMessage = confirmationPage.GetConfirmationMessage();
	 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	 driver.quit();
 	}
 
 @Then("{string} message is displayed")
 public void incorrect_email_or_password_message_is_displayed(String string) {

	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	System.out.println("check if AfterMatchCheck is ok");
    driver.quit();
 }


}
  