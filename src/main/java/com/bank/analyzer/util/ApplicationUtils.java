package com.bank.analyzer.util;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ApplicationUtils {

	private static Scanner scan = new Scanner(System.in);

	public static void getPrincipalMenu() {
		System.out.println("1) registrar transa��o");
		System.out.println("2) mostrar todas as transa��es");
		System.out.println("3) mostrar por montante");
		System.out.println("4) mostrar transa��es de determinado mes");
		System.out.println("5) calcular total gasto por categoria");
		System.out.println("6) calcular total ganho por categoria");
		System.out.println("7) exportar transa��es");
	}

	public static void getExportMenu() {
		System.out.println("1) exportar transa��es para Html");
	}

	public static byte getByteFromUser() {
		return scan.nextByte();
	}
	public static DateTimeFormatter getApplicationTimeFormat() {
		return 
				DateTimeFormatter.ofPattern("dd-MM-yyyy");
	}

	public static String getStringFromUser() {
		return scan.nextLine();
	}
	public static float getFloatFromUser() {
		return scan.nextFloat();
	}

}
