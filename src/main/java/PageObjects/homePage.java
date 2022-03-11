package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseTest;

public class homePage extends BaseTest{

	
	public homePage() {

	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickOnHomePageLogo() {
		driver.findElement(By.xpath("//li[@class='commbank-logo']/a")).click();
	}
	
	public homeLoanCalcPage clickOnHomeLoanRepaymentCalculator() {
		System.out.println("Trying to click homeloan calculator");
		WebElement calc = driver.findElement(By.xpath("//a[contains(text(),'Repayments calculator')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", calc);
		calc.click();
		
		return new homeLoanCalcPage();
	}

}
