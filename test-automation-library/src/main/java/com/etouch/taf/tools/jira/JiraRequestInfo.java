package com.etouch.taf.tools.jira;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * This class stores Request information.
 * @author eTouch Systems Corporation
 * @version 1.0
 *
 */
public class JiraRequestInfo {
	
	String requestType;
	String fetch[]; 
	ArrayList<String> queryFilter;
	String refField;
	
	Hashtable<String,String> queryOrder;
	int pageSize;
	int limit;
	Map<String,Map<String,String>> entry;
	boolean attachmentPresent =true ;
	public JiraRequestInfo(){
		pageSize = -1;
		limit = -1;
	}
	
	
	/// constructor for JiraRequestInfo class///
	public JiraRequestInfo(String reqType, String key, Map<String, String> reqEntries){
		setRequestType(reqType);
		for(Map.Entry<String, String> entry: reqEntries.entrySet()){
			addEntry(key, entry.getKey(), entry.getValue());
		}
	}
	
	public String getRequestType() {
		return requestType;
	}
	
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public String[] getFetch() {
		return fetch;
	}
	
	public void setFetch(String[] fetch) {
		this.fetch = fetch;
	}	
	
	public ArrayList<String> getQueryFilter() {
		return queryFilter;
	}

	public void setQueryFilter(ArrayList<String> queryFilter) {
		this.queryFilter = queryFilter;
	}
	
	public String getRefField() {
		return refField;
	}

	public void setRefField(String refField) {
		this.refField = refField;
	}
	
	public void addOrder(String field,String order) {
		if(queryOrder == null)
			queryOrder = new Hashtable<String, String>();
		queryOrder.put(field,order);
	}

	public Map<String, String> getQueryOrder() {
		return queryOrder;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void addEntry(String key, String name,String value){
		
		if(entry == null)
			entry = new HashMap<String, Map<String,String>>();
		
		Map<String, String> map = null;
		if(entry.containsKey(key))
			map = entry.get(key);
		
		if(map == null)
			map =  new HashMap<String, String>();
		
		map.put(name, value);
		
		entry.put(key, map
				);
	}
	public Map<String, Map<String,String>> getEntry() {
		return entry;
	}
	public void setEntry(Map<String, Map<String,String>> newEntry) {
		this.entry = newEntry;
	}

}
