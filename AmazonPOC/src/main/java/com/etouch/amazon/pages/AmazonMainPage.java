package com.etouch.amazon.pages;

 import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.xml.bind.ParseConversionEvent;

import org.apache.commons.logging.Log;
import org.apache.poi.ss.usermodel.Textbox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.etouch.taf.core.TestBedManager;
import com.etouch.taf.core.config.TestBedManagerConfiguration;
import com.etouch.taf.core.datamanager.excel.TafExcelDataProvider;
import com.etouch.taf.core.datamanager.excel.TestParameters;
import com.etouch.taf.core.datamanager.excel.annotations.IExcelDataFiles;
import com.etouch.taf.core.datamanager.excel.annotations.ITafExcelDataProviderInputs;
import com.etouch.taf.core.exception.PageException;
import com.etouch.taf.core.resources.ObjectType;
import com.etouch.taf.core.resources.ObjectValType;
import com.etouch.taf.core.resources.WaitCondition;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.util.SoftAssertor;
import com.etouch.amazon.common.BaseTest;
import com.etouch.amazon.common.CommonPage;
import com.etouch.taf.webui.selenium.webelement.*;
import com.etouch.taf.webui.selenium.WebPage;

public class AmazonMainPage extends CommonPage {
	
	//private WebDriver webDriver;
	
	
	public AmazonMainPage(String sbPageUrl, WebPage webPage) {
		super(sbPageUrl, webPage);
  
		
		//webDriver = webPage.getDriver();
		CommonUtil.sop("webDriver in AmazonMainPage "+ webPage.getDriver());
				
			//startRecording();
	//	if(TestBedManager.INSTANCE.getCurrentTestBed().getDevice().getName() != null){
			
			loadPage();		
			
		//}
	}

	public String getPageUrl() {
		return webPage.getCurrentUrl();
	}

	
	public void PreSignIn() throws InterruptedException {
		final int MAX_WAIT = 20;
		try {
			/*((Button) webPage.findObject(ObjectType.Button,
				MainPageElements.Nav_SignIn_Title_XPATH, ObjectValType.XPATH, MAX_WAIT,
					WaitCondition.CLICKABLE)).click();*/ 
			CommonUtil.sop("this is presignIn");
			((Button) webPage.findObject(ObjectType.Button,
					".//*[@id='nav-your-account']/span[2]", ObjectValType.XPATH, MAX_WAIT,
						WaitCondition.CLICKABLE)).click();
			
		}	
		catch (Exception e) {
			CommonUtil.sop("Exception is here");
			CommonUtil.sop(e.getMessage());			
		}
	}
			
			
			public void SignIn() throws InterruptedException {
				final int MAX_WAIT = 20;
				try {
					((TextBox) webPage.findObject(ObjectType.TextBox,
						"ap_email", ObjectValType.ID))
							.enterText("");
	
						
					((TextBox) webPage.findObject(ObjectType.TextBox,
							"ap_password", ObjectValType.ID, MAX_WAIT,
							WaitCondition.VISIBLE)).enterText("");
					
				
					((Button) webPage.findObject(ObjectType.Button,
						"signInSubmit-input", ObjectValType.ID, MAX_WAIT,
							WaitCondition.CLICKABLE)).click();
					
						
				} 
					catch (Exception e) {
						
					CommonUtil.sop("exception 2");	
					CommonUtil.sop(e.getMessage());
				}
			}
		}




