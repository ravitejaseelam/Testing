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

public class AllTests {
    WebDriver webDriver;
    DateFormat dateFormat = new SimpleDateFormat("kk:mm");
    @BeforeClass
    public void openSavvytimeHome()
    {

        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get("https://savvytime.com/");

    }
    @Test(description = "Converter Swap Button is tested")
    public void converterSwap() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        String[] place1 = {"Hyderabad","Graz"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
        Assert.assertTrue(elements.get(1).getText().contains(place1[1]));
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='swap-tz btn']")).click();
        elements = webDriver.findElements(By.xpath("//div[@class='table-time row']"));
        Assert.assertTrue(elements.get(0).getText().contains(place1[1]));
        Assert.assertTrue(elements.get(1).getText().contains(place1[0]));
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
        WebElement text2 = webDriver.findElement(By.xpath("//*[@id=\"converter-view\"]/div[2]/div[4]/div/div[2]/div/div[2]/div"));
        Assert.assertTrue(text2.getText().contains("Fri, Dec 25"));
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
    @Test(description = "Local time is tested")
    public void localTime() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/local']")).click();
        webDriver.findElement(By.id("place-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@href='/local/india-hyderabad']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='content-block frame text-center']"));
        String place1 = "Hyderabad";
        Assert.assertTrue(elements.get(0).getText().contains(place1));
    }
    @Test(description ="Permanent Link Creation is Tested ")
    public void converterLink() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/converter']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Hyderabad");
        webDriver.findElement(By.xpath("//a[@data-id='india-hyderabad']")).click();
        webDriver.findElement(By.id("time-search")).sendKeys("Austria");
        webDriver.findElement(By.xpath("//a[@data-id='austria-graz']")).click();
        webDriver.findElement(By.xpath("//div[@class='btn-group']//a[@class='permanent-link btn']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='col-xs-12 collapse in']//div[@class='row share-url-wrap']"));
        String[] place1 ={"Include Time","Include Date"};
        Assert.assertTrue(elements.get(0).getText().contains(place1[0]));
    }

    @Test(description = "Timer is tested")
    public void timer() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/timers']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='big-services-box col-xs-12 col-sm-6 ']"));
        String place1 = "Timer";
        Assert.assertTrue(elements.get(0).getText().contains(place1));
    }

    @Test(description = "Time is verified")
    public void timeTesting() throws InterruptedException {
        webDriver.findElement(By.xpath("//nav[@id='main-menu']//a[@href='/']")).click();
        webDriver.findElement(By.xpath("//div[@class='hour-switch']//button[@data-format='format24']")).click();
        List<WebElement> elements = webDriver.findElements(By.xpath("//div[@class='content-block frame']"));
        String dateString = dateFormat.format(new Date());
        Assert.assertTrue(elements.get(0).getText().contains(dateString));
        System.out.println(elements.get(0).getText());
    }
}
