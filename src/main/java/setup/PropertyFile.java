package setup;

public enum PropertyFile {
    NATIVE("/src/main/resources/nativetest.properties"),
    WEB("/src/main/resources/webtest.properties");

    String prop;

    PropertyFile(String data) {
        prop = data;
    }
}
