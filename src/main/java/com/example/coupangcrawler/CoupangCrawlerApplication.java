package com.example.coupangcrawler;

import org.openqa.selenium.WebDriver;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CoupangCrawlerApplication {



    public static void main(String[] args) {
        // Directly use SeleniumUtility to run the WebDriver setup and crawl
        WebDriver driver = SeleniumUtility.setupWebDriver();

        try {
            driver.get("https://www.coupang.com");
            SeleniumUtility.bypassWebDriverDetection(driver);

            // Perform crawling actions here...
            System.out.println("Page title: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
