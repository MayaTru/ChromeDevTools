package org.maya.ChromeDEVTools;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
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
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

public class C9_NetworkOffline {
	@Test
	public void Test1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(opt);
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		long starttime = System.currentTimeMillis();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.send(Network.emulateNetworkConditions(true, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET)));
		devTools.addListener(Network.loadingFailed(), loadingFailed ->
		{
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		//driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		Thread.sleep(3000);
		long endtime = System.currentTimeMillis();
		System.out.println(endtime-starttime);
	}
}
