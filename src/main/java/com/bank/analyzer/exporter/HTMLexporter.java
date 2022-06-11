package com.bank.analyzer.exporter;

import com.bank.analyzer.domain.model.dto.SummaryStatistics;

public class HTMLexporter implements Exporter {

	@Override
	public String export(SummaryStatistics summary) {
		
		 return 
		 
		 "<!DOCTYPE html>\r\n"
		 + "<html>\r\n"
		 + "<head>\r\n"
		 + "	<title>bank transactions report</title>\r\n"
		 + "</head>\r\n"
		 + "<body>\r\n"
		 + "<center>\r\n"
		 + "<h1>Relatório Transações</h1>\r\n"
		 + "<ul>\r\n"
		 + "<li>total :"+  Float.toString(summary.getTotal())+ "</li>\r\n"
		 + "<li>máximo gasto : "+Float.toString(summary.getMin())+ "</li>\r\n"
		 + "<li>maximo ganho: "+Float.toString(summary.getMax())+"</li>\r\n"
		 + "</ul>\r\n"
		 + "</body>\r\n"
		 + "</center>\r\n"
		 + "</html>";
		
	
	}

}
