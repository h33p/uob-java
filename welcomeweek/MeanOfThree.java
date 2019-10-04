public class MeanOfThree {
	public static void main (String[] args) {
		int val1 = Integer.parseInt(args[0]);
		int val2 = Integer.parseInt(args[1]);
		int val3 = Integer.parseInt(args[2]);
		double meanVal = mean(val1, val2, val3);
		System.out.println("The mean value of the input values is: " + meanVal);
	}

	public static double mean(int num1, int num2, int num3) {
		return (num1 + num2 + num3) / 3.0;
	}
}
