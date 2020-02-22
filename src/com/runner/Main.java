package com.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.processor.MessageProcessorExecutor;
import com.processor.factory.MessageProcessorExecutorFactory;

public class Main {

	public static void main(String[] args) {
		MessageProcessorExecutor mpe = MessageProcessorExecutorFactory.getInstance();
		/*List<String> messages = new ArrayList<String>() {{
			add("apple at 20p");
			add("10 sales of apples at 20p each");
			add("Add 20p apples");
			add("orange at 20p");
			add("10 sales of oranges at 20p each");
			add("Add 20p oranges");
			add("kiwi at 20p");
			add("10 sales of kiwis at 20p each");
			add("Add 20p kiwis");
			add("Subtract 10p kiwis");
		}};
		mpe.processMessage(messages);*/

		System.out.println("Enter Messages(s), (type quit to exit):");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			if(line.equalsIgnoreCase("quit")) {
				break;
			}
			mpe.processMessage(line);
		}
	}

}
