package scenarios.NativeTest;

import org.openqa.selenium.By;

public class PageObjectContactManagerApp {
    public static final String CONTACT_BUTTON = "addContactButton";
    public static final String TITLE_NAME = "Add Contact";
    public static final String app_package_name = "com.example.android.contactmanager:id/";
    public static final String ANDROID_ID_TITLE = "android:id/title";
    public static final String CONTACT_PHONE = "contactPhoneEditText";
    public static final String CONTACT_NAME ="contactNameEditText";

    public static By addBtn = By.id(app_package_name + CONTACT_BUTTON);
    public static By title = By.id(ANDROID_ID_TITLE);

    public static final String TARGET_ACCOUNT ="accountSpinner";
    public static By targetAccount =  By.id(app_package_name + TARGET_ACCOUNT);
    public static By contactName =  By.id(app_package_name + CONTACT_NAME);
    public static By contactPhone = By.id(app_package_name + CONTACT_PHONE);
}

