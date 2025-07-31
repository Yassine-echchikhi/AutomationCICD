package rahulshettyacademy.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException
	{
	// TODO Auto-generated method stub

		//landingPage = launchApplication();
		ProductCatalog productcatalog = landingPage.loginApplication("ey.selenium@yopmail.com","WrongPass");
	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		System.out.println("check if AfterMatchCheck is ok");

	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException
	{
	// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		ProductCatalog productcatalog = landingPage.loginApplication("ey.selenium@yopmail.com","Azerty123+");
		 //= new ProductCatalog(driver);
		List <WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(productName);
		CartPage cartPage = productcatalog.goToCartPage();		
		Boolean matchCheck = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(matchCheck);

	}

} 
