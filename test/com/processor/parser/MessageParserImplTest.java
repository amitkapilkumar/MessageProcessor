package com.processor.parser;

import org.junit.Assert;
import org.junit.Test;

import com.processor.dto.MessageToken;
import com.processor.model.MessageType;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageParserImplTest {
	@InjectMocks
	private MessageParserImpl messageParserImpl;

	@Test
	public void testParseSingleSale() {
		String message = "apple at 20p";
		MessageToken token = messageParserImpl.parse(message);
		
		Assert.assertEquals(MessageType.SINGLE_SALE, token.getMessageType());
		Assert.assertEquals("apples", token.getProduct());
		Assert.assertEquals(20, token.getAmount(), 0.0);
	}
	
	@Test
	public void testParseMutipleSale() {
		String message = "10 sales of apples at 20p each";
		MessageToken token = messageParserImpl.parse(message);
		
		Assert.assertEquals(MessageType.MULTIPLE_SALES, token.getMessageType());
		Assert.assertEquals("apples", token.getProduct());
		Assert.assertEquals(20, token.getAmount(), 0.0);
		Assert.assertEquals(10, token.getOccurrences());
	}
	
	@Test
	public void testParseUpdateSale() {
		String message = "Add 20p apples";
		MessageToken token = messageParserImpl.parse(message);
		
		Assert.assertEquals(MessageType.UPDATE_SALES, token.getMessageType());
		Assert.assertEquals("apples", token.getProduct());
		Assert.assertEquals(20, token.getAmount(), 0.0);
	}
}
