package com.processor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.processor.dto.MessageToken;
import com.processor.model.Sale;
import com.processor.util.PrintUtil;

import static com.processor.util.Constants.*;

public class MessageProcessorServiceImpl implements MessageProcessorService {
	private Map<String, List<Sale>> productSales;
	private Map<String, List<String>> productAdjustments;
	private long messageCounter;
	private PrintUtil printUtil;
	
	public MessageProcessorServiceImpl() {
		productSales = new HashMap<>();
		productAdjustments = new HashMap<>();
	}
	
	public void setPrintUtil(PrintUtil printUtil) {
		this.printUtil = printUtil;
	}

	@Override
	public void process(MessageToken messageToken) {
		if(isPaused())
			return;
		
		switch(messageToken.getMessageType()) {
			case SINGLE_SALE:
				processSingleSale(messageToken);
				break;
			case MULTIPLE_SALES:
				processMultipleSales(messageToken);
				break;
			case UPDATE_SALES:
				processUpdateSales(messageToken);
				break;
		}
		messageCounter++;
		printProductRaleReport();
		printProductAdjustmentReport();
	}
	
	private void printProductAdjustmentReport() {
		if(isPaused())
			printUtil.printAdjustmentReport(productAdjustments);
	}
	
	private void printProductRaleReport() {
		if(messageCounter % MESSAGE_PROCESSOR_PRINT_LIMIT != 0) 
			return;
		
		printUtil.printProductSaleReport(productSales);
	}
	
	@Override
	public boolean isPaused() {
		return messageCounter == MESSAGE_PROCESSOR_LIMIT;
	}

	private void processUpdateSales(MessageToken messageToken) {
		List<Sale> sales = productSales.get(messageToken.getProduct());
		if(productAdjustments.containsKey(messageToken.getProduct())) {
			productAdjustments.get(messageToken.getProduct()).add(messageToken.getAdjustment());
		}
		else {
			List<String> adjustments = new ArrayList<>();
			adjustments.add(messageToken.getAdjustment());
			productAdjustments.put(messageToken.getProduct(), adjustments);
		}
				
		switch(messageToken.getAction()) {
			case ADD:
				for(Sale sale : sales) {
					sale.setValue(sale.getValue() + messageToken.getAmount());
				}
				break;
			case SUBTRACT:
				for(Sale sale : sales) {
					sale.setValue(sale.getValue() - messageToken.getAmount());
				}
				break;
			case MULTIPLY:
				for(Sale sale : sales) {
					sale.setValue(sale.getValue() * messageToken.getAmount());
				}
				break;
		}
	}
	
	private void processMultipleSales(MessageToken messageToken) {
		List<Sale> list = new ArrayList<>();
		long occurences = messageToken.getOccurrences();
		while(occurences > 0) {
			Sale sale = new Sale();
			sale.setProduct(messageToken.getProduct());
			sale.setValue(messageToken.getAmount());
			list.add(sale);
			occurences--;
		}
		if(productSales.containsKey(messageToken.getProduct())) {
			productSales.get(messageToken.getProduct()).addAll(list);
		}
		else {
			productSales.put(messageToken.getProduct(), list);
		}
	}

	private void processSingleSale(MessageToken messageToken) {
		Sale sale = new Sale();
		sale.setProduct(messageToken.getProduct());
		sale.setValue(messageToken.getAmount());
		if(productSales.containsKey(messageToken.getProduct())) {
			List<Sale> list = productSales.get(messageToken.getProduct());
			list.add(sale);
		}
		else {
			List<Sale> list = new ArrayList<>();
			list.add(sale);
			productSales.put(messageToken.getProduct(), list);
		}
	}
	
}