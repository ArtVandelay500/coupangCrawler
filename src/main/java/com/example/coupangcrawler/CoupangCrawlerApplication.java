package com.example.coupangcrawler;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;


@SpringBootApplication
public class CoupangCrawlerApplication {



    public static void main(String[] args) {
        // Directly use SeleniumUtility to run the WebDriver setup and crawl
        WebDriver driver = SeleniumUtility.setupWebDriver();

        try {
            driver.get("https://www.coupang.com");
            // Delete all cookies
            driver.manage().deleteAllCookies();

            // Refresh the page to apply the logout
            driver.navigate().refresh();

            Thread.sleep(2000);
            SeleniumUtility.bypassWebDriverDetection(driver);
            WebElement logoutBtn = driver.findElement(By.cssSelector("li#logout > a"));
            logoutBtn.click();

            WebElement loginBtn = driver.findElement(By.cssSelector("li#login > a"));

            loginBtn.click();
            WebElement usernameField = driver.findElement(By.id("login-email-input"));
            WebElement passwordField = driver.findElement(By.id("login-password-input"));

            usernameField.sendKeys("ghdgusdnr123@naver.com");
            passwordField.sendKeys("oa2426mm");
            WebElement loginSubmitBtn = driver.findElement(By.className("login__button--submit"));
            loginSubmitBtn.click();
            Thread.sleep(5000);


            // Verify login success
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dashboardElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li#logout > a"))
            );
            if (dashboardElement.isDisplayed()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }

        } catch (Exception e) {
            if(e instanceof NoSuchElementException){
               WebElement element = driver.findElement(By.cssSelector("li#logout > a"));
                if (element != null) {
                    System.out.println("logged In already");
                }else {
                    System.out.println("Login failed!");
                }
            }
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
