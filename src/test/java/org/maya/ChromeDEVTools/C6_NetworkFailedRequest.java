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
import org.openqa.selenium.devtools.v114.network.model.ErrorReason;
import org.openqa.selenium.devtools.v114.network.model.Request;
import org.openqa.selenium.devtools.v114.network.model.Response;
import org.testng.annotations.Test;

public class C6_NetworkFailedRequest {
	@Test
	public void Test1() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(opt);
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setDeviceMetricsOverride(430, 932, 50, true, java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
		Optional<List<RequestPattern>> pattern = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
		devTools.send(Fetch.enable(pattern, Optional.empty()));
		devTools.addListener(Fetch.requestPaused(), request ->
		{
			devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
			
		});
		devTools.addListener(Network.requestWillBeSent(), request ->
		{
			Request req = request.getRequest();
			System.out.println(req.getUrl());
			System.out.println(req.getHeaders());
		});
		devTools.addListener(Network.responseReceived(), response ->
		{
			Response res = response.getResponse();
			if(res.getStatus().toString().startsWith("4")) {
				System.out.println(res.getUrl()+ "is failing with Status Code" + res.getStatus());
			}
		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector(".navbar-toggler-icon")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()=\"Library\"]")).click();
	}
}
