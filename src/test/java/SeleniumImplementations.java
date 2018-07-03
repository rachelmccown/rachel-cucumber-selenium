import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeleniumImplementations {

    WebDriver driver;

    public SeleniumImplementations(WebDriver driver){
        this.driver = driver;
    }

    public void scrollToElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void navigateMenu(){
        WebElement element = driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/button/i"));
        if(element.isDisplayed()){
            element.click();
        }
    }

    public void hover(String obj) {
        Actions action = new Actions(driver);
        WebElement hoverObject = null;
        switch (obj) {
            case ("Products"):
                hoverObject = driver.findElement(By.linkText("Products"));
                break;
            case ("Services"):
                hoverObject = driver.findElement(By.linkText("Services"));
                break;
            case ("Thought Leadership"):
                hoverObject = driver.findElement(By.linkText("Thought Leadership"));
                break;
            case ("About Us"):
                hoverObject = driver.findElement(By.linkText("About Us"));
                break;
            default:
                System.out.println("Object not Found");
                break;
        }
        if (hoverObject != null){
            action.moveToElement(hoverObject).build().perform();
        }
    }

    public void click(String obj){

        WebDriverWait wait = new WebDriverWait(driver, 10);
        switch (obj) {
            case("SecureCI"): hover("Products");driver.findElement(By.linkText("SecureCI")).click(); break;
            case("Selenified"): hover("Products"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Selenified"))); driver.findElement(By.linkText("Selenified")).click(); break;
            case("Selenified Github"): scrollToElement(driver.findElement(By.linkText("Check out the README")));driver.findElement(By.linkText("Check out the README")).click(); break;
            case("Blog"): hover("Thought Leadership"); driver.findElement(By.linkText("Blog")).click(); break;
            case("Training"): hover("Services"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Training"))); driver.findElement(By.linkText("Training")).click(); break;
            case("Coveros Twitter"): driver.findElement(By.xpath("//*[@id=\"text-11\"]/div/p/a[2]/img")).click(); break;
            case("Publications"): hover("Thought Leadership"); driver.findElement(By.linkText("Publications")).click(); break;
            case("Presentations"): hover("Thought Leadership"); driver.findElement(By.linkText("Presentations")).click(); break;
            case("Our Team"): hover("About Us"); driver.findElement(By.linkText("Our Team")).click(); break;
            case("Agile Transformation"): hover("Services"); driver.findElement(By.linkText("Agile Transformation")).click(); break;
            default: System.out.println("Page Object not Found"); break;
        }
    }

    /**
     * Create a list of employees shown on the coveros website
     */
    public List<Employee> loadEmployees(){

        List <Employee> employees = new ArrayList<Employee>();
        WebElement element = driver.findElement(By.id("team"));
        List<String> temp2 = Arrays.asList(element.getText().split("\n"));
        for(int i = 8; i < temp2.size(); i+=4){
            employees.add(new Employee(temp2.get(i), temp2.get(i+1), temp2.get(i+2)));
        }
        System.out.println("Done");
        return employees;
    }

}
