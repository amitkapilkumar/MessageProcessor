package com.processor.service;

import com.processor.dto.MessageToken;

public interface MessageProcessorService {
	public void process(MessageToken messageToken);
	public boolean isPaused();
}
