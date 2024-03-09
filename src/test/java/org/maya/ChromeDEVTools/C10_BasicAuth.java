package org.maya.ChromeDEVTools;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

import java.net.URI;

public class C10_BasicAuth {
	@Test
	public void Test1() {
		System.setProperty("webdriver.chrome.driver", "C:\\JarsForTestAut\\driver6\\chromedriver.exe");
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		opt.setBinary("C:\\JarsForTestAut\\chrome-win64\\chrome-win64\\chrome.exe");
		ChromeDriver driver = new ChromeDriver(opt);
		
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
		((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
		driver.get("https://httpbin.org/basic-auth/foo/bar");
		
	}
}
