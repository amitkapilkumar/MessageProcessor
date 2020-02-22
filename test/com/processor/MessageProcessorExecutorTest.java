package com.processor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.processor.builder.MessageTokenBuilder;
import com.processor.dto.MessageToken;
import com.processor.model.MessageType;
import com.processor.parser.MessageParser;
import com.processor.service.MessageProcessorService;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MessageProcessorExecutorTest {
	
	@InjectMocks
	private MessageProcessorExecutor messageProcessorExecutor;
	
	@Mock
	private MessageParser messageParser;
	
	@Mock
	private MessageProcessorService messageProcessorService;

	@Test
	public void testProcessMessage() {
		String message = "apple at 20p";
		MessageToken token = MessageTokenBuilder.aMessageTokenBuilder()
						.withMessageType(MessageType.SINGLE_SALE)
						.withAmount(20)
						.withProduct("apples")
						.build();
		when(messageProcessorService.isPaused()).thenReturn(false);
		when(messageParser.parse(message)).thenReturn(token);
		
		messageProcessorExecutor.processMessage(message);
		
		verify(messageProcessorService).isPaused();
		verify(messageProcessorService).process(token);
	}
	
	@Test
	public void testProcessMessageWithList() {
		List<String> messages = new ArrayList<String>() {{
			add("apple at 20p");
			add("10 sales of apples at 20p each");
			add("Add 20p apples");
		}};
		MessageToken token1 = MessageTokenBuilder.aMessageTokenBuilder()
						.withMessageType(MessageType.SINGLE_SALE)
						.withAmount(20)
						.withProduct("apples")
						.build();
		MessageToken token2 = MessageTokenBuilder.aMessageTokenBuilder()
						.withMessageType(MessageType.MULTIPLE_SALES)
						.withAmount(20)
						.withProduct("apples")
						.withOccurences(10)
						.build();
		
		when(messageProcessorService.isPaused()).thenReturn(false).thenReturn(false).thenReturn(true);
		when(messageParser.parse("apple at 20p")).thenReturn(token1);
		when(messageParser.parse("10 sales of apples at 20p each")).thenReturn(token2);
		
		messageProcessorExecutor.processMessage(messages);
		
		verify(messageProcessorService, times(3)).isPaused();
		verify(messageProcessorService).process(token1);
		verify(messageProcessorService).process(token2);
	}

}
