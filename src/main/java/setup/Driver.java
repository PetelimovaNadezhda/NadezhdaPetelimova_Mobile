package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static setup.SettingsDriver.*;

public class Driver extends TestProperties {

    protected static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;
    protected static DesiredCapabilities capabilities;

    static public String AUT; // (mobile) app under testing
    static public String SUT; // site under testing
    static public String TEST_PLATFORM;
    static public String DRIVER;
    static public String APP_NAME;

    public Driver(String prop) throws IOException {
        AUT = getProp("aut", prop);
        String t_sut = getProp("sut", prop);
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = getProp("platform", prop);
        DRIVER = getProp("driver", prop);
        APP_NAME = getProp("appName", prop);
    }

    public static void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case PLATFORM_ANDROID:
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, EMULATOR); // default Android emulator
                browserName = BROWSER_CHROME;
                break;
            case PLATFORM_IOS:
                browserName = BROWSER_SAFARI;
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(new File(AUT), APP_NAME);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
        } else {
            throw new Exception("Unclear type of mobile app");
        }
        // Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
        // Set an object to handle timeouts
        if (waitSingle == null) waitSingle = new WebDriverWait(driver(), 10);
    }

    //Singleton
    public static AppiumDriver driver() throws Exception {
        if (driverSingle == null) prepareDriver();
        return driverSingle;
    }

    protected WebDriverWait driverWait(){
        return waitSingle;
    }

}
