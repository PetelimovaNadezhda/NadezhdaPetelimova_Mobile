package scenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

@Test(groups = {"native", "web"})
public class Hooks extends Driver {

    public Hooks(String prop) throws IOException {
        super(prop);
    }

    @BeforeSuite(alwaysRun = true, description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        System.out.println("WHAT&");
        prepareDriver();
        System.out.println("Driver prepared");

    }

    @AfterSuite(alwaysRun = true, description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }
}
