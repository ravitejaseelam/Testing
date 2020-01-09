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
import java.util.concurrent.TimeUnit;

public class CalenderTesting {
    WebDriver webDriver;
    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description = "Calender button is tested")
    public void checkCalendar() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='permanent-link btn']")).click();

        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"date-picker\"]/span"));
        element.click();
        WebElement monthDiv = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"));
        monthDiv.click();
        WebElement selectMonth = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[12]"));
        selectMonth.click();
        WebElement date = webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[4]/td[6]"));
        date.click();

        WebElement text1 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[1]/div[4]/div/div[2]/div/div[2]/div"));
        Assert.assertTrue(text1.getText().contains("Fri, Dec 25"));
        System.out.println(text1.getText());

        WebElement text2 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[2]/div[4]/div/div[2]/div/div[2]/div"));
        Assert.assertTrue(text2.getText().contains("Fri, Dec 25"));
        System.out.println(text2.getText());
    }
}
