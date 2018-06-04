import java.util.Scanner;

public class ReadNumbers{
	public static void main(String[] args) {
		System.out.println("Enter 10 numbers");
		int[] array = new int[10];
		Scanner read = new Scanner(System.in);
		for(int i=0;i<10;i++) {
			array[i] = read.nextInt();
		}
		System.out.println("printing numbers in reverse order:");
		for(int i=9;i>=0;i--) {
			System.out.println(array[i]);
		}
	}
}
