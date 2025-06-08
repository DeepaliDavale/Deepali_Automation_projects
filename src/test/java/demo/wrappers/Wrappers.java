package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static demo.TestCases.*;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static void openURL(String url){
        driver.get(url);

     }

     public static void enterText(WebElement box,String text){
      box.clear();
      box.sendKeys(text);

     }

     public static void selectItem(WebElement selectElement){
      selectElement.click();
     }

     public static void pause(long time) throws InterruptedException{
      Thread.sleep(time);
     }
     
}
