package com.ravi;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimerTest {
    WebDriver webDriver;

    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description = "com.ravi.Timer is tested")
        public void timer() throws InterruptedException {
            webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/timers']")).click();
            List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='big-services-box col-xs-12 col-sm-6 ']"));
            String place1 = "Timer";
            Assert.assertTrue(elements.get(0).getText().contains(place1));
        }
}
