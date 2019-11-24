package com.bham.pij.assignments.converters;

import com.bham.pij.assignments.converters.IConverter;
import com.bham.pij.assignments.converters.InvalidFormatException;

public class GenericBaseConverter implements IConverter {

	private String placeValueIn;
	private String placeValueOut;
	private int inputLength;
	private int outputLength;
	private boolean inputTrimZeroes;
	private boolean outputTrimZeroes; 
	private int maxNumericalValue;

	/**
	 * @param placeValueIn The input place-value system to convert the number from
	 * @param placeValueOut The output place-value system to convert the number to
	 * @param inputLength The maximum length of the input string
	 * @param outputLength The maximum length of the output string
	 * @param inputTrimZeroes Controls whether the input string can be shorter than inputLength (i.e. can have trimmed zeroes at the start)
	 * @param outputTrimZeroes Controls whether the output string should have the leading zeroes removed
 	*/
	public GenericBaseConverter(String placeValueIn, String placeValueOut, int inputLength, int outputLength, boolean inputTrimZeroes, boolean outputTrimZeroes) {
		this.placeValueIn = placeValueIn;
		this.placeValueOut = placeValueOut;
		this.inputLength = inputLength;
		this.outputLength = outputLength;
		this.inputTrimZeroes = inputTrimZeroes;
		this.outputTrimZeroes = outputTrimZeroes;

		//Calculate the largest number we can output
		maxNumericalValue = 1;
		
		for (int i = 0; i < outputLength; i++) {
			maxNumericalValue *= this.placeValueOut.length();
		}
	}

	public String convert(String value) throws InvalidFormatException {

		//Initial check for input string format
		if ((value.length() != inputLength && !inputTrimZeroes) || (value.length() > inputLength && inputTrimZeroes))
			throw new InvalidFormatException();

		int num = 0;

		int mul = placeValueIn.length();
		
		//Convert the input string to a java integer
		for (int i = 0; i < value.length(); i++) {
			num *= mul;
			char c = value.charAt(i);
			int idx = placeValueIn.indexOf(c);
			//The input string has characters that are not in the place value system
			if (idx == -1)
				throw new InvalidFormatException();
			num += idx;
		}

		//Prevent overflow (for example: prevents number 256 passing through when the output string only supports 8 bits)
		if (num >= maxNumericalValue)
			throw new InvalidFormatException();

		int div = placeValueOut.length();

		String outString = "";

		//Convert the java integer to a string representation given the output place-value system
		for (int i = 0; i < outputLength; i++) {
			outString = "" + placeValueOut.charAt(num % div) + outString;
			num /= div;

			if (outputTrimZeroes && num == 0)
				break;
		}


		return outString;
	}
}

