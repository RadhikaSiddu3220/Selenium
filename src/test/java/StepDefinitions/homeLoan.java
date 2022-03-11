package StepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import PageObjects.homeLoanCalcPage;
import PageObjects.homePage;
import Utilities.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class homeLoan extends BaseTest{
	
	homePage homePO = new homePage();
	homeLoanCalcPage calcPO;
	
	@Before()
	public void setup(){
		BaseTest.initialize();
	}
	
	@After()
	public void teardown() {
		BaseTest.closeBrowser();
	}
	
	/*
	 * @AfterStep() public void backToHome() { homePO.clickOnHomePageLogo(); }
	 */
	
	@Given("user is in the home page")
	public void user_is_in_the_home_loan_calculator_page() throws Throwable {
		String actualTitle = "CommBank - bank accounts, credit cards, home loans and insurance";
		String title = homePO.getTitle();
	}
	
	@And("click on the home loan repayment calculator")
	public void click_on_the_home_loan_repayment_calculator() throws InterruptedException {
	    calcPO = homePO.clickOnHomeLoanRepaymentCalculator();
//	    Thread.sleep(20);
	    
	}
	
	
	@When("user input details {string}, {string}, {string} and {string}")
	public void user_input_details(String BorrowAmount, String Term, String RepayType, String HomeLoanType) throws InterruptedException {
		String actualHeader = calcPO.checkPageHeader();
		String expectedHeader = "Home loan repayments calculator";
		assertEquals(actualHeader, expectedHeader);
		
		calcPO.enterBasicDetailsForHomeLoanCalculation(
				BorrowAmount, Term,
				RepayType.trim(), HomeLoanType.trim());
		
		calcPO.clickCalulateButton();
		
	}
	
	@When("user enter details")
	public void user_input_the_basic_details(DataTable data) throws InterruptedException {
		
		List<Map<String, String>> input_data = data.asMaps(String.class, String.class);
		
		calcPO.enterBasicDetailsForHomeLoanCalculation(
				input_data.get(0).get("BorrowAmount"), input_data.get(0).get("Term"),
				input_data.get(0).get("RepayType").trim(), input_data.get(0).get("HomeLoanType").trim());
		
		calcPO.clickCalulateButton();
	}

	@Then("Validate amounts {string}, {string} and {string}")
	public void validate_amounts(String EMIvalue, String TotalLoanPay, String TotalInterest) {
	
		assertEquals(EMIvalue, calcPO.getMonthlyEMIAmount());
		
		assertEquals(TotalLoanPay, calcPO.getTotalLoanRepayAmt());
		
		assertEquals(TotalInterest, calcPO.getTotalRepayInterest());
	}

	@Then("validate monthly repay amount along with total loan amount {string}, {string}, {string} and {string}")
	public void validate_amounts(String EMIvalue, String TotalLoanPay, String TotalInterest, String MonthlyRepay) {
	
		assertEquals(EMIvalue, calcPO.getMonthlyEMIAmount());
		
		assertEquals(TotalLoanPay, calcPO.getTotalLoanRepayAmt());
		
		assertEquals(TotalInterest, calcPO.getTotalRepayInterest());
		
		assertEquals(MonthlyRepay, calcPO.getMonthlyRepayAmount());
	}
	
	
	@Then("change the Repayment frequency to {string}")
	public void change_frequency(String freqType) throws InterruptedException {
	
		calcPO.selectFrequencyType(freqType);
	}
	
	
	@Then("validate the repayment amounts")
	public void validate_the_repayment_amount_on_frequency_change(DataTable output) {
		List<Map<String, String>> output_data = output.asMaps(String.class, String.class);
			
		assertEquals(output_data.get(0).get("MonthlyEMI"), calcPO.getMonthlyEMIAmount());
		
		assertEquals(output_data.get(0).get("TotalLoanRepay"), calcPO.getTotalLoanRepayAmt());
		
		assertEquals(output_data.get(0).get("TotalInterest"), calcPO.getTotalRepayInterest());	
	}
	
	@Then("check for frequency message {string}")
	public void check_for_frequency_message(String freqMsg) {
	
		assertEquals(freqMsg, calcPO.getFrequencyBenefitMsg());
	}
	
	@Then("{string} the Wealth Package radio button")
	public void check_or_uncheck_wealth_package_radio_button(String selectType) throws InterruptedException {
		calcPO.checkOrUncheckWealthPackage(selectType);
	}
	
	@When("make the extra payment of {string}")
	public void make_the_extra_repayment(String extraPayAmt) throws InterruptedException {
		calcPO.enterExtraRepayAmount(extraPayAmt);
	}
	
	
	
	




}
