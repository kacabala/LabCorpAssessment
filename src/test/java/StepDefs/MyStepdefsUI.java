package StepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyStepdefsUI {
    public static WebDriver driver;
    WebDriverWait webDriverWait;

    @Given("the user is on the main page")
    public void the_user_is_on_the_main_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.manage().window().maximize();

        driver.get("https://www.labcorp.com/");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.labcorp.com/");
    }


    @When("the user clicks Career Links")
    public void the_user_clicks_Career_Links() {
        String careersXpath= "//div[@class='footer-links']//a[contains(@href,'careers')]";
        String acceptCookiesxPath= "//*[@title='Accept Cookies Button']";
        webDriverWait = new WebDriverWait(driver,30);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(acceptCookiesxPath)));
        driver.findElement(By.xpath(acceptCookiesxPath)).click();


        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(careersXpath)));
        driver.findElement(By.xpath(careersXpath)).click();
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String string){
        String keyWordSearchXpath= "//input[contains(@id,'search-keyword-')]";
        String submitXpath = "//button[contains(@id,'search-submit-')]";

        //swithc to the new windows
        Set<String> set = driver.getWindowHandles();
        for(String s:set){
            driver.switchTo().window(s);
            if(driver.getCurrentUrl().contains("jobs")){
                break;
            }
        }

        driver.findElement(By.xpath(keyWordSearchXpath)).sendKeys(string);
        driver.findElement(By.xpath(submitXpath)).click();
    }

    @Then("the user validates the following information")
    public void the_user_validates_the_following_information(io.cucumber.datatable.DataTable dataTable) throws Throwable {
        List<Map<String,String>> maps = dataTable.asMaps();
        String firstJobFilterResultxPath = "(//*[@id='search-results-list']/ul/li)[1]";
        String jobLocationxPath= "//*[@class='job-location job-info']";
        String jobIdxPath= "//*[@class='job-id job-info']";
        String jobDescriptionxPath = "//h1[contains(@class,'job-description')]";


        driver.findElement(By.xpath(firstJobFilterResultxPath)).click();
        //Assert job title, expected, actual
        Assert.assertEquals(maps.get(0).get("Job Title").trim(),driver.findElement(By.xpath(jobDescriptionxPath)).getText().trim());

        //Assert job location, expected, actual
        Assert.assertEquals(maps.get(0).get("Job Location").trim(),driver.findElement(By.xpath(jobLocationxPath)).getText().substring(8).trim());

        //Assert job id, expected, actual
        Assert.assertEquals(maps.get(0).get("Job Id").trim(),driver.findElement(By.xpath(jobIdxPath)).getText().substring(7).trim());
    }
}
