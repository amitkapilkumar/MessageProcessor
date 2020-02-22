package com.processor.parser;

import com.processor.dto.MessageToken;
import com.processor.model.Action;
import com.processor.model.MessageType;

import static com.processor.util.Constants.MULTIPLE_SALES_PATTERN;
import static com.processor.util.Constants.SPLIT_PATTERN;

public class MessageParserImpl implements MessageParser {
	
	@Override
	public MessageToken parse(String message) {
		MessageToken token = new MessageToken();
		for(Action action : Action.values()) {
			if(message.toLowerCase().contains(action.toString().toLowerCase())) {
				token.setAction(action);
				token.setMessageType(MessageType.UPDATE_SALES);
				parseUpdateSales(message, token);
				return token;
			}
		}
		
		if(message.matches(MULTIPLE_SALES_PATTERN)) {
			token.setMessageType(MessageType.MULTIPLE_SALES);
			parseMutipleSales(message, token);
		}
		else {
			token.setMessageType(MessageType.SINGLE_SALE);
			parseSingleSales(message, token);
		}
		
		return token;
	}
	
	private void parseUpdateSales(String message, MessageToken token) {
		String[] strs = message.split(SPLIT_PATTERN);
		token.setProduct(strs[2].toLowerCase());
		token.setAmount(Double.parseDouble(strs[1].substring(0, strs[1].length()-1)));
		token.setAdjustment(message);
	}
	
	private void parseMutipleSales(String message, MessageToken token) {
		String[] strs = message.split(SPLIT_PATTERN);
		token.setOccurrences(Long.parseLong(strs[0].trim()));
		token.setProduct(strs[3].toLowerCase());
		token.setAmount(Double.parseDouble(strs[5].substring(0, strs[5].length()-1)));
	}

	private void parseSingleSales(String message, MessageToken token) {
		String[] strs = message.split(SPLIT_PATTERN);
		token.setProduct(strs[0].toLowerCase() + "s");
		token.setAmount(Double.parseDouble(strs[2].substring(0, strs[2].length()-1)));
	}
}