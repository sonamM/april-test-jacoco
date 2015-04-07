package com.etouch.taf.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public Properties objectRep;

	public Properties getObjectRep(String fileName) {
		if(objectRep==null){
			objectRep=createRepository(fileName);
			
		}
		return objectRep;
	}

	private Properties createRepository(String fileName)  {
		objectRep=new Properties();
		InputStream ip = null;
		try {
			ip = new FileInputStream(fileName);
		
		objectRep.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return objectRep;

	}

}
