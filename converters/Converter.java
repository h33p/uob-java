package com.bham.pij.assignments.converters;

import java.util.ArrayList;
import com.bham.pij.assignments.converters.InvalidFormatException;
import com.bham.pij.assignments.converters.IConverter;
import com.bham.pij.assignments.converters.GenericBaseConverter;

public class Converter {
	
	public static enum ConvertMode {BIN2HEX, HEX2BIN, BIN2DEC, DEC2BIN};

	protected IConverter converter;

	public Converter(ConvertMode cm) {
		converter = createConverter(cm);
	}

	protected IConverter createConverter(ConvertMode cm) {
		switch(cm) {
			case BIN2HEX:
				return new GenericBaseConverter("01", "0123456789ABCDEF", 8, 2, false, false);
			case HEX2BIN:
				return new GenericBaseConverter("0123456789ABCDEF", "01", 2, 8, false, false);
			case BIN2DEC:
				return new GenericBaseConverter("01", "0123456789", 8, 3, false, true);
			case DEC2BIN:
				return new GenericBaseConverter("0123456789", "01", 3, 8, true, false);
		}

		return null;
	}

	public String convert(String value) throws InvalidFormatException {
		String ret = converter.convert(value);

		if (ret == null)
			throw new InvalidFormatException();

		return ret;
	}

	public void fromFile(String filename) {
	}

	public ArrayList<String> getInputValues() {
		return null;
	}

	public ArrayList<String> getOutputValues() {
		return null;
	}
}

