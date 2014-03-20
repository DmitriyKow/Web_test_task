package WebTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TestTask extends TestSourse {

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

   @Test
    public void CheckTestLine ()  throws Exception {
        List<String> AllRows = new ArrayList<String>();
        AllRows = GetLines();
        assertTrue(CheckStringInList(AllRows,"First bug"));
        assertTrue(CheckStringInList(AllRows,"This is a test description"));

    }


    @Test
     public void AddNewLine() throws Exception {
        GEtSite("");

        // Start editing
         Fillline();
         CheckTheLine("Webdriver","The note");
         DeleteLine("Webdriver");

    }


      @Test
        public void AddLinebyMenu() throws Exception {
            String menuName = null;

            GEtSite("");

            menuName = FillMenu();  //call method that will fill menu
            Assert.assertEquals(menuName, "Add Bug");
            CheckTheLine("Web_name","Web_note");
            DeleteLine("Web_name");

        }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }


}

