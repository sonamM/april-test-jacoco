package com.etouch.taf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;

import org.apache.commons.logging.Log;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;



public class SoftAssertor {
	
	static Log log=LogUtil.getLog(SoftAssertor.class);
	
	private static Map<ITestResult,List<String>> verificationFailuresMap = new HashMap<ITestResult,List<String>>();
	
	 
    public static void assertTrue(boolean condition, String errMsg) {
    	try {
    		Assert.assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		
    	}
    }
 
    public static void assertFalse(boolean condition, String errMsg)  {
    	try {
    		Assert.assertFalse(condition);
	} catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		
	}
    }
 
    public static void assertEquals(Object actual, Object expected, String errMsg)  {
    	try {
    		Assert.assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(errMsg + " Exception msg: "+e.getMessage());
    		
		}
    }
    
    public static void assertTrue(boolean condition) {
    	try {
    		Assert.assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		
    	}
    }
 
    public static void assertFalse(boolean condition)  {
    	try {
    		Assert.assertFalse(condition);
	} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		
	}
    }
 
    
    public static void assertNotNull(Object actual, String errMsg)  {
    	try {
    		Assert.assertNotNull(actual);
		} catch(Throwable e) {
    		addVerificationFailure(errMsg+" Exception msg: "+e.getMessage());    		
		}
    }
 
	public static List<String> getVerificationFailures() {
		List<String> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<String>() : verificationFailures;
	}
	
	public static void addVerificationFailure(String e) {
		List<String> verificationFailures = getVerificationFailures();
		verificationFailures.add(e);
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
	}

	public static void assertFail(String errMsg) {
		
		try {
    		Assert.fail(errMsg);
		} catch(Throwable e) {
    		addVerificationFailure(errMsg+" Exception msg: "+e.getMessage());    		
		}
		
	}

	public static void assertEquals(Object actual, Object expected)  {
    	try {
    		Assert.assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(" Exception msg: "+e.getMessage());
    		
		}
    }

	public static void assertNotNull(Object actual) {
		try {
    			Assert.assertNotNull(actual);
			} catch(Throwable e) {
    		addVerificationFailure("Exception msg: "+e.getMessage());    		
		}
		
	}
	
	public static void displayAssertErrors()
	{
		if(verificationFailuresMap!=null && verificationFailuresMap.size()>0 )
		{
			Set keys = verificationFailuresMap.keySet();
			java.util.Iterator<ITestResult> itr = keys.iterator();
			while(itr.hasNext())
			{
				List<String> errorList = verificationFailuresMap.get(itr.next());
				if(errorList!=null && errorList.size()>0)
				{
					for(int index=0; index<errorList.size(); index++)
					{
						log.error(errorList.get(index));					
					}
					
				}
			}
			
			Assert.fail("Exception during execution of Test methods");
		}
	}
	

}
