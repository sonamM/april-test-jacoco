package com.etouch.taf.tools.tfsclient.event;

import com.etouch.taf.util.LogUtil;
import com.microsoft.tfs.core.clients.versioncontrol.events.GetOperationStartedEvent;
import com.microsoft.tfs.core.clients.versioncontrol.events.OperationStartedEvent;
import com.microsoft.tfs.core.clients.versioncontrol.events.OperationStartedListener;
import com.microsoft.tfs.core.clients.versioncontrol.soapextensions.GetRequest;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

public class CustomGetOperationStartedListener
    implements OperationStartedListener
{
	private static Log log = LogUtil.getLog(CustomGetOperationCompletedListener.class);
	
    public void onGetOperationStarted(final GetOperationStartedEvent e)
    {
        for (GetRequest request : e.getRequests())
        {
            if (request.getItemSpec() != null)
            {
            	log.info("Started getting: " + request.getItemSpec().toString());
                //System.out.println("Started getting: " + request.getItemSpec().toString());
            }
        }
    }

    public void onOperationStarted(final OperationStartedEvent e)
    {
        if (e instanceof GetOperationStartedEvent)
        {
            onGetOperationStarted((GetOperationStartedEvent) e);
        }
    }
}
