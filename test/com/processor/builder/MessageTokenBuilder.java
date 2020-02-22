package com.processor.builder;

import com.processor.dto.MessageToken;
import com.processor.model.Action;
import com.processor.model.MessageType;

public class MessageTokenBuilder {
	private MessageType messageType;
	private Action action;
	private String product;
	private double amount;
	private long occurences;
	private String adjustment;

	public static MessageTokenBuilder aMessageTokenBuilder() {
		return new MessageTokenBuilder();
	}
	
	public MessageTokenBuilder withMessageType(MessageType messageType) {
		this.messageType = messageType;
		return this;
	}
	
	public MessageTokenBuilder withAction(Action action) {
		this.action = action;
		return this;
	}
	
	public MessageTokenBuilder withProduct(String product) {
		this.product = product;
		return this;
	}
	
	public MessageTokenBuilder withAdjustment(String adjustment) {
		this.adjustment = adjustment;
		return this;
	}
	
	public MessageTokenBuilder withAmount(double amount) {
		this.amount = amount;
		return this;
	}
	
	public MessageTokenBuilder withOccurences(long occurences) {
		this.occurences = occurences;
		return this;
	}
	
	public MessageToken build() {
		MessageToken msgToken = new MessageToken();
		msgToken.setMessageType(messageType);
		msgToken.setAction(action);
		msgToken.setProduct(product);
		msgToken.setAmount(amount);
		msgToken.setAdjustment(adjustment);
		msgToken.setOccurrences(occurences);
		return msgToken;
	}
}
