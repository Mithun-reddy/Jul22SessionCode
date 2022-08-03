import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDemo {
	static WebDriver driver;

	public static void loginToSFDC() {

		driver.get("https://login.salesforce.com");
		driver.findElement(By.name("username")).sendKeys("jul22.mithun@ta.com");
		System.out.println("Entered username in username field");
		driver.findElement(By.id("password")).sendKeys("Mithun123");
		System.out.println("Entered password in password field");
		driver.findElement(By.id("Login")).click();
		driver.manage().window().maximize();
	}

	public static void TC06() {
		String[] expectedUserMenuValues = { "My Profile", "My Settings", "Developer Console",
				"Switch to Lightning Experience", "Logout" };
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".menuButtonButton #userNavLabel")).click();

		for (int i = 0; i < expectedUserMenuValues.length; i++) {
			String actualValue = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a" + "[" + (i + 1) + "]"))
					.getText();
			if (actualValue.equals(expectedUserMenuValues[i])) {
				System.out.println(expectedUserMenuValues[i] + " is verified successfully");
			}
		}
		// clicks on my profile
		driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[1]")).click();

		// click on edit profile
		driver.findElement(By.xpath("//a[@class='contactInfoLaunch editLink']")).click();

		// Switching to an iframe

		driver.switchTo().frame("contactInfoContentId");

		driver.findElement(By.xpath("//li[@id='aboutTab']/a")).click();

		WebElement lastName = driver.findElement(By.xpath("//input[@id='lastName']"));
		lastName.clear();
		lastName.sendKeys("Raj");
		driver.findElement(By.xpath("//*[@value='Save All']")).click();

		// come out of iframe

		driver.switchTo().defaultContent();

		driver.findElement(By.cssSelector("#publishereditablearea")).click();

		driver.switchTo().frame(0);

		driver.findElement(By.xpath("/html/body")).sendKeys("Adding my first post");
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("#publishersharebutton")).click();

		driver.findElement(By.cssSelector("#publisherAttachContentPost")).click();
		
//		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@id='publisherAttachContentPost']/span[1]")).click();
		
		driver.findElement(By.cssSelector("#chatterUploadFileAction")).click();
		driver.findElement(By.cssSelector("#chatterFile")).sendKeys("C:\\Users\\user\\Desktop\\Framework.PNG");
		
		driver.findElement(By.cssSelector("#publishersharebutton")).click();
		if(driver.findElement(By.cssSelector("#displayBadge")).isDisplayed()) {
			driver.findElement(By.cssSelector("#displayBadge")).click();
		} else {
			System.out.println("Display badge is not visible");
		}
		
		
		driver.switchTo().frame("uploadPhotoContentId");
		driver.findElement(By.id("j_id0:uploadFileForm:uploadInputFile")).sendKeys("C:\\Users\\user\\Desktop\\Framework.PNG");
		driver.findElement(By.name("j_id0:uploadFileForm:uploadBtn")).click();

		// explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("j_id0:j_id7:save"))));
		
//		driver.findElement(By.name("j_id0:j_id7:save")).click();
//		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", driver.findElement(By.name("j_id0:j_id7:save")));
		
	}

	public static void main(String[] args) throws Exception {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		SeleniumDemo.loginToSFDC();
		SeleniumDemo.TC06();
		driver.close();

	}
	
	public void jsExecutorDemo() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// alert
		js.executeScript("alert('Leave this site');");
		// Send text 
		js.executeScript("arguments[0].value='mithun.r';", driver.findElement(By.id("username")));
		// To scroll
		js.executeScript("window.scrollBy(0,600)");
		//To click
		js.executeScript("arguments[0].click();", driver.findElement(By.id("Login")));
		
		
		
		
		
		
	}
}
