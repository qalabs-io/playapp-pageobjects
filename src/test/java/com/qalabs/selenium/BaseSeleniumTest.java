package com.qalabs.selenium;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseSeleniumTest {


    private final Logger logger = LoggerFactory.getLogger(BaseSeleniumTest.class);

    private WebDriver driver;

    private String baseUrl = "http://localhost:9000";
    private String browser = "firefox";
    private String remote = "false";
    private String remoteHub = "http://localhost:4444/wd/hub";
    
    private String driverPathChrome = "C:\\formations\\selenium\\Selenium 2\\chromedriver.exe";
    private String driverPathIe = "C:\\formations\\selenium\\Selenium 2\\IEDriverServer.exe";

    private int implicteWait = 5; // en seconde

    @Before
    public void setUp() throws Exception {

    }

    protected String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl = System.getProperty("selenium.baseUrl");
            if (baseUrl == null) {
                logger.error("Veuillez renseigner la propriété -Dselenium.baseUrl");
                throw new NullPointerException("Veuillez renseigner la propriété -Dselenium.baseUrl");
            }
        }
        return baseUrl;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    @Before
    public void createDriver() throws Throwable {
        if (System.getProperty("selenium.browser") != null)
            browser = System.getProperty("selenium.browser");
        if (System.getProperty("selenium.baseurl") != null)
            baseUrl = System.getProperty("selenium.baseurl");  
        if (System.getProperty("selenium.remote") != null)
            remote = System.getProperty("selenium.remote");
        if (System.getProperty("selenium.remote.hub") != null)
            remoteHub = System.getProperty("selenium.remote.hub");
        if (System.getProperty("selenium.driver.ie") != null)
            driverPathIe = System.getProperty("selenium.driver.ie");
        if (System.getProperty("selenium.driver.chrome") != null)
            driverPathChrome = System.getProperty("selenium.driver.chrome");
        
        if ("true".equals(remote))
            initRemoteDriver();
        else
            initLocalDriver();
        
        driver.manage().timeouts().implicitlyWait(implicteWait, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) 
            driver.quit();
    }

    private void initLocalDriver() {
        switch (browser) {
            default:
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", driverPathIe);
                driver = new InternetExplorerDriver();
                break;
            case "htmlunit":
                driver = new HtmlUnitDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverPathChrome);
                driver = new ChromeDriver();
                break;
        }
    }

    private void initRemoteDriver() throws Throwable {
        DesiredCapabilities capabilities;
        if (browser != null) {
            switch (browser) {
                case "firefox":
                    capabilities = DesiredCapabilities.firefox();
                    break;
                case "chrome":
                    capabilities = DesiredCapabilities.chrome();
                    break;
                case "ie":
                    capabilities = DesiredCapabilities.internetExplorer();
                    break;
                case "htmlunit":
                    capabilities = DesiredCapabilities.htmlUnit();
                    break;
                default: {
                    logger.warn("Driver inconnu {}. On va utiliser HtmlUnit.", browser);
                    capabilities = DesiredCapabilities.htmlUnit();
                }
            }
        } else {
            logger.warn("Driver non précisé. On va utiliser HtmlUnit.", browser);
            capabilities = DesiredCapabilities.htmlUnit();
        }
        driver = new RemoteWebDriver(new URL(remoteHub), capabilities);
    }

}
