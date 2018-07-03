import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CoverosWebsiteStepDefinitions {

    WebDriver driver;
    SeleniumImplementations selActions;

    @Before
    public void openBrowser() {
        String browser = System.getProperty("browser");
        if(browser == null){
            browser = "";
        }
        switch (browser) {
            case("Chrome"): WebDriverManager.chromedriver().setup(); driver = new ChromeDriver(); break;
            case("Firefox"): WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver(); break;
            case("Edge"): WebDriverManager.edgedriver().setup(); driver = new EdgeDriver(); break;
            default: driver = new HtmlUnitDriver(); ((HtmlUnitDriver) driver).setJavascriptEnabled(true);  break;
        }
        String size = System.getProperty("size");
        if (size != null) {
            driver.manage().window()
                    .setSize(new Dimension(Integer.valueOf(size.split("x")[0]), Integer.valueOf(size.split("x")[1])));
        } else {
            driver.manage().window().maximize();
        }
        selActions = new SeleniumImplementations(driver);
    }

    @When("^I open the Coveros website$")
    public void goToCoveros() {
        driver.navigate().to("https://www.coveros.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

    @And("^I navigate to the Training Schedule$")
    public void navigateToTrainingSchedule(){
    }

    @And("^I navigate to Everyone$")
    public void navigateToEveryone() {
        selActions.scrollToElement(driver.findElement(By.id("team")));
        WebElement controls = driver.findElement(By.className("filter-controls"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement everyoneButton = controls.findElement(By.className("ats-button"));
        everyoneButton.click();
        wait.until(ExpectedConditions.visibilityOf(controls.findElement(By.className("ats-button ats-button-active"))));
    }

    @And("^I download SecureCI$")
    public void submitSecureCI() {
        driver.findElement(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/p")).submit();
    }


    @And("^I go to the (.*) page$")
    public void goTo(String page){
        selActions.click(page);
    }

    @And("^I email a presentation to (.*) from (.*)$")
    public void emailPresentation(String email, String from){

        selActions.scrollToElement(driver.findElement(By.id("sidebar")));
        driver.switchTo().frame(0);
        WebElement toolbar = driver.findElement(By.className("toolbar"));
        WebElement jtools = toolbar.findElement(By.className("j-tools"));
        jtools.findElement(By.tagName("button")).click();

        driver.findElement(By.id("share-email-to")).sendKeys(email);
        driver.findElement(By.id("share-email-name")).sendKeys(from);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("share-email-send")));
        driver.findElement(By.id("share-email-send")).click();
    }

    @Then("^the title of the page is (.*)$")
    public void confirmTitle(String title) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getTitle(), title);

    }

    @Then("^the SecureCI confirmation message says \"(.*)\"$")
    public void confirmMessage(String message){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement submitMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpcf7-f1227-o1\"]/form/div[2]")));
        Assert.assertEquals(submitMessage.getText(), message);
    }

    @Then("^the page displays (.*)$")
    public void confirmPageObjectDisplayed(String pageObj){
        switch (pageObj) {
            case("Recent Blogs"): Assert.assertTrue(driver.findElement(By.id("recent-posts-4")).isDisplayed()); break;
            default: System.out.println("Page Object " + pageObj + " Could not be located"); Assert.assertTrue(false); break;
        }
    }

    @Then("^the newest blog post is dated (.*)$")
    public void checkBlogDate(String date){
        selActions.scrollToElement(driver.findElement(By.id("sidebar")));
        WebElement article = driver.findElement(By.tagName("article"));
        WebElement metadata = article.findElement(By.className("blog-post-meta"));
        WebElement dateElement = metadata.findElement(By.className("post-meta-time"));
        Assert.assertEquals(date, dateElement.getText());
    }

    @Then("^the (.*) should be (.*)$")
    public void ceoCheck(String position, String employee){

        boolean isPos = false;
        WebElement element = driver.findElement(By.id("team"));
        List<String> employees = Arrays.asList(element.getText().split("\n"));
        if(employees.contains(employee) == true){
            int index = employees.indexOf(employee);
            if(employees.get(index+1).contains(position)){
                isPos = true;
            }
        }
        Assert.assertEquals(true, isPos);
    }

    @Then("^the confirmation message says (.*)$")
    public void confirmEmailSent(String confirmation){
        //TODO Implement Method
    }

    @Then("^there (.*) training courses? available$")
    public void trainingCourses(String available){
        boolean expected = true;
        boolean actual = true;
       if(available.equals("are not") || available.equals("is not")){
           expected = false;
       }
        WebElement element = driver.findElement(By.id("evcalwidget_sc-3"));
        Actions builder = new Actions(driver);
        builder.moveToElement(element);
        String [] scheduleText = element.getText().split("\n");
        if(scheduleText[2].equals("No Events")){
            actual = false;
        }
        Assert.assertEquals(expected, actual);
    }

    @Then("^(.*) is listed$")
    public void findEmployee(String employee){
        boolean isOnWebsite = false;
        WebElement element = driver.findElement(By.id("team"));
        List<String> employees = Arrays.asList(element.getText().split("\n"));
        for(String s: employees){
            if(s.equals(employee)){
                isOnWebsite = true;
            }
        }
        Assert.assertTrue(isOnWebsite);
    }

    @After
    public void afterClass(){
        if(driver != null)
            driver.quit();
    }

}
