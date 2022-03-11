package PageObjects;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class homeLoanCalcPage extends BaseTest{
	
	@FindBy(xpath = "//h1")
	WebElement pageHeader;
	
	@FindBy(xpath="//input[@id='amount']")
	WebElement borrowAmountEle;
	
	@FindBy(xpath="//input[@id='term']")
	WebElement termEle;
	
	@FindBy(xpath ="//select[@id='interestOnly']")
	WebElement repayTypeEle;
	
	@FindBy(xpath ="//select[@id='productId']")
	WebElement interestTypeEle;
	
	@FindBy(xpath ="//a[@id='useProductList']")
	WebElement interestType2;
	
	@FindBy(xpath ="//input[@id='customRate']")
	WebElement inputInterest;
	
	@FindBy(xpath = "//button[@id='submit']")
	WebElement calculateBtn;
	
	@FindBy(xpath="//select[@id='frequency']")
	WebElement selectFrequency;
	
	@FindBy(xpath="//a[text()='+ What if I make extra repayments?' ]")
	WebElement extraPaymentLink;
	
	@FindBy(xpath="//div[@class='results']/div/div/div/div/h3/span")
	WebElement monthlyEMI;
	
	@FindBy(xpath="//table[@class='repayments-table']/tbody/tr")
	List<WebElement> repaymentsTable;
	
	@FindBy(xpath="//span[text()='Total loan repayments']/following-sibling::span[@class='h3']")
	WebElement totalLoanRepayAmount;
	
	@FindBy(xpath="//span[text()='Total interest charged']/following-sibling::span[@class='h3']")
	WebElement totalInterest;
	
	@FindBy(xpath="//span[@class='frequency-benefit']")
	WebElement frequencyBenefitMsg;
	
	@FindBy(xpath="//a[@id='toggle-graph-link']")
	WebElement graphToggleLink;
	
	@FindBy(xpath = "//div[@class='results']/div/div/div/div/p/strong/span")
	WebElement monthlyRepay ;
	
	@FindBy(id = "wealthPackage")
	WebElement wealthPackageRadioBtn;
	
	@FindBy(xpath = "//input[@id='extra-repayment']")
	WebElement extraPayInput;
	
	public homeLoanCalcPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public String checkPageHeader() {
		super.waitForElementVisibility(pageHeader);
		return pageHeader.getText();	
	}

	
	public void clickCalulateButton() {
		calculateBtn.click();
	}
	
	public void enterBasicDetailsForHomeLoanCalculation(String borrowAmt, String term, String repayType, String interestType) {
		
		borrowAmountEle.clear();
		super.sendKeyToEle(borrowAmountEle, borrowAmt);
		
		
		termEle.clear();
		super.sendKeyToEle(termEle, term);
		
		super.selectValueFromDropDown(repayTypeEle, repayType);
		
		super.selectValueFromDropDown(interestTypeEle, interestType);
		
	}

	public String getMonthlyEMIAmount() {
		super.scrollToEle(monthlyEMI);
		String amount = monthlyEMI.getText().replace(",","");
		return amount.replace("$","");
	}
	
	public String getTotalLoanRepayAmt() {
		super.scrollToEle(totalLoanRepayAmount);
		 String amount = totalLoanRepayAmount.getText().replace(",","");	
		 return amount.replace("$","");
	}
	
	public String getTotalRepayInterest() {
		super.scrollToEle(totalInterest);
		String amount = totalInterest.getText().replace(",","");
		return amount.replace("$","");
	}
	
	public String getMonthlyRepayAmount() {
		super.scrollToEle(monthlyRepay);
		String amount = monthlyRepay.getText().replace(",","");
		return amount.replace("$","");
		
	}
	
	public void selectFrequencyType(String freqType) throws InterruptedException {
		super.selectValueFromDropDown(selectFrequency, freqType);
		Thread.sleep(20000);
	}
	
	public String getFrequencyBenefitMsg() {
		return frequencyBenefitMsg.getText();
	}
	
	
	public void checkOrUncheckWealthPackage(String selectType) throws InterruptedException {
		
		super.radioButtonSelection(wealthPackageRadioBtn, selectType);
	}
	
	public void enterExtraRepayAmount(String extraPay) {
		
		try {
			super.scrollToEle(extraPayInput);
			extraPayInput.sendKeys(extraPay);
		}
		catch(NoSuchElementException e) {
			super.scrollToEle(extraPaymentLink);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", extraPaymentLink);
			extraPayInput.sendKeys(extraPay);
		}

		
	}
	
}
