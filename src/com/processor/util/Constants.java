package com.processor.util;

public class Constants {

	private Constants() {}

	public static final String MULTIPLE_SALES_PATTERN = "[1-9][0-9]*\\s+sales.*";
	public static final String SPLIT_PATTERN = "\\s+";
	public static final long MESSAGE_PROCESSOR_LIMIT = 50;
	public static final long MESSAGE_PROCESSOR_PRINT_LIMIT = 10;
}
