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

public class LocaltimeTesting {
    WebDriver webDriver;
    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description ="Local time is tested")
    public void localTime()  {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/local']")).click();
        webDriver.findElement(By.id("place-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@href='/local/india-hyderabad']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='content-block frame text-center']"));
        String place1 = "Hyderabad";
        Assert.assertTrue(elements.get(0).getText().contains(place1));
    }
}
