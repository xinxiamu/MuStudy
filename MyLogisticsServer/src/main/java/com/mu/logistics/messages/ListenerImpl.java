package com.mu.logistics.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListenerImpl {

	static Log logger = LogFactory.getLog(ListenerImpl.class);
	
	public void listen(String message) {
		logger.info("got the message: " + message + "啊啊啊啊啊啊");
	}

}
