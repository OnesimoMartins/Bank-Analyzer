package com.bank.analyzer.domain.model.dto;

import java.util.List;

import com.bank.analyzer.model.BankTransaction;

public class SummaryStatistics {

	private float min;
	private float max;
	private float total;

	public SummaryStatistics(List<BankTransaction> transactions) {
		this.max=0;
		this.min=0;
		
		transactions.forEach(x -> {
			if (x.getAmount()>=max) 
			this.max=x.getAmount();
			else if(x.getAmount()<=min)
			this.min=x.getAmount();
			total=total+x.getAmount();
		});
	}

	public float getMin() {
		return min;
	}

	public float getMax() {
		return max;
	}

	public float getTotal() {
		return total;
	}

}
