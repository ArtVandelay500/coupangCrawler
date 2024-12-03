package com.example.coupangcrawler;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.coupangcrawler
 * fileName       : SeleniumUtility
 * author         : Giant
 * date           : 2024-12-03
 * description    :
 * ======================================================================
 * DATE             AUTHOR            NOTE
 * ----------------------------------------------------------------------
 * 2024-12-03         Giant
 */
@Component
public class SeleniumUtility {

    public static WebDriver setupWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\홍현욱\\stuff\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run browser in headless mode
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return new ChromeDriver(options);
    }

    public static void bypassWebDriverDetection(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
    }
}
