package org.maya.ChromeDEVTools;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;

public class C3_LocalizationTest {
	@Test
	public void Test1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(opt);
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		Map<String,Object> cordinates = new HashMap<String,Object>();
		cordinates.put("latitude", 39);
		cordinates.put("longitude", 5);
		cordinates.put("accuracy", 1);
		driver.executeCdpCommand("Emulation.setGeolocationOverride", cordinates);
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
				
	}
}
