package com.processor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.processor.dto.MessageToken;
import com.processor.parser.MessageParser;
import com.processor.service.MessageProcessorService;

public class MessageProcessorExecutor {
	
	private MessageParser messageParser;
	private MessageProcessorService service;
	
	public void setMessageParser(MessageParser messageParser) {
		this.messageParser = messageParser;
	}
	
	public void setMessageProcessorService(MessageProcessorService service) {
		this.service = service;
	}
	
	public void processMessage(String message) {
		MessageToken token = messageParser.parse(message);
		if(!service.isPaused()) {
			service.process(token);
		}
		else {
			System.out.println("Processor not accepting anymore message!!");
		}
	}
	
	public void processMessage(List<String> messages) {
		for(String msg : messages) {
			processMessage(msg);
		}
	}
	
	public void processMessageFromFile(String filepath) throws IOException {
		List<String> messages = new ArrayList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
		for(String line = br.readLine(); line != null; line = br.readLine()) {
			messages.add(line);
		}
		br.close();
		processMessage(messages);
	}
}
