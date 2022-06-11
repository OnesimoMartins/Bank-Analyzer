package com.bank.analyzer.service;

import java.time.Month;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.bank.analyzer.filter.BankTransactionFilter;
import com.bank.analyzer.model.BankTransaction;

//responsible for iterating over diferents bank transactions
public class BankProcessor {

	private List<BankTransaction> bankTransactionList;

	public BankProcessor(List<BankTransaction> aux) {
		this.bankTransactionList = aux;
	}

	public float getTotalAmount() {
		float total = 0;
		for (BankTransaction eachTransaction : bankTransactionList)
			total += eachTransaction.getAmount();
		return total;
	}

	@Deprecated
	public float getTotalAmountByMonth(final Month month) {
		float total = 0;
		for (BankTransaction trans : this.bankTransactionList)
			if (trans.getDate().getMonth() == month)
				total += trans.getAmount();

		return total;
	}

	@Deprecated
	public float totalByDescription(String desc) {

		float suma = 0f;
		for (BankTransaction transaction : bankTransactionList)
			if (desc.equalsIgnoreCase(transaction.getDescription()))
				suma += transaction.getAmount();

		return suma;
	}

	@Deprecated(forRemoval = true)
	public List<BankTransaction> getTransactionsByAmount(final float amount) {
		return bankTransactionList.stream().filter(x -> x.getAmount() == amount).toList();
	}

	@Deprecated
	public List<BankTransaction> getTransactionByInMonth(final Month month) {
		return bankTransactionList.stream().filter(x -> x.getDate().getMonth() == month).toList();

	}

	// soluction for all deprecated
	public List<BankTransaction> filterTransactions(BankTransactionFilter transactionFilter) {
		return bankTransactionList.stream().filter(transactionFilter::filter).toList();
	}

	public float sumarize(BankTransactionFilter sumarizerFilter) {
		AtomicLong total = new AtomicLong();
		this.bankTransactionList.stream().filter(sumarizerFilter::filter)
				.forEach(it -> total.addAndGet((long) it.getAmount())
				);
		return total.floatValue();
	}

}
