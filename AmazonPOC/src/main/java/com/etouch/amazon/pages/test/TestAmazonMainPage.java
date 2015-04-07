package com.etouch.amazon.pages.test;

import javax.validation.constraints.AssertTrue;

import com.etouch.taf.core.resources.WaitCondition;
import com.etouch.taf.tools.defect.IDefectManager;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.util.SoftAssertor;
import com.etouch.amazon.common.BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.etouch.taf.core.TestBedManager;
import com.etouch.taf.core.datamanager.excel.annotations.IExcelDataFiles;
import com.etouch.taf.core.datamanager.excel.TafExcelDataProvider;
import com.etouch.taf.core.datamanager.excel.TestParameters;
import com.etouch.taf.core.datamanager.excel.annotations.ITafExcelDataProviderInputs;
import com.etouch.taf.core.exception.PageException;
import com.etouch.amazon.common.BaseTest;
import com.etouch.amazon.pages.AmazonMainPage;
import com.etouch.taf.webui.selenium.WebPage;

import java.io.IOException;
import java.awt.AWTException;

@IExcelDataFiles(excelDataFiles = { "file1=stageData" })
public class TestAmazonMainPage extends BaseTest {
	private String url = "http://www.amazon.com/";
	private WebPage webPage;
	private AmazonMainPage mainPage;

	//required for jira
	String url3 =TestBedManager.INSTANCE.getDefectConfig().getUrl3();
	String issueUrl = TestBedManager.INSTANCE.getDefectConfig().getIssueUrl();
	String username = TestBedManager.INSTANCE.getDefectConfig().getUsername();
	String password = TestBedManager.INSTANCE.getDefectConfig().getPassword();
	String keys = TestBedManager.INSTANCE.getDefectConfig().getKeys();
	
	//required for rally
	String PROJECT_ID = TestBedManager.INSTANCE.getDefectConfig().getProjectId();
	String DEFECT_OWNER = TestBedManager.INSTANCE.getDefectConfig().getDefectOwner();
	
	@BeforeClass
	public void prepareBeforeClass() throws Exception {
		try {
			
			webPage = new WebPage();
			mainPage = new AmazonMainPage(url, webPage);
		}

		catch (Exception e) {
			CommonUtil.sop("errr is " + e);
			SoftAssertor.addVerificationFailure(e.getMessage());
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {

		WebDriver driver = (WebDriver) TestBedManager.INSTANCE
				.getCurrentTestBed().getDriver();
		CommonUtil.sop("At tear down "+ driver);
		try {
			driver.wait(150000);
		} catch (Exception e) {
			//SoftAssertor.addVerificationFailure(e.getMessage());
		}
		driver.close();
		driver.quit();
//		mainPage.stopRecording();

	}

	// Testing for preSigIn 
	@Test(dataProvider = "tafDataProvider", dataProviderClass = TafExcelDataProvider.class, priority = 1)
	@ITafExcelDataProviderInputs(excelFile = "file1", excelsheet = "CISCO", dataKey = "Authentication")
	public void testPreSignIn(TestParameters inputs) throws PageException {
		try {
			CommonUtil.sop("Starting test case 1");
			
			CommonUtil.sop (" test data from xls : " + inputs.getParamMap().get("uname") );
			String actualURL = "";
			String expectedURL = "";
			mainPage.PreSignIn();
			actualURL = mainPage.getPageUrl();
			
		if (actualURL == "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26ref_%3Dgno_signin");
		{
			
			CommonUtil.sop("this is same for test 1");
		}

		} catch (AssertionError e) {
			CommonUtil.sop("the test case 1 failed");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
			
		}
		catch(Exception e)
		{
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	public void testSignIn() throws PageException {
		try {
			CommonUtil.sop("Starting test case 2");
			String actualURL = "";
			String expectedURL = "";

			// actualURL =
			// "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fcss%2Fhomepage.html%3Fie%3DUTF8%26ref_%3Dgno_yam_ya";
			mainPage.SignIn();
			CommonUtil.sop("this is here 2");
			actualURL = webPage.getCurrentUrl();
			expectedURL = "https://www.amazon.com/gp/yourstore/home?ie=UTF8&ref_=gno_signin&";
			
			if (actualURL == "https://www.amazon.com/ap/signin?_encoding=UTF8&openid.assoc_handle=usflex&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26ref_%3Dgno_signin");
			{
				
				CommonUtil.sop("this is same for test 2");
			}
			
			//Assert.assertEquals(actualURL,"https://www.google.com", "URL did not matched");
			

		} catch (AssertionError e) {
			CommonUtil.sop("the test case 2 failed");
			//defect logging method for Rally 
			 //mainPage.logAndCreateADefect(TestBedManager.INSTANCE.getDefect(),DEFECT_PROP,SEARCH_TEST_ID, PROJECT_ID,  STORY_ID, SEARCH_DEFECT_NAME, DEFECT_SEVERITY,DEFECT_OWNER,DEFECT_NOTES,e.getMessage());
			// mainPage.getScreenshot();			 
			 //mainPage.logAndCreateADefect(TestBedManager.INSTANCE.getDefect(),"","26818545560", TestBedManager.INSTANCE.getDefectConfig().getProjectId(),  "26818539785", "Sample Defect1", "Minor Problem",TestBedManager.INSTANCE.getDefectConfig().getDefectOwner(),"Yahoo page Assertion error",e.getMessage());
			
			//defect logging method for jira
			//mainPage.logAndCreateADefect(TestBedManager.INSTANCE.getDefect(),url3, issueUrl, username, password,keys);
			
			SoftAssertor.addVerificationFailure(e.getMessage());
		    e.printStackTrace();

		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}
		catch(Exception e)
		{
			SoftAssertor.addVerificationFailure(e.getMessage());
			e.printStackTrace();
		}

	}

}
