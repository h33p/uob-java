public class MeanOfThree2 {
	public static void main (String[] args) {
		int vals[] = new int[3];
		vals[0] = Integer.parseInt(args[0]);
		vals[1] = Integer.parseInt(args[1]);
		vals[2] = Integer.parseInt(args[2]);
		double meanVal = mean(vals);
		System.out.println("The mean value of the input values is: " + meanVal);
	}

	public static double mean(int[] nums) {
		double result = 0;

		for (int i = 0; i < nums.length; i++) {
			result += nums[i];
		}

		return result / nums.length;
	}
}
