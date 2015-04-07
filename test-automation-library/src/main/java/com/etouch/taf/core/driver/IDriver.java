package com.etouch.taf.core.driver;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.etouch.taf.core.TestBed;
import com.etouch.taf.core.exception.DriverException;


public interface IDriver {//extends RemoteWebDriver{
	
	public void buildDriver(TestBed profile) throws DriverException;
	public Object getDriver();
}
