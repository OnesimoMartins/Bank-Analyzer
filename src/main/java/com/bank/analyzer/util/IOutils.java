package com.bank.analyzer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class IOutils {

	// each line of .txt is a string
	public static Optional<List<String>> readTextFromFile(String src) {
		try {

			var file = Files.readAllLines(Paths.get("src/main/resources/" + src));
			return Optional.of(file);
		} catch (IOException e) {
			System.out.println("Erro ao abrir o ficheiro de transações, verifique");
		}
		return Optional.empty();
	}

	public static void storeTextToFile(String src, String text) {
		try {

			Files.write(Paths.get("src/main/resources/" + src), (""+text).getBytes(), StandardOpenOption.APPEND);

		} catch (IOException e) {
			System.out.println("Erro ao abrir o ficheiro de transações, verifique");
		}
	}

}
