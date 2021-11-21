package com.sample.test.demo;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    private Configuration config;
    protected WebDriver driver;
    protected String url;
    protected HashMap<String,Locator> locators;

    @BeforeClass(alwaysRun = true)
    public void init() throws Throwable {
        config = new Configuration();
        url = config.getUrl();
        readLocators();
        initializelDriver();
        navigateToSite();
    }
    
    private String findNotNullValue(String arr[]) {
    	for(int i=1;i<arr.length-1;i++) {
    		if(!"".equals(arr[i]))
    			return arr[i];
    	}
    	return "ID";
    }
    public By getLocators(String key) {
    	switch(locators.get(key).getLocatorType().toLowerCase()) {
    	case "id":
    		return By.id(locators.get(key).getLocatorValue());
    		
    	case "xpath":
    		return By.xpath(locators.get(key).getLocatorValue());
    	default:
    		return By.id(locators.get(key).getLocatorValue());
    	}
    }
    public void pause(int sec) {
    	try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void readLocators() {
    	locators = new HashMap<>();
    	try {
			Scanner sc = new Scanner(new File("src/test/resources/files/locators.txt"));
			sc.nextLine();
			while(sc.hasNextLine()) {
				String line = sc.nextLine().trim();
				if(sc==null || "".equals(line))
					continue;
				String arr[] = line.split(" ");
				locators.put(arr[0], new Locator(findNotNullValue(arr), arr[arr.length-1].replaceAll("\"", "")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    private void navigateToSite() {
        driver.get(url);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        try {
            driver.quit();

        } catch (Exception e) {
        }
    }

    private void initializelDriver() {
        if (config.getBrowser().equalsIgnoreCase("chrome")) {
            if (config.getPlatform().equalsIgnoreCase("mac")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver/mac/chromedriver");
            } else {
                System.setProperty("webdriver.chrome.driver",
                        "src/test/resources/chromedriver/windows/chromedriver.exe");
            }
            driver = new ChromeDriver();
        }
        else {
            fail("Unsupported bfrowser " + config.getBrowser());
        }
       
    }


}
