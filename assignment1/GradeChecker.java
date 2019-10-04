package com.bham.pij.assignments.gradechecker;

import java.util.Scanner;

public class GradeChecker {

	private static final int PASS_MARK = 40;

	public static void main(String[] args) {

		GradeChecker gc = new GradeChecker();

		int grade = gc.getInput();

		if (!gc.isValid(grade)) {
			System.out.println("Invalid input.");
			return;		    
		}

		gc.checkInput(grade);
	}

	private void checkInput(int grade) {
		if (isPass(grade)) {
			System.out.println("Pass.");
		}

		else  {
			System.out.println("Fail.");
		}
	}

	private int getInput() {

		Scanner in = new Scanner(System.in);

		System.out.println("Input a grade to check.");

		String input = in.nextLine();

		int val = Integer.parseInt(input);

		in.close();

		return val;
	}

	public boolean isValid(int grade) {
		return grade >= 0 && grade <= 100;
	}

	public boolean isPass(int grade) {
		return grade >= 40;
	}
}
