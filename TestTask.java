package WebTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import java.util.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestTask extends TestSourse {

    @Before
    public void SetUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

   @Test
    public void CheckTestLine ()  throws Exception {
       CheckLineValues("First bug","This is a test description");
    }


    @Test
     public void AddNewLine() throws Exception {
        GetSite();

        // Start editing
         FillLine();
         CheckLineValues("Webdriver","The note");
         DeleteLine("Webdriver");

    }


      @Test
        public void AddLineByMenu() throws Exception {
            String menuName = null;

            GetSite();

            menuName = FillMenu();  //call method that will fill menu
            Assert.assertEquals(menuName, "Add Bug");
            CheckLineValues("Web_name","Web_note");
            DeleteLine("Web_name");

        }

    @After
    public void TearDown() throws Exception {
        driver.close();
    }


}

