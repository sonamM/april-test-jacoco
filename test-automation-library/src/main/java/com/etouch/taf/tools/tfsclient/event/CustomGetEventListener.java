package com.etouch.taf.tools.tfsclient.event;

import com.etouch.taf.tools.tfsclient.VersionControlHandler;
import com.etouch.taf.util.LogUtil;
import com.microsoft.tfs.core.clients.versioncontrol.events.GetEvent;
import com.microsoft.tfs.core.clients.versioncontrol.events.GetListener;
 

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class CustomGetEventListener implements GetListener
{
	private static Log log = LogUtil.getLog(CustomGetEventListener.class);
	
    public void onGet(final GetEvent e)
    {
        String item = e.getTargetLocalItem() != null ? e.getTargetLocalItem() : e.getServerItem();
        log.info("getting: " + item);
        //System.out.println("getting: " + item);
    }
}
