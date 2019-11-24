package com.bham.pij.assignments.converters;

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import com.bham.pij.assignments.converters.InvalidFormatException;
import com.bham.pij.assignments.converters.IConverter;
import com.bham.pij.assignments.converters.GenericBaseConverter;

public class Converter {
	
	public static enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN};

	private static final String BINARY_PV_SYSTEM = "01";
	private static final String DECIMAL_PV_SYSTEM = "0123456789";
	private static final String HEXADECIMAL_PV_SYSTEM = "0123456789ABCDEF";

	protected IConverter converter;

	private ArrayList<String> inputValues;
	private ArrayList<String> outputValues;

	public Converter(ConvertMode cm) {
		converter = createConverter(cm);
	}

	public String convert(String value) throws InvalidFormatException {
		return converter.convert(value);
	}

	public void fromFile(String filename) {
		inputValues = new ArrayList<String>();
		outputValues = new ArrayList<String>();

		try (BufferedReader reader = new BufferedReader(
			new FileReader(filename))) {
			
			String str;
			//Read the file line by line
			while ((str = reader.readLine()) != null) {
				//Split each line by space and process each token individually
				String[] tokens = str.split(" ");
				for (String token : tokens) {
					token = token.trim();
					//Try converting each token. If the conversion is successful, add the input and output strings to their respective lists.
					//NOTE: The spec does not specify the behavior when invalid numbers are in the file, so we decide to completely filter them out (so that each number in the input list would correspond to the same index in the output list)
					try {
						String converted = convert(token);
						inputValues.add(token);
						outputValues.add(converted);
					} catch (InvalidFormatException e) {
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getInputValues() {
		return inputValues;
	}

	public ArrayList<String> getOutputValues() {
		return outputValues;
	}

	protected IConverter createConverter(ConvertMode cm) {
		/* We could technically create a separate class for each conversion mode that inherits GenericBaseConverter, and then have them pass in the right arguments,
		 * but according to spec the ConvertMode enum itself is stored on the Converter class, thus these conversion modes are tied directly to this class, and separate classes would only lead to longer code, without any extra extensibility.
		 * The way to add more converters is to inherit this class and replace the converter variable with the converter of choice in there. */
		switch(cm) {
			case BIN2HEX:
				return new GenericBaseConverter(BINARY_PV_SYSTEM, HEXADECIMAL_PV_SYSTEM, 8, 2, false, false);
			case HEX2BIN:
				return new GenericBaseConverter(HEXADECIMAL_PV_SYSTEM, BINARY_PV_SYSTEM, 2, 8, false, false);
			case BIN2DEC:
				return new GenericBaseConverter(BINARY_PV_SYSTEM, DECIMAL_PV_SYSTEM, 8, 3, false, true);
			case DEC2BIN:
				return new GenericBaseConverter(DECIMAL_PV_SYSTEM, BINARY_PV_SYSTEM, 3, 8, true, false);
		}

		return null;
	}
}

