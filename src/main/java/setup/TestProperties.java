package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class TestProperties {
    Properties currentProps = new Properties();

    Properties getCurrentProps(String prop) throws IOException {
        System.out.println(prop);
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + prop);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String propKey, String prop) throws IOException {
        if (!currentProps.containsKey(propKey))
            currentProps = getCurrentProps(prop);
        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
