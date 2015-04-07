package com.etouch.taf.tools.jira;

//import com.sun.jersey.core.util.Base64;
import org.apache.commons.codec.binary.Base64;
/**
 * Interface for holding Jira integration constants.
 * @author eTouch Systems Corporation
 * @version 1.0
 *
 */

public final class JiraConstants {
	

	public static String DEFECT = "defect";
	public static String CREATE ="Create";
	public static String URL = "http://localhost:8080";
	public static String CREATE_ISSUE_URL = "http://localhost:8080/rest/api/2/issue";
	public static String USERNAME = "sam123";
	public static String PASSWORD = "Dobhi*tage2";
	public static String newKey = "";
	public static String KEY = "SAT";
	public static String summary = "REST Test1";
	}
//can also add create issueData fields.


//public static String AUTH = new String(Base64.encode("sam123:Dobhi*tage2"));
