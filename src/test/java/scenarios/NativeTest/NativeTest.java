package scenarios.NativeTest;

import org.testng.annotations.Test;
import setup.Driver;

import java.io.IOException;

import static org.testng.Assert.*;
import static scenarios.NativeTest.PageObjectContactManagerApp.*;
import static setup.PropertyFile.NATIVE;

@Test(groups = "native")
public class NativeTest extends Driver {

    protected NativeTest() throws IOException {
        super(NATIVE.prop);
    }

    @Test(description = "Just click on button 'Add contact'")
    public void simplestTest() {
        driverSingle.findElement(addBtn).click();

        assertEquals(driverSingle.findElement(title).getText(), TITLE_NAME);
        assertTrue(driverSingle.findElement(contactName).isDisplayed());
        assertTrue(driverSingle.findElement(targetAccount).isDisplayed());
        assertTrue(driverSingle.findElement(contactPhone).isDisplayed());
        assertNotNull(driverSingle.getKeyboard());
        System.out.println("Simplest Appium test done");
    }
}
