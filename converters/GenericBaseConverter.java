package com.bham.pij.assignments.converters;

import com.bham.pij.assignments.converters.IConverter;

public class GenericBaseConverter implements IConverter{

	private String placeValueIn;
	private String placeValueOut;
	private int inputLength;
	private int outputLength;
	private boolean inputTrimZeroes;
	private boolean outputTrimZeroes; 

	public GenericBaseConverter(String pvIn, String pvOut, int inLen, int outLen, boolean inTZ, boolean outTZ) {
		placeValueIn = pvIn;
		placeValueOut = pvOut;
		inputLength = inLen;
		outputLength = outLen;
		inputTrimZeroes = inTZ;
		outputTrimZeroes = outTZ;
	}

	public String convert(String value) {

		if ((value.length() != inputLength && !inputTrimZeroes) || (value.length() > inputLength && inputTrimZeroes))
			return null;

		int num = 0;

		int mul = placeValueIn.length();

		for (int i = 0; i < value.length(); i++) {
			num *= mul;
			char c = value.charAt(i);
			int idx = placeValueIn.indexOf(c);
			if (idx == -1)
				return null;
			num += idx;
		}

		int div = placeValueOut.length();

		String outString = "";

		for (int i = 0; i < outputLength; i++) {
			outString = "" + placeValueOut.charAt(num % div) + outString;
			num /= div;

			if (outputTrimZeroes && num == 0)
				break;
		}

		if (num > 0)
			return null;

		return outString;
	}
}

