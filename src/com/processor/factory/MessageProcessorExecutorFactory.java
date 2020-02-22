package com.processor.factory;

import com.processor.MessageProcessorExecutor;
import com.processor.parser.MessageParserImpl;
import com.processor.service.MessageProcessorServiceImpl;
import com.processor.util.PrintUtilImpl;

public class MessageProcessorExecutorFactory {

	private MessageProcessorExecutorFactory() { }

	public static MessageProcessorExecutor getInstance() {
		MessageProcessorExecutor mse = new  MessageProcessorExecutor();
		mse.setMessageParser(new MessageParserImpl());
		MessageProcessorServiceImpl msei = new MessageProcessorServiceImpl();
		msei.setPrintUtil(new PrintUtilImpl());
		mse.setMessageProcessorService(msei);
		return mse;
	}
}
