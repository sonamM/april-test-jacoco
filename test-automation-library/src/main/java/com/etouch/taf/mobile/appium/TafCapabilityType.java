package com.etouch.taf.mobile.appium;

import org.openqa.selenium.remote.CapabilityType;

/**
 * Appium driver capabilities.
 */
public interface TafCapabilityType extends CapabilityType{
  String PLATFORM_NAME = "platformName";
  String DEVICE_NAME = "deviceName";
  String APP_PACKAGE = "androidPackage";
  String APP_ACTIVITY = "androidActivity";
  String UDID = "udid";
  String DEVICE = "device";
  String BUNDLE_ID = "bundleId";
  String APP = "app";
  String DEVICE_ID = "device-id";
  
}
