package com.processor.util;

import java.util.List;
import java.util.Map;

import com.processor.model.Sale;
import static java.lang.System.out;

public class PrintUtilImpl implements PrintUtil {

	@Override
	public void printProductSaleReport(Map<String, List<Sale>> productSales) {
		for(Map.Entry<String, List<Sale>> entry : productSales.entrySet()) {
			double totalValue = 0;
			for(Sale sale : entry.getValue()) {
				totalValue += sale.getValue();
			}
			out.println("Product Sales Report");
			out.println("Product 	 		: " + entry.getKey());
			out.println("No. of Sales 		: " + entry.getValue().size());
			out.println("Total Sale value 	: " + totalValue);
			out.println("===================================================");
		}
	}

	@Override
	public void printAdjustmentReport(Map<String, List<String>> productAdjustments) {
		out.println("Product Adjustment Report");
		for(Map.Entry<String, List<String>> entry : productAdjustments.entrySet()) {
			out.println("Product 	: " + entry.getKey());
			for(String adjustment : entry.getValue()) {
				out.println(adjustment);
			}
			out.println("===================================================");
		}
	}

}
