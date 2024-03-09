package org.maya.ChromeDEVTools;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.bidi.log.LogEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.devtools.v114.fetch.Fetch;
import org.openqa.selenium.devtools.v114.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v114.network.Network;
import org.openqa.selenium.devtools.v114.network.model.ConnectionType;
import org.openqa.selenium.devtools.v114.network.model.ErrorReason;
import org.openqa.selenium.devtools.v114.network.model.Request;
import org.openqa.selenium.devtools.v114.network.model.Response;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

public class C11_ConsoleLogCapture {
	@Test
	public void Test1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(opt);
		DevTools devTools = driver.getDevTools();
		//devTools
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.linkText("Browse Products")).click();
		driver.findElement(By.linkText("Selenium")).click();
		driver.findElement(By.cssSelector(".add-to-cart")).click();
		driver.findElement(By.linkText("Cart")).click();
		driver.findElement(By.id("exampleInputEmail1")).clear();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("2",Keys.ENTER);
		
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
		List<org.openqa.selenium.logging.LogEntry> logs = entry.getAll();
		for(org.openqa.selenium.logging.LogEntry e : logs) {
			System.out.println(e.getMessage());
		}
	}
}
