package com.processor.util;

import java.util.List;
import java.util.Map;

import com.processor.model.Sale;

public interface PrintUtil {
	void printProductSaleReport(Map<String, List<Sale>> productSales);
	void printAdjustmentReport(Map<String, List<String>> productAdjustments);
}