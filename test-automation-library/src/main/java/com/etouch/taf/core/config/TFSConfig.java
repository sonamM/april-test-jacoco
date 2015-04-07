package com.etouch.taf.core.config;

// TODO: Auto-generated Javadoc
/**
 * The Class BrowserConfig.
 */
public class TFSConfig {
	
	/** The User Name */
	private String userName;
	
	/** The password */
	private String password;
	
	/** The project name */
	private String projectName;
	
	/** The mapping server path */
	private String mappingServerPath;
	
	/** The mapping local path */
	private String mappingLocalPath;
	
	/** The TFS_JNI_NATIVE_BASE directory */
	private String jniNativeBaseDir;
	
	/** The TFS workspace name */
	private String workspaceName;
	
	/** The maximum number of reconnection attempts */
	private int maxReconections;
	
	/** The TFS server URL */
	private String collectionURL;
	
	/**
	 * @return the collectionURL
	 */
	public String getCollectionURL() {
		return collectionURL;
	}

	/**
	 * @param collectionURL the collectionURL to set
	 */
	public void setCollectionURL(String collectionURL) {
		this.collectionURL = collectionURL;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the mappingServerPath
	 */
	public String getMappingServerPath() {
		return mappingServerPath;
	}

	/**
	 * @param mappingServerPath the mappingServerPath to set
	 */
	public void setMappingServerPath(String mappingServerPath) {
		this.mappingServerPath = mappingServerPath;
	}

	/**
	 * @return the mappingLocalPath
	 */
	public String getMappingLocalPath() {
		return mappingLocalPath;
	}

	/**
	 * @param mappingLocalPath the mappingLocalPath to set
	 */
	public void setMappingLocalPath(String mappingLocalPath) {
		this.mappingLocalPath = mappingLocalPath;
	}

	/**
	 * @return the jniNativeBaseDir
	 */
	public String getJniNativeBaseDir() {
		return jniNativeBaseDir;
	}

	/**
	 * @param jniNativeBaseDir the jniNativeBaseDir to set
	 */
	public void setJniNativeBaseDir(String jniNativeBaseDir) {
		this.jniNativeBaseDir = jniNativeBaseDir;
	}

	/**
	 * @return the workspaceName
	 */
	public String getWorkspaceName() {
		return workspaceName;
	}

	/**
	 * @param workspaceName the workspaceName to set
	 */
	public void setWorkspaceName(String workspaceName) {
		this.workspaceName = workspaceName;
	}

	/**
	 * @return the maxReconections
	 */
	public int getMaxReconections() {
		return maxReconections;
	}

	/**
	 * @param maxReconections the maxReconections to set
	 */
	public void setMaxReconections(int maxReconections) {
		this.maxReconections = maxReconections;
	}
	

}
