package scenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static setup.Driver.driver;
import static setup.Driver.prepareDriver;

@Test(groups = {"native", "web"})
public class Hooks{

    @BeforeSuite(alwaysRun = true, description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");

    }

    @AfterSuite(alwaysRun = true, description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }
}
