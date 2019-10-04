public class ComputeCubeCL {
	public static void main (String[] args) {
		String input = args[0];
		int val = Integer.parseInt(input);
		int result = val * val * val;
		System.out.println("The cube of " + val + " is " + result);
	}
}
