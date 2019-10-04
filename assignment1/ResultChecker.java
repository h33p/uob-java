package com.bham.pij.assignments.resultchecker;

import java.util.Scanner;
import com.bham.pij.assignments.meangrade.MeanGrade;
import com.bham.pij.assignments.gradechecker.GradeChecker;

public class ResultChecker {
	public static void main(String[] args) {
		ResultChecker instance = new ResultChecker();

		MeanGrade mg = new MeanGrade();
		int[] grades = mg.getInput(8);

		System.out.println("Please enter the project grade:");

		Scanner in = new Scanner(System.in);
		int projectGrade = in.nextInt();

		System.out.println(instance.getResult(grades, projectGrade));
	}

	public String getResult(int[] grades, int projectGrade) {
		MeanGrade mg = new MeanGrade();
		GradeChecker gc = new GradeChecker();

		double meanGrade = mg.computeMean(grades);

		if (meanGrade < 0 || !gc.isValid(projectGrade))
			return "ERROR";

		for (int i = 0; i < grades.length; i++)
			if (!gc.isPass(grades[i]))
				return "FAIL";

		if (meanGrade < 50 || projectGrade < 50)
			return "PASS";

		//meanGrade >= 50 && projectGrade >= 50
		return "MERIT";
	}
	
}

