package com.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import com.processor.builder.MessageTokenBuilder;
import com.processor.dto.MessageToken;
import com.processor.model.Action;
import com.processor.model.MessageType;
import com.processor.util.PrintUtil;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static com.processor.model.MessageType.SINGLE_SALE;
import static com.processor.model.MessageType.UPDATE_SALES;
import static com.processor.model.Action.ADD;

@RunWith(MockitoJUnitRunner.class)
public class MessageProcessorServiceImplTest {
	
	@InjectMocks
	private MessageProcessorServiceImpl messageProcessorServiceImpl;
	
	@Mock
	private PrintUtil printUtil;
	
	@Test
	public void testProcessWith10Message() {
		List<MessageToken> tokens = new ArrayList<MessageToken>() {{
			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));

			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(UPDATE_SALES, 10, "kiwis", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "apples", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));
		}};
		
		for(MessageToken token : tokens) {
			messageProcessorServiceImpl.process(token);
		}
		
		verify(printUtil).printProductSaleReport(any());
	}
	
	@Test
	public void testProcessWith50Message() {
		List<MessageToken> tokens = new ArrayList<MessageToken>() {{
			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10.50, "kiwis"));

			add(getMessageToken(UPDATE_SALES, 12.31, "kiwis", ADD));
			add(getMessageToken(SINGLE_SALE, 11.01, "oranges", ADD));
			add(getMessageToken(SINGLE_SALE, 11.49, "apples", ADD));
			add(getMessageToken(SINGLE_SALE, 12.45, "oranges", ADD));

			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));

			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(UPDATE_SALES, 10, "kiwis", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "apples", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));

			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));

			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(UPDATE_SALES, 10, "kiwis", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "oranges", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "apples", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "oranges", ADD));

			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));


			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(UPDATE_SALES, 10, "kiwis", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "oranges", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "apples", ADD));
			add(getMessageToken(UPDATE_SALES, 10, "oranges", ADD));

			add(getMessageToken(SINGLE_SALE, 10, "apples"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));
			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(SINGLE_SALE, 10, "apple"));
			add(getMessageToken(SINGLE_SALE, 10, "oranges"));

			add(getMessageToken(SINGLE_SALE, 10, "kiwis"));
			add(getMessageToken(UPDATE_SALES, 10, "kiwis", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "apples", ADD));
			add(getMessageToken(SINGLE_SALE, 10, "oranges", ADD));
		}};
		
		for(MessageToken token : tokens) {
			messageProcessorServiceImpl.process(token);
		}
		
		verify(printUtil, times(5)).printProductSaleReport(any());
		verify(printUtil).printAdjustmentReport(any());
	}

	private MessageToken getMessageToken(MessageType messageType, double amount, String product, Action... args) {
		MessageTokenBuilder builder = MessageTokenBuilder.aMessageTokenBuilder()
				.withMessageType(messageType)
				.withAmount(amount)
				.withProduct(product);

		if(args.length > 0) {
			return builder.withAction(args[0]).build();
		}
		return builder.build();
	}

}
