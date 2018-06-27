import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;

public class coverosWebsiteDefs {

    WebDriver driver = null;

    private static java.lang.String getOs() {
        java.lang.String os = System.getProperty("os.name");
        os = os.substring(0, os.indexOf(" ")).toLowerCase();
        return os;
    }

    public static void setDriver(String browser) {
        java.lang.String os = getOs();
        String driverName = "";
        if(browser.equals("Firefox")) {
            driverName = "geckodriver";
        } else if(browser.equals("Chrome")){
            driverName = "chromedriver";
        }
        if (os.equals("windows")) {
            driverName += ".exe";
        }
        System.setProperty("webdriver.gecko.driver", "src/test/drivers/" + driverName);
    }

    @Given("^I have opened the (.*) browser$")
    public void openBrowser(String browser) {
        setDriver(browser);
        driver = new FirefoxDriver();
    }

    @When("^I open Coveros website$")
    public void goToCoveros() {
        driver.navigate().to("https://www.coveros.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @And("^I click (.*)$")
    public void clickPageObject(String obj) {
        switch (obj) {
            case("SecureCI"): driver.findElement(By.linkText("SecureCI")).click(); break;
            case("Blog"): driver.findElement(By.linkText("Read more")).click(); break;
            case("Training"): driver.findElement(By.linkText("Training")).click(); break;
            default: System.out.println("Page Object not Found"); break;
        }
    }

    @And("^I hover over (.*)$")
    public void hoverOnPageObject(String obj){
        Actions action = new Actions(driver);
        WebElement hoverObject = null;
        switch (obj) {
            case("Products"): hoverObject = driver.findElement(By.linkText("Products")); break;
            case("Services Dropdown"): hoverObject = driver.findElement(By.linkText("Services")); break;
            case("Thought Leadership"): hoverObject = driver.findElement(By.linkText("Thought Leadership")); break;
            case("About Us"): hoverObject = driver.findElement(By.linkText("About Us")); break;
            default: System.out.println("Object not Found"); break;
        }
        if(hoverObject != null)
            action.moveToElement(hoverObject).build().perform();
    }

    @And("^I enter in my (.*) as (.*)$")
    public void secureCIForm(String userElement, String entry){
        String element = userElement.toLowerCase();
        switch(element){
            case("first name"): driver.findElement(By.name("FirstName")).sendKeys(entry); break;
            case("last name"): driver.findElement(By.name("LastName")).sendKeys(entry); break;
            case("email"): driver.findElement(By.name("email")).sendKeys(entry); break;
            case("company"): driver.findElement(By.name("Company")).sendKeys(entry); break;
            default: System.out.println("Form object not found.");
        }
    }

    @And("^to download SecureCI I click submit$")
    public void submitSecureCI() {
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/p")).submit();
    }

    @Then("^the title should be (.*)$")
    public void confirmTitle(String title) {
        Assert.assertEquals(driver.getTitle(), title);
    }

    @Then("^the message should say (.*)$")
    public void confirmMessage(String message){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement submitMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/div[2]")));
        Assert.assertEquals(submitMessage.getText(), message);
    }

    @Then("^the page should display (.*)$")
    public void confirmPageObjectDisplayed(String pageObj){
        switch (pageObj) {
            case("Recent Blogs"): Assert.assertTrue(driver.findElement(By.id("recent-posts-4")).isDisplayed()); break;
            default: System.out.println("Page Object " + pageObj + " Could not be located"); Assert.assertTrue(false); break;
        }
    }

    @After
    public void afterClass(){
        driver.quit();
    }

}
