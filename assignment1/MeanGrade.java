package com.bham.pij.assignments.meangrade;

import java.util.Scanner;
import com.bham.pij.assignments.gradechecker.GradeChecker;

public class MeanGrade {
	public static void main(String[] args) {
		MeanGrade instance = new MeanGrade();
		
		int[] grades = instance.getInput(4);
		double mean = instance.computeMean(grades);

		if (mean < 0)
			System.out.println("Error: Invalid grades entered!");
		else
			System.out.println(mean);
	}

	public int[] getInput(int count) {
		int[] ret = new int[count];
		Scanner in = new Scanner(System.in);

		System.out.println(String.format("Input %d grades to calculate their mean value.", count));

		for (int i = 0; i < count; i++) {
			ret[i] = in.nextInt();
		}

		return ret;
	}

	public double computeMean(int[] grades) {
		double ret = 0;
		GradeChecker gc = new GradeChecker();

		for (int i = 0; i < grades.length; i++) {
			if (!gc.isValid(grades[i]))
				return -1.0;
			ret += grades[i];
		}

		return ret / grades.length;
	}
}

