package scenarios.WebTests;

import io.restassured.RestAssured;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks;
import setup.Driver;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static setup.PropertyFile.WEB;

@Test(groups = "web")
public class WebTest extends Hooks {
    protected WebTest() throws IOException {
        super(WEB.prop);
    }

    @Test(description = "Open website")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));
        assertEquals(200, RestAssured.get(SUT).statusCode());
        assertEquals(driverSingle.getTitle(), "Internet Assigned Numbers Authority");
        //assertEquals(driverSingle.findElementById("intro").getText(), "The global coordination of the DNS Root, IP addressing, and other Internet protocol resources is performed as the Internet Assigned Numbers Authority (IANA) functions.");
        System.out.println("Site opening done");
    }
}