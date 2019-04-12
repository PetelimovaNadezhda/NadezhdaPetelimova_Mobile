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

    private static String AUT; // (mobile) app under testing
    protected static String SUT; // site under testing
    private static String TEST_PLATFORM;
    private static String DRIVER;
    private static String DEVICE_NAME;
    private static String APP_ACTIVITY;
    private static String APP_PACKAGE;
    private static String UDID;
    static public String APP_NAME;


    public Driver(String prop) throws IOException {
        AUT = getProp("aut", prop);
        String t_sut = getProp("sut", prop);
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = getProp("platform", prop);
        DRIVER = getProp("driver", prop);
        DEVICE_NAME = getProp("devicename", prop);
        APP_PACKAGE = getProp("appPackage", prop);
        APP_ACTIVITY = getProp("appActivity", prop);
        UDID = getProp("udid", prop);
        APP_NAME = getProp("appName", prop);
    }

    public void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case PLATFORM_ANDROID:
                 browserName = BROWSER_CHROME;
                break;
            case PLATFORM_IOS:
                browserName = BROWSER_SAFARI;
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.UDID, UDID); // or Android emulator, or real device
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(new File(AUT), APP_NAME);
            capabilities.setCapability("appPackage", APP_PACKAGE);
            capabilities.setCapability("appActivity", APP_ACTIVITY);
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
    public AppiumDriver driver() throws Exception {
        if (driverSingle == null) prepareDriver();
        return driverSingle;
    }

    protected WebDriverWait driverWait() {
        return waitSingle;
    }

}
