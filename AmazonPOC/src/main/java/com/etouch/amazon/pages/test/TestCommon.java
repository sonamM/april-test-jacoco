package com.etouch.amazon.pages.test;


import org.testng.annotations.Test;

import com.etouch.amazon.common.BaseTest;
import com.etouch.taf.util.CommonUtil;

public class TestCommon extends BaseTest{
	
	@Test
	public void testCommon(){
		CommonUtil.sop(" Message from TestCommon");
	}

}
