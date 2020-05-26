package testScript;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

public class LaunchAmazonApp {

	static DesiredCapabilities cap;
	public static void orderProductFromApp(String productName, Properties pro) throws MalformedURLException, InterruptedException
	{
		cap= new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
		cap.setCapability(MobileCapabilityType.UDID, pro.getProperty("UDID"));
		cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		cap.setCapability("appActivity", "com.amazon.mShop.aiv.AIVGatewayStartupActivity");
		cap.setCapability("noReset", "true");
		AndroidDriver driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//android.widget.Buttextton[@text='Sign in']")).click();
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.widget.EditText").sendKeys(pro.getProperty("email"));
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[2]/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[7]/android.widget.Button").click();	
		driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View[1]/android.view.View[8]/android.view.View[2]/android.widget.EditText").sendKeys(pro.getProperty("password"));	
		driver.findElementByXPath("//android.widget.Button[@text='Login']").click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Search']")).sendKeys("galaxy");
		driver.pressKeyCode(AndroidKeyCode.ENTER);
		driver.findElement(By.xpath("//android.view.View[@text='Samsung Galaxy M21 (Midnight Blue, 6GB RAM, 128GB Storage)']")).click();
		Dimension size = driver.manage().window().getSize();
		int startx=(int)(size.width*0.50);
		int starty=(int)(size.height*0.90);
		int endy=(int)(size.height*0.20);
		for(int i=0;i<2;i++)
		{
			driver.swipe(startx, starty, startx, endy, 1000);
			Thread.sleep(2000);
		}
		driver.findElement(By.xpath("//android.widget.Button[@text='Add to Cart']")).click();
	}
}
