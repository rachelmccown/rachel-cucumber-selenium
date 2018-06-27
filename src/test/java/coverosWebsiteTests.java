import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class coverosWebsiteTests {

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
    public void clickObject(String obj) {
        if(obj.equals("SecureCI")) {
            driver.findElement(By.linkText("SecureCI")).click();
        } else {
            System.out.println("Object not Found");
        }
    }

    @And("^Enter in my first name as (.*)$")
    public void enterFirstName(String fname) {
        driver.findElement(By.name("FirstName")).sendKeys(fname);
    }

    @And("^I enter in my last name as (.*)$")
    public void enterLastName(String lname) {
        driver.findElement(By.name("LastName")).sendKeys(lname);
    }

    @And("^I enter in my email as (.*)$")
    public void enterEmail(String email) {
        driver.findElement(By.name("email")).sendKeys(email);
    }

    @And("^I enter in my company as (.*)$")
    public void enterCompany(String company) {
        driver.findElement(By.name("Company")).sendKeys(company);
    }

    @And("^to download SecureCI I click submit$")
    public void submitSecureCI() {
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/p")).submit();
    }

    @Then("^the title should be (.*)$")
    public void checkTitle(String title) {
        if(driver.getTitle().equals(title)) {
            System.out.println("Title Test Successful");
        } else {
            System.out.println("Title Test Failed, title expected: " + title + " but actual was: " + driver.getTitle());
        }
        driver.close();
    }

    @Then("^the message should say (.*)$")
    public void submissionMessage(String message){
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement submitMessage = driver.findElement(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/div[2]"));
        Assert.assertEquals(submitMessage.getText(), message);
    }

    @After
    public void afterClass(){
        driver.quit();
    }

}
