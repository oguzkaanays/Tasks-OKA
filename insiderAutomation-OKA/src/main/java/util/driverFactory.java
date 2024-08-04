package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;


public class driverFactory {


    static WebDriver driver;


    public static void initializeDriver(String browser){

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Geçersiz tarayıcı: " + browser);
        }


    }
    public static WebDriver getDriver(){
        return driver;
    }
//public static WebDriver getDriver(String browser) {
//    WebDriver driver;
//    if (browser.equalsIgnoreCase("chrome")) {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    } else if (browser.equalsIgnoreCase("firefox")) {
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
//    } else {
//        throw new IllegalArgumentException("Geçersiz tarayıcı: " + browser);
//    }
//    return driver;
//}
//
//public static WebDriver getDriver(){
//    return driver;
//
//}



}
