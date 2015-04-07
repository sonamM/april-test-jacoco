package com.etouch.taf.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.etouch.taf.util.LogUtil;
import com.etouch.taf.webui.selenium.WebPage;

public class FileDownload  {

	private WebPage webPage;
	final int MAX_WAIT = 55;
	static Log log = LogUtil.getLog(FileDownload.class);
	FileOutputStream fileOutputStream = null;
	HttpResponse response = null;
	String fileName = "tempfile";
	String outputFilePath = null;
    
	public HttpResponse downloadFile(String downloadUrl, String filePath) throws Exception {
		
		try{
	       			
			HttpClient httpClient = HttpClientBuilder.create().build();

			//get the HTTP status of the url to check if url exists
	        HttpGet httpGet = new HttpGet(downloadUrl);
	        response = httpClient.execute(httpGet);
	        
	        System.out.println("Http Response ------> "+response);
	         
	     //   String content_disposition = response.getFirstHeader("content-disposition").getValue();
	        
	        Pattern regex = Pattern.compile("(?<=filename=\").*?(?=\")");
	     //   Matcher regexMatcher = regex.matcher(content_disposition);
	       /* if (regexMatcher.find()) {
	            fileName = regexMatcher.group();
	            System.out.println("FileName ----->" +fileName);
	        }*/
	        
	        HttpEntity entity = response.getEntity();
	        //check if entity response object is not null
	        if (entity != null) {
	        	outputFilePath = filePath+fileName;
	        	
	            //Create file object
	        	File outputFile = new File(outputFilePath);            
	            
	            //get the entity content of the file to be downloaded
	            InputStream inputStream = entity.getContent();
	            
	            //Write the entity content out to the output stream
	            fileOutputStream = new FileOutputStream(outputFile);
	            
	            System.out.println(" File Path" + outputFilePath);
	            
	            int read = 0;
	            byte[] bytes = new byte[2048];
	            
	            //start writing to output stream only if byte size is not equal to -1
	            while ((read = inputStream.read(bytes)) != -1) {
	                fileOutputStream.write(bytes, 0, read);
	                System.out.println("Writing the file contents to local directory->>>>>"+read);
	            }
		            
	            //Print the file size and content type of the downloaded file
	            System.out.println("File size is ------> " + outputFile.length() + " bytes");
        	}
		}
        catch(FileNotFoundException e) {
            System.out.println("File downloading failed!!!");
            e.printStackTrace();
        }
		finally{
			 //close the file stream after reading
            fileOutputStream.close();
			
		}
		return response;	
    }
}