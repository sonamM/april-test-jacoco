package com.etouch.taf.core.config;

// TODO: Auto-generated Javadoc
/**
 * The Class WebConfig.
 */
public class WebConfig extends TafConfig {
	
	/** The url. */
	private String URL;
	
	private String proxy;
	private String noProxy;

	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * Sets the url.
	 *
	 * @param uRL the new url
	 */
	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	public String getNoProxy() {
		return noProxy;
	}

	public void setNoProxy(String noProxy) {
		this.noProxy = noProxy;
	}
	
	
	
	

}
