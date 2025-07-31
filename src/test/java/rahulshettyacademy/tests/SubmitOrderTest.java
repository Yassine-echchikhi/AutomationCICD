package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap <String,String> input) throws InterruptedException, IOException
	{
	// TODO Auto-generated method stub

		//landingPage = launchApplication();

		//LandingPage landingPage = launchApplication();
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\YassineEchchikhi\\Documents\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		// webdriver.chrome.driver -> value of path
		//WebDriver driver = new ChromeDriver();
	
		ProductCatalog productcatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		 //= new ProductCatalog(driver);
		List <WebElement> products = productcatalog.getProductList();
		productcatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productcatalog.goToCartPage();
		
		Boolean matchCheck = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(matchCheck);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.SelectCountry("France");
		checkoutPage.submitOrder();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.GetConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		// orders
	
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest() throws InterruptedException
	{
		// "ZARA COAT 3";
		ProductCatalog productcatalog = landingPage.loginApplication("ey.selenium@yopmail.com","Azerty123+");
		//Thread.sleep(2000);
		OrdersPage ordersPage = productcatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		System.out.println("Last order passed is: "+ productName);
		
	}
	

	// Extent Reports - 	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
/*
		//
	HashMap <String,String> map = new HashMap<String,String> ();
//		map.put("email", "ey.selenium@yopmail.com");
//		map.put("password", "Azerty123+");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap <String,String> map1 = new HashMap<String,String> ();
//		map1.put("email", "ey2.selenium@yopmail.com");
//		map1.put("password", "Azerty1234+");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		// Thread.sleep(1500);
		//driver.findElement(By.cssSelector(".field input")).sendKeys("4111 1111 1111 1111");

		//WebElement DayDropDown = driver.findElement(By.cssSelector(".input.ddl:first-of-type"));
		//Select dropdownday = new Select(DayDropDown);
		//dropdownday.selectByIndex(2);

		//WebElement MonthDropDown = driver.findElement(By.cssSelector(".input.ddl:last-of-type"));
		//Select dropdownmonth = new Select(MonthDropDown);
		//dropdownmonth.selectByIndex(2);

		//driver.findElement(
		//		By.xpath("//div[contains(@class, 'title') and contains(., 'CVV Code')]/following-sibling::*[1]"))
				.sendKeys("737");
		//driver.findElement(
			//	By.xpath("//div[contains(@class, 'title') and contains(., 'Name on Card')]/following-sibling::*[1]"))
			//	.sendKeys("Yassine");
			///
			///
			
		//WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
	
		
		// we Added Java Script Executor , because the element was not seen , and we should scroll down to be seen by the automate then be clickaBLE;

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", submit);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
*/
		//driver.close();
		
		
		// a.se

		// driver.findElement(By.cssSelector("input[placeholder='Select
		// Country']")).sendKeys("Fra");

		/*
		 * driver.findElement(By.cssSelector("input[placeholder='Select Country']")).
		 * sendKeys("Fra");
		 * 
		 * Thread.sleep(3000);
		 * 
		 * List <WebElement> countries =
		 * driver.findElements(By.cssSelector(".ta-results.list-group.ng-star-inserted")
		 * );
		 * 
		 * for (WebElement country:countries) { if
		 * (country.getText().equalsIgnoreCase("France")) { country.click(); break; } }
		 * // .field input
		 * 
		 * 
		 * for (WebElement product:products) { WebElement title =
		 * product.findElement(By.tagName("h5")); System.out.println(title.getText());
		 * //break; // Exit after the first iteration }
		 */
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "C:\\Users\\YassineEchchikhi\\Documents\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
		 * ); // webdriver.chrome.driver -> value of path WebDriver driver = new
		 * ChromeDriver();
		 */

	

}
