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

public class ConverterTesting {
    WebDriver webDriver;

    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description = "Converter Option is tested")
    public void converterBasic() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        String[] place1 = {"Hyderabad","Graz"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
        Assert.assertTrue(elements.get(1).getText().contains(place1[1]));
    }
}
