import java.util.Scanner;

public class ComputeCube {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter an integeri to discover its cube.");
		int inval = in.nextInt();
		int result = inval * inval * inval;
		System.out.println("The cube of " + inval + " is " + result);
	}
}
