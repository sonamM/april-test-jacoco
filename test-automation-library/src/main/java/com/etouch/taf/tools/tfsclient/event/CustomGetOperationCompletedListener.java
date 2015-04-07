package com.etouch.taf.tools.tfsclient.event;

import com.etouch.taf.util.LogUtil;
import com.microsoft.tfs.core.clients.versioncontrol.events.GetOperationCompletedEvent;
import com.microsoft.tfs.core.clients.versioncontrol.events.OperationCompletedEvent;
import com.microsoft.tfs.core.clients.versioncontrol.events.OperationCompletedListener;
import com.microsoft.tfs.core.clients.versioncontrol.soapextensions.GetRequest;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class CustomGetOperationCompletedListener
    implements OperationCompletedListener
{
	
	private static Log log = LogUtil.getLog(CustomGetOperationCompletedListener.class);
	
    public void onGetOperationCompleted(final GetOperationCompletedEvent e)
    {
        for (GetRequest request : e.getRequests())
        {
            if (request.getItemSpec() != null)
            {
            	log.info("Completed getting: " + request.getItemSpec().toString());
                //System.out.println("Completed getting: " + request.getItemSpec().toString());
            }
        }
    }

    public void onOperationCompleted(final OperationCompletedEvent e)
    {
        if (e instanceof GetOperationCompletedEvent)
        {
            onGetOperationCompleted((GetOperationCompletedEvent) e);
        }
    }
}
