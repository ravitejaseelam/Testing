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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeTest {
    WebDriver webDriver;
    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description = "Time is verified")
    public void timeTesting() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/']")).click();
        webDriver.findElement(By.xpath("//div[@class='hour-switch']//button[@data-format='format24']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='content-block frame']"));
        DateFormat dateFormat = new SimpleDateFormat("kk:mmaaa");

        String dateString = dateFormat.format(new Date());
        Assert.assertTrue(elements.get(0).getText().contains(dateString));
        System.out.println(elements.get(0).getText());
    }
}
