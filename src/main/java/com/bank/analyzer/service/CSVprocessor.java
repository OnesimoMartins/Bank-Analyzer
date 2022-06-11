package com.bank.analyzer.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bank.analyzer.model.BankTransaction;
import com.bank.analyzer.util.ApplicationUtils;

//Responsible for convert csv file to a list of transactions
public class CSVprocessor {
	private static BankTransaction ProcessTransaction(String transactionLine) {
		String[] newlineTransaction = transactionLine.split(",");
		
		
		LocalDate date = LocalDate.parse(newlineTransaction[0], ApplicationUtils.getApplicationTimeFormat());
		float amount = Float.parseFloat(newlineTransaction[1]);
		String description = newlineTransaction[2];

		return new BankTransaction(date, description, amount);
	}

	public static List<BankTransaction> processAllTransactions(List<String> lines) {
		var transactionsList = new ArrayList<BankTransaction>();

		for (String line : lines)
			transactionsList.add(ProcessTransaction(line));

		return transactionsList;
	}

}
