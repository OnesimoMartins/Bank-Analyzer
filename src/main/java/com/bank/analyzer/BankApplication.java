package com.bank.analyzer;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import com.bank.analyzer.domain.model.dto.SummaryStatistics;
import com.bank.analyzer.exporter.Exporter;
import com.bank.analyzer.exporter.HTMLexporter;
import com.bank.analyzer.model.BankTransaction;
import com.bank.analyzer.service.BankProcessor;
import com.bank.analyzer.service.CSVprocessor;
import com.bank.analyzer.util.ApplicationUtils;
import com.bank.analyzer.util.IOutils;

public class BankApplication {

	private final static String SRC = "transactions.txt";

	public static void main(String[] args) throws IOException, URISyntaxException {
		var option = 0;

		// try to start the program
		var text = IOutils.readTextFromFile(SRC);
		if (text.isEmpty())
			option = 500;
		if (option == 500)
			System.out.println("Erro ao iniciar o programa.");

		var bankTransactions = CSVprocessor.processAllTransactions(text.get());
		var bankProcessor = new BankProcessor(bankTransactions);

		while (option < 9) {
			ApplicationUtils.getPrincipalMenu();
			var opt = ApplicationUtils.getByteFromUser();

			switch (opt) {

			case 1 -> {
				var newTransaction = createTransaction();
				saveTransaction(newTransaction, bankTransactions);
				System.out.println("transação criada com sucesso");
				break;
			}
			case 2 -> {
				bankProcessor.filterTransactions(it -> true).forEach(System.out::println);
				break;
			}
			case 3 -> {
				System.out.println("Insira o montante: ");
				var ammount = ApplicationUtils.getFloatFromUser();
				bankProcessor.filterTransactions(it -> it.getAmount() >= ammount).forEach(System.out::println);
				break;
			}

			case 4 -> {
				System.out.println("Insira o mes: ");
				var month = ApplicationUtils.getByteFromUser();
				bankProcessor.filterTransactions(it -> it.getDate().getMonth().equals(Month.of(month)));
				break;
			}
			case 5 -> {

				System.out.println("Insira a categoria: ");
				var description = ApplicationUtils.getStringFromUser();
				var total = bankProcessor
						.sumarize(it -> it.getDescription().equalsIgnoreCase(description) && it.getAmount() < 0);

				System.out.println("O total gasto em " + description + "foi" + total);
				break;
			}
			case 6 -> {

				System.out.println("Insira a categoria: ");
				var description = ApplicationUtils.getStringFromUser();
				var total = bankProcessor
						.sumarize(it -> it.getDescription().equalsIgnoreCase(description) && it.getAmount() > 0);
				System.out.println("O total ganho em " + description + "foi" + total);
				break;
			}
			case 7 -> {
				System.out.println(" para onde deseja exportar as transações? ");
				ApplicationUtils.getExportMenu();
				opt = ApplicationUtils.getByteFromUser();
				if (opt == 1)
					export(new HTMLexporter(), bankTransactions);

				break;
			}

			default -> option = 10;
			}
		}

	}

	private static BankTransaction createTransaction() {
		System.out.println("Descrição :");
		ApplicationUtils.getStringFromUser();
		var description = ApplicationUtils.getStringFromUser();
		System.out.println("Valor :");
		var amount = ApplicationUtils.getFloatFromUser();

		
//HAVE A BUG WITH DATE HERE!!!!!!!!!!!		
		var transaction = new BankTransaction(
//				LocalDate.parse(LocalDate.now().format(ApplicationUtils.getApplicationTimeFormat())),
				LocalDate.now(),
				description, amount);
		return transaction;
	}

	private static void export(Exporter exporter, List<BankTransaction> transacions)
			throws IOException, URISyntaxException {

		Desktop desktop = Desktop.getDesktop();
		File file = new File("HTMLexporter.html");

		Files.writeString(file.toPath(), exporter.export(new SummaryStatistics(transacions)),
				StandardOpenOption.CREATE);

		desktop.browse(file.toURI());

	}

	private static void saveTransaction(BankTransaction transaction, List<BankTransaction> transactions) {
		transactions.add(transaction);
		IOutils.storeTextToFile(SRC, transaction.toCSV());
	}

}
