package com.processor.dto;

import com.processor.model.Action;
import com.processor.model.MessageType;

public class MessageToken {
	private MessageType messageType;
	private Action action;
	private String product;
	private double amount;
	private long occurrences;
	private String adjustment;
	
	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

	public MessageType getMessageType() {
		return messageType;
	}
	
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
		this.action = action;
	}
	
	public String getProduct() {
		return product;
	}
	
	public void setProduct(String product) {
		this.product = product;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public long getOccurrences() {
		return occurrences;
	}
	
	public void setOccurrences(long occurrences) {
		this.occurrences = occurrences;
	}
	
}