package com.bank.analyzer.exporter;

import com.bank.analyzer.domain.model.dto.SummaryStatistics;

public interface Exporter {
	String export(SummaryStatistics summary);
}
