import java.util.Scanner;

public class SimpleIO {
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter an integer.");
		int inval = in.nextInt();
		System.out.print("You entered the integer: ");
		System.out.println(inval);
	}
}
