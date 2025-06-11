package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

     public static void openURL(ChromeDriver driver,String url){
      try{
        driver.get(url);
      }
      catch(Exception e){
         e.printStackTrace();
      }
     }

     public static void enterText(WebElement box,String text){
      try{
      box.clear();
      box.sendKeys(text);
      }
      catch(Exception e){
         e.printStackTrace();
      }

     }


     public static void selectItem(WebElement selectElement){
      try{
      selectElement.click();
      }
       catch(Exception e){
         e.printStackTrace();
      }
     }

     public static void pause(long time) throws InterruptedException{
      try{
      Thread.sleep(time);
      }
       catch(Exception e){
         e.printStackTrace();
      }
     }

     public static long getEpochTime(){
      return System.currentTimeMillis()/1000;
       
     }

     public static void selectRadioOption(List<WebElement> list, String option){
      try{
         
         String option_trimmed=option.replace(" ", "");
         System.out.println("option to select ----after trim..."+option_trimmed);
         for(WebElement e:list){
            String str=e.getText().replace(" ", "");
            System.out.println("options......after trim..."+str);
            if(option_trimmed.equals(str)){
            System.out.println("option......after matching..."+str);

               selectItem(e);

            }
            else continue;
         }   

      }
      catch(Exception e){
         e.printStackTrace();
      }
     }

     public static void selectdropdownOption(List<WebElement> list,String saluteToSelect){
      try{
         for(WebElement e:list){
           if( e.getText().equals(saluteToSelect)){
           selectItem(e);
           break;
           }
           else continue;
         }
         
      }
      catch(Exception e){
         e.printStackTrace();
      }
     }
     
}
