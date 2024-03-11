package gluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static commonFunctions.FunctionLibrary.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SupplierSteps 
{
	RemoteWebDriver driver;
    @Given("Launch Browser")
    public void launchBrowser() 
    {
       driver = startBrowser();
    }

    @When("Launch Url {string}")
    public void launchUrl(String url) 
    {
		openUrl(url);
    }

    @Then("wait for username with {string} as {string}")
    public void waitForUsername(String locatorType, String locator) 
    {
    	waitForElement(locatorType, locator, "10");
    }

    @When("enter username with {string} as {string} and testdata as {string}")
    public void enterUsername(String locatorType, String locator, String testData)
    {
    	typeAction(locatorType, locator, testData);
    }

    @When("enter password with {string} as {string} and testdata as {string}")
    public void enterPassword(String locatorType, String locator, String testData)
    {
    	typeAction(locatorType, locator, testData);
    }

    @Then("click login button with {string} as {string}")
    public void clickLogin(String locatorType, String locator) 
    {
        clickAction(locatorType, locator);
    }

    @When("wait for logout link with {string} as {string}")
    public void waitForLogoutLink(String locatorType, String locator)
    {
    	waitForElement(locatorType, locator, "10");
    }

    @When("wait for supplier link with {string} as {string}")
    public void waitForSupplierLink(String locatorType, String locator)
    {
    	waitForElement(locatorType, locator, "10");
    }

    @Then("click supplier link with {string} as {string}")
    public void clickSupplierLink(String locatorType, String locator) 
    {
    	clickAction(locatorType, locator);
    }

    @When("wait for Add + icon with {string} as {string}")
    public void waitForAddIcon(String locatorType, String locator) 
    {
    	waitForElement(locatorType, locator, "10");
    }

    @Then("click on Add + icon with {string} as {string}")
    public void clickOnAddIcon(String locatorType, String locator) 
    {
    	clickAction(locatorType, locator);
    }

    @When("wait for capture supplier number with {string} as {string}")
    public void waitForCaptureSupplierNumber(String locatorType, String locator)
    {
    	waitForElement(locatorType, locator, "10");
    }

    @Then("capture supplier number with {string} as {string}")
    public void captureSupplierNum(String locatorType, String locator) throws Throwable
    {
        captureSupplierNumber(locatorType, locator);
    }

    @When("Enter in {string} with {string} as {string}")
    public void enterSupplierDetails(String data, String locatorType, String locator)
    {
    	typeAction(locatorType, locator, data);
    }

    @Then("click on Add button with {string} as {string}")
    public void clickOnAddButton(String locatorType, String locator)
    {
    	driver.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.id("btnAction")));
    	clickAction(locatorType, locator);
    }

    @When("Wait for confirm ok button with {string} as {string}")
    public void waitForConfirmOkButton(String locatorType, String locator)
    {
    	waitForElement(locatorType, locator, "10");
    }

    @Then("click on confirm ok button with {string} as {string}")
    public void clickOnConfirmOkButton(String locatorType, String locator)
    {
    	clickAction(locatorType, locator);
    }

    @When("wait for alert with {string} as {string}")
    public void waitForAlert(String locatorType, String locator) 
    {
    	waitForElement(locatorType, locator, "10");
    }

    @Then("click on alert with {string} as {string}")
    public void clickOnAlert(String locatorType, String locator) 
    {
    	clickAction(locatorType, locator);
    }

    @Then("user validate the supplier table")
    public void validateSupplierTable() throws Throwable 
    {
       supplierTable();
    }

    @Then("user closes the browser")
    public void closeBrowser() 
    {
       closeBrowser();
    }
}
