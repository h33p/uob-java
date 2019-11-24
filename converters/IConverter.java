package com.bham.pij.assignments.converters;

import com.bham.pij.assignments.converters.InvalidFormatException;

public interface IConverter {
	String convert(String value) throws InvalidFormatException;
}

