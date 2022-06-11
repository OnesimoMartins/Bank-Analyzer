package com.bank.analyzer.filter;

import com.bank.analyzer.model.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
	 public boolean filter(BankTransaction ban);
}
