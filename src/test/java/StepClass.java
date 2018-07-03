import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class StepClass {
    WebDriver driver;
    SeleniumImplementations selActions;

    @Before
    public void openBrowser() {
        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "";
        }
        switch (browser) {
            case ("Chrome"):
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case ("Firefox"):
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case ("Edge"):
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                HtmlUnitDriver htmlunit = new HtmlUnitDriver();
                htmlunit.setJavascriptEnabled(true);
                driver = htmlunit;
                break;
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

    @After
    public void afterClass() {
        if (driver != null) driver.quit();
    }


}
