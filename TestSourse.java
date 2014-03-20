package WebTests;
import static org.junit.Assert.assertEquals;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import com.opera.core.systems.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSourse {
    public WebElement element;
    public WebDriver driver;



    public WebElement findandtype (String cssloc, String Message) {

        WebElement result = null;

        List<WebElement> allfields = driver.findElements(By.cssSelector(cssloc));
        myforloop:
        for (WebElement web : allfields) {
            System.out.println(web.toString());
            try {
                web.sendKeys(Message);
                result = web;
                web.sendKeys(Keys.TAB);
                break myforloop;
            } catch (Exception e) {}
        }
        return result;
    }



    public WebElement findandclick (String cssloc) {

        WebElement result = null;

        List<WebElement> allfields = driver.findElements(By.cssSelector(cssloc));
        myforloop:
        for (WebElement web : allfields) {
            System.out.println(web.toString());
            try {
                web.click();
                result = web;
                // web.sendKeys(Keys.TAB);
                break myforloop;
            } catch (Exception e) {}
        }
        return result;
    }



    public void GEtSite (String Sitename) {

        if (Sitename.isEmpty()) {
            driver.get("http://testtask.22web.org/task.html");
        }

        System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Test Task for Automation");

        driver.switchTo().frame("main");
    }



    public void DeleteLine(String name) throws Exception {
        String checkelement = null;

        GEtSite("");
        //Get all lines
        List<WebElement> allRows = driver.findElements(By.tagName("tr"));

        //Search line wich contains keyword
        for (WebElement row : allRows) {
            if (row.getText().contains(name)) {
                element = row;
            }
        }

        Assert.assertTrue(element.getText().contains(name));
        System.out.println(element.toString());
        //Click on line and delete it
        element.click();
        driver.findElement(By.xpath("//span[text()='Delete']")).click();    //Delete button click
        driver.findElement(By.xpath("//span[text()='Yes']")).click();      //Yes confirm

        //Wait for table will appear afte preloader
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("x-grid-table"))); // wait for table will load, and save done
        System.out.println("Line was deleted");


    }




    public void CheckTheLine(String name1, String name2) throws Exception {

        GEtSite("");
        String eq1 = null,eq2 = null;

        WebElement table = driver.findElement(By.className("x-grid-table"));

        List<WebElement> allRows = table.findElements(By.tagName("tr"));

        // 1. Rewrite with assert
        // 2. Rewrite without for and if
        for (WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                System.out.println(cell.getText());
                if (cell.getText().equals(name1)) {
                    eq1 = cell.getText();
                }
                if (cell.getText().equals(name2)) {
                    eq2 = cell.getText();
                }
            }
        }

        Assert.assertEquals(eq1,name1);
        Assert.assertEquals(eq2,name2);

    }


    public List<String> GetLines() throws Exception {

        GEtSite("");
        List<String> AllRows = new ArrayList<String>();
        //read all table
        WebElement table = driver.findElement(By.className("x-grid-table"));
        //convert it to list of string lines
        List<WebElement> allRows = table.findElements(By.tagName("tr"));

        for (WebElement row : allRows) {
            AllRows.add(row.getText());
           }

        return   AllRows;
    }



    public boolean CheckStringInList (List<String> CheckList, String CheckName ) {
        //Return true if line contain target string
        Boolean answer = false;
        for (String line : CheckList) {
             if (line.contains(CheckName))   {
                 answer = true;
                 System.out.println(line);
             }
        }
        return answer;
    }




    public String FillMenu () throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Add in form']")).click();    //add button click

        //wait for window will load - 20 sec maximum
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".x-window-header-text,.x-window-header-text-default")));

        //get and print window name
        String WindowName = driver.findElement(By.cssSelector(".x-window-header-text,.x-window-header-text-default")).getText();
        System.out.println("The menu is "+WindowName);

        //Fill form boxes
        findandclick(".x-form-field,.x-form-checkbox"); // click on checkbox
        driver.findElement(By.name("name")).sendKeys("Web_name");
        driver.findElement(By.name("notes")).sendKeys("Web_note");
        driver.findElement(By.name("priority")).sendKeys("3");
        driver.findElement(By.name("due")).sendKeys("2014-01-19");

        //clik ok
        driver.findElement(By.xpath("//span[text()='OK']")).click();

        System.out.println("Added by menu successfull");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("x-grid-table"))); // wait for table will load, and save done

        return WindowName;
    }




    public void Fillline () {
        //click button Add
        driver.findElement(By.xpath("//span[text()='Add']")).click();

        // fill all boxes
        driver.findElement(By.cssSelector(".x-form-field,.x-form-text"));
        element = findandtype(".x-form-field,.x-form-text","Webdriver");
        findandtype(".x-form-field,.x-form-text","The note");
        findandtype(".x-form-field,.x-form-text,.x-form-focus","2");
        findandtype(".x-form-field,.x-form-text,.x-form-focus","01/01/2099");

        //click on todays date button
        findandclick(".x-trigger-index-0,.x-form-trigger,.x-form-date-trigger,.x-form-trigger-last");

        //click button Apply
        driver.findElement(By.xpath("//span[text()='Apply']")).click();
    }

}
