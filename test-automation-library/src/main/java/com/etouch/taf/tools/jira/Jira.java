package com.etouch.taf.tools.jira;

//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
//import org.jboss.netty.handler.codec.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.junit.internal.matchers.StringContains;








import com.etouch.taf.core.exception.DefectException;
import com.etouch.taf.core.resources.DefectParameters;
import com.etouch.taf.core.resources.DefectParameters.IDefect;
import com.etouch.taf.tools.defect.IDefectManager;
import com.etouch.taf.tools.rally.RallyConstants;
import com.etouch.taf.tools.rally.RallyInfo;
import com.etouch.taf.tools.rally.RequestInfo;
import com.etouch.taf.util.CommonUtil;
import com.etouch.taf.util.DateUtil;
import com.etouch.taf.util.LogUtil;
import com.etouch.taf.webui.selenium.WebPage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.AuthenticationException;

import java.io.BufferedReader;

import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.response.CreateResponse;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
/**
 *Jira Integration //added by sonam //
 * 
 * @author eTouch Systems Corporation
 * @version 1.0
 *
 */

public class Jira implements IDefectManager{
	
	

	private static final String SUMMARY = null;

	private static Log log = LogUtil.getLog(Jira.class);
	
	private JiraInfo jiraInfo;

	//constructor for Jira class
	public Jira(JiraInfo jiraInfo) throws DefectException{
		this.jiraInfo = jiraInfo;
	}

	public void setJiraInfo(JiraInfo jiraInfo) {
		this.jiraInfo = jiraInfo;
	}
	
	public void createAJiraDefectBuilder(String url, String issueUrl, String username, String password,
			String keys){
		try{
			
			System.out.println("entered in create defect builder in Jira");
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put(JiraConstants.URL, url);
			reqMap.put(JiraConstants.CREATE_ISSUE_URL,issueUrl);
			reqMap.put(JiraConstants.USERNAME, username);
			reqMap.put(JiraConstants.PASSWORD, password);
			reqMap.put(JiraConstants.KEY, keys);
			
			JiraRequestInfo reqInfo= new JiraRequestInfo(RallyConstants.DEFECT, RallyConstants.CREATE, reqMap);
			System.out.println("done with building the defect builder");
			try {
				
				createIssue(url,issueUrl,username,password,keys);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			}catch(NullPointerException e){
			System.out.println("null pointer exception occured here i.e after create() method");
			log.error(e.getMessage());
			}
			}
		
	void createIssue(String url,String issueUrl,String username,String password,String keys){
		
		  String host = url;
		  String ResponseData;
		  DefaultHttpClient httpClient = new DefaultHttpClient();
		  /*String jsonObj = "{"
		    + "\"fields\": {"
		    + "\"project\":"
		    + "{"
		    + "\"key\": \"SAT\""
		    + "},"
		    + "\"summary\": \"REST eg.\","
		    + "\"description\": \"Creating of an issue using project keys and issue type names using the REST API\","
		    + "\"issuetype\": {" + "\"name\": \"Task\"" + "}" + "}" + "}";*/
		  String jsonObj = "{"
				    + "\"fields\": {"
				    + "\"project\":"
				    + "{"
				    + "\"key\": \"ETAAPIHS\""
				    + "},"
				    + "\"summary\": \"REST eg.\","
				    + "\"description\": \"Creating of an issue using project keys and issue type names using the REST API\","
				    + "\"issuetype\": {" + "\"name\": \"Bug\"" + "}" + "}" + "}";
		
		  HttpHost targetHost = new HttpHost("etouch.atlassian.net",
		    -1, "https");
		  
		  httpClient.getCredentialsProvider().setCredentials(
		    new AuthScope(targetHost.getHostName(), targetHost.getPort(),
		      targetHost.getSchemeName()),
		    new UsernamePasswordCredentials(username, password));

		  /*HttpPost httpPost = new HttpPost(
		    "https://version1etouch.atlassian.net/rest/api/2/issue");*/
		 
		  HttpPost httpPost = new HttpPost(
				    issueUrl);
		  
		  StringEntity entity;
		try {
			entity = new StringEntity(jsonObj);
			entity.setContentType("application/json");
			  httpPost.setEntity(entity);

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  // Create AuthCache instance
		  AuthCache authCache = new BasicAuthCache();
		  // Generate BASIC scheme object and add it to the local auth cache
		  BasicScheme basicAuth = new BasicScheme();

		  authCache.put(targetHost, basicAuth);

		  // Add AuthCache to the execution context
		  BasicHttpContext localcontext = new BasicHttpContext();
		  localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		  try {

		   HttpResponse httpResponse = httpClient.execute(httpPost,
		     localcontext);
		   HttpEntity entitydata = httpResponse.getEntity();
		   ResponseData = new String(EntityUtils.toByteArray(entitydata));
		   System.out.println(ResponseData);
		  } catch (ClientProtocolException e) {
		   e.printStackTrace();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		  httpClient.getConnectionManager().shutdown();
	}

	public void createDefectBuilder(String defName, String testcaseId,
			String projId, String DefSeverity, String DefOwner,
			String DefNotes, String storyId) {
		// TODO Auto-generated method stub
		
	}

	public boolean verifyDefectExists(String testcaseId, String projId,
			String storyId, String defName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean verifyIfdefectClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void reopenDefect() {
		// TODO Auto-generated method stub
		
	}

	public String getDefectStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryDefectID(String testcaseId, String storyId,
			String projId, String defName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String queryDefectStatus(String testcaseId, String storyId,
			String projId, String defName) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateDefect(String testcaseId, String projId,
			String storyId, String defName, IDefect updateKey,
			String updateValue) {
		// TODO Auto-generated method stub
		return false;
	}

	public String queryDefect(String testcaseId, String storyId, String projId,
			String defName, IDefect queryByParam) {
		// TODO Auto-generated method stub
		return null;
	}
}
