package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public BaseTest() {
		
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("C:\\Users\\rark\\Radhika\\CBA Assesment\\HomeLoan\\src\\main\\java\\configuration\\configuration.properties");
			prop.load(file);
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static void initialize() {
		String browserName = prop.getProperty("browser");
		
		if(browserName.toUpperCase().equals("CHROME")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (browserName.toUpperCase().equals("FIREFOX")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	public static void closeBrowser() {
		driver.close();
	}

	public void waitForElementVisibility(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void sendKeyToEle(WebElement ele, String input) {
		ele.sendKeys(input);
	}
	
	public void selectValueFromDropDown(WebElement ele, String value) {
		Select select = new Select(ele);
		select.selectByVisibleText(value);
	}
	
	public String getTextOfElement(WebElement ele) {
		return ele.getText();
	}
	
	public void radioButtonSelection(WebElement ele, String selectionType) throws InterruptedException {
		
		try {
			if(selectionType.equalsIgnoreCase("select")) {  // selecting radio button
				if(!ele.isSelected())
					ele.click();
			}
			
			else if(selectionType.equalsIgnoreCase("unselect")) {  // deselecting radio button
				if(ele.isSelected())
					ele.click();
			}
		}
		catch(Exception e) {
			js.executeScript("arguments[0].click()", ele);
		}

	}
	
	public void scrollToEle(WebElement ele) {
		
		js.executeScript("arguments[0].scrollIntoView(true);",ele);
	}
}
