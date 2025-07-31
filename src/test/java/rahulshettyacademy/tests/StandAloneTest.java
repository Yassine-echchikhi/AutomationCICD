package rahulshettyacademy.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingpage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("ey.selenium@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Azerty123+");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".heading.cf")));

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "France").build()
				.perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.cssSelector(".ta-item:nth-of-type(1)")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".field input")).sendKeys("4111 1111 1111 1111");

		WebElement DayDropDown = driver.findElement(By.cssSelector(".input.ddl:first-of-type"));
		Select dropdownday = new Select(DayDropDown);
		dropdownday.selectByIndex(2);

		WebElement MonthDropDown = driver.findElement(By.cssSelector(".input.ddl:last-of-type"));
		Select dropdownmonth = new Select(MonthDropDown);
		dropdownmonth.selectByIndex(2);

		driver.findElement(
				By.xpath("//div[contains(@class, 'title') and contains(., 'CVV Code')]/following-sibling::*[1]"))
				.sendKeys("737");
		driver.findElement(
				By.xpath("//div[contains(@class, 'title') and contains(., 'Name on Card')]/following-sibling::*[1]"))
				.sendKeys("Yassine");
		WebElement submit = driver.findElement(By.cssSelector(".action__submit"));
		
		// we Added Java Script Executor , because the element was not seen , and we should scroll down to be seen by the automate then be clickaBLE;

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", submit);

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
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

}
