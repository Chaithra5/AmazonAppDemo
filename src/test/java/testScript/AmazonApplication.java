package testScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonApplication {
	WebDriver driver;
	Properties pro;
	@BeforeMethod
	public void launchBrowser() throws FileNotFoundException, IOException
	{
		pro= new Properties();
		pro.load(new FileInputStream("./src/main/resources/AmazonProduct.properties"));
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void orderProduct() throws InterruptedException, MalformedURLException
	{
		//Order product from web
		driver.get(pro.getProperty("url"));	
		driver.findElement(By.xpath("//span[text()='Hello, Sign in']")).click();
		driver.findElement(By.name("email")).sendKeys(pro.getProperty("email"));
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys(pro.getProperty("password"));
		driver.findElement(By.id("signInSubmit")).click();
		driver.findElement(By.id("continue")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("dolls");
		driver.findElement(By.xpath("//input[@type='submit']")).click();;
		WebElement product1 = driver.findElement(By.xpath("//span[text()='L.O.L. Surprise! O.M.G. Candylicious Fashion Doll with 20 Surprises,Multicolor']"));
		String product1Name = product1.getText();
		System.out.println(product1Name);
		product1.click();
		driver.findElement(By.id("add-to-cart-button-ubb")).click();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Books");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='Camino Winds']")).click();
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.close();
		///Launch app through mobile
		LaunchAmazonApp.orderProductFromApp(product1Name,pro);
	}
}
