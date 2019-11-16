package com.bham.pij.assignments.converters;

import java.util.Scanner;
import com.bham.pij.assignments.converters.Converter;
import com.bham.pij.assignments.converters.InvalidFormatException;

public class TestConverters {

	public static void doTest(Scanner in, Converter converter) {
		while (true) {
			String input = in.nextLine().trim();
			if (input.equals(""))
				break;
			try {
				System.out.println(converter.convert(input));
			} catch (InvalidFormatException e) {
				System.out.println("Invalid format!");
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		Converter converter = new Converter(Converter.ConvertMode.BIN2HEX);
		System.out.println("BIN2HEX converter:");
		doTest(in, converter);
	
		converter = new Converter(Converter.ConvertMode.HEX2BIN);
		System.out.println("HEX2BIN converter:");
		doTest(in, converter);

		converter = new Converter(Converter.ConvertMode.BIN2DEC);
		System.out.println("BIN2DEC converter:");
		doTest(in, converter);

		converter = new Converter(Converter.ConvertMode.DEC2BIN);
		System.out.println("DEC2BIN converter:");
		doTest(in, converter);
	}
}

