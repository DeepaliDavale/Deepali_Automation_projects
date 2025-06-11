package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.Timeout;

import static demo.wrappers.Wrappers.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
//import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGERs
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @Test
    public void testCase01() throws InterruptedException{
        //declaring explicit wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        
        //navigate to URL google form-
        String url="https://docs.google.com/forms/u/0/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/formResponse";
        openURL(driver,url);

        //Enter Name field- textbox
        WebElement nameBox=driver.findElement(By.xpath("(//div[contains(@class,'Qr')])[1]//input")) ;
        enterText(nameBox,"Crio Learner");

        //Enter Answer to Why are you practicing Automation?
        WebElement participateBox=driver.findElement(By.xpath("//div[contains(@class,'Pc')]/textarea"));
        long epoch = getEpochTime();
        enterText(participateBox,"I want to be the best QA Engineer! "+String.valueOf(epoch));

        //select Radio button for How much experience do you have in Automation Testing?
        //select 3-5 option from radio button
        //Approach 3- correct approach but its hard coded
        //WebElement experienceRadio=driver.findElement(By.xpath("(//div[@class='Qr7Oae'])[3]//div[@class='d7L4fc bJNwt  FXLARc aomaEc ECvBRb']"));
        //selectItem(experienceRadio);
        //Approach 1 - send selection as parameter and create method to select radio button , send list of radio buttons and option to select

        List<WebElement> radioList=driver.findElements(By.xpath("//span[contains(@class,'OvPDhc')]"));
        selectRadioOption(radioList,"3-5");


        //Select Java, Selenium and TestNG from the next check-box
        WebElement javaElement=driver.findElement(By.xpath("(//div[contains(@class,'Qr')])[4]//span[ text()='Java']"));
        WebElement seleniumElement=driver.findElement(By.xpath("(//div[contains(@class,'Qr')])[4]//span[ text()='Selenium']"));
        WebElement TestNGElement=driver.findElement(By.xpath("(//div[contains(@class,'Qr')])[4]//span[ text()='TestNG']"));
         
        selectItem(javaElement);
        selectItem(seleniumElement);
        selectItem(TestNGElement);

        //select from dropdown how u should be addressed
        WebElement chooseElement=driver.findElement(By.xpath("(//div[contains(@class,'kXd')])[1]"));
        selectItem(chooseElement);
        //Approach 2 -select option directly
        //WebElement saluteElement=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='OA0qNb ncFHed QXL7Te']//div[@class='MocG8c HZ3kWc mhLiyf OIC90c LMgvRb'][2]")));
        //selectItem(saluteElement);

        //Approach 1 - send choice as parameter and select from list
        List<WebElement> saluteList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'ncFHed')]//span[not (contains(text(),'Choose'))]")));
        selectdropdownOption(saluteList,"Dr");


        //what was the date 7 days ago
        WebElement dateElement=driver.findElement(By.xpath("//input[contains(@class,'zHQkBf') and @type='date']"));
        LocalDate currentDate=LocalDate.now();
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("current date ............. "+currentDate);
        LocalDate dateAgo_7=currentDate.minusDays(7);
        String date=dateAgo_7.format(oldFormat);
        System.out.println("date after 7............. "+dateAgo_7);
        System.out.println("to string date............. "+date);
        enterText(dateElement,date);

        //Enter Time in the hh:mm format
        WebElement hourElement =driver.findElement(By.xpath("//input[contains(@class,'zHQkBf') and @aria-label='Hour']"));
        enterText(hourElement,"07");
        WebElement minElement =driver.findElement(By.xpath("//input[contains(@class,'zHQkBf') and @aria-label='Minute']"));
        enterText(minElement,"30");

        //click on Submit
        WebElement submitElement=driver.findElement(By.xpath("//span[text()='Submit']"));
        selectItem(submitElement);
        wait.until(ExpectedConditions.urlContains("formResponse"));
        WebElement respElement=driver.findElement(By.xpath("//div[contains(@class,'vHW')]"));
        System.out.println("Response" +respElement.getText());
        
        
        

        

}

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}