package setup;

public enum PropertyFile {
    NATIVE("/src/main/resources/nativetest.properties"),
    WEB("/src/main/resources/webtest.properties"),
    MOBILEFARMIOS("/src/main/resources/mobileFarm.properties"),
    MOBILEFARMANDROID("/src/main/resources/mobileFarmAndroid.properties");

    String prop;

    PropertyFile(String data) {
        prop = data;
    }
}
