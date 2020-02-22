package com.processor.parser;

import com.processor.dto.MessageToken;

public interface MessageParser {
	public MessageToken parse(String message);
}
