package com.etouch.taf.util;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestGroup {

	@Test (groups =  "Sniff")
    public void validAuthenticationTest(){
        //System.out.println("1 Sniff + Regression" + System.getProperty("environment"));
        System.out.println("1 Sniff");
    }

    @Test (groups =  "Regression" )
    public void failedAuthenticationTest(){
        //System.out.println("2Regression-"+environment);
        System.out.println("2 Regression");
    }

    @Parameters("environment")
   // @Test (groups = { "Sniff"})
    public void newUserAuthenticationTest(String environment){
        //System.out.println("3Sniff-"+environment);
        System.out.println("1 test");
    }
    
    
    

}

