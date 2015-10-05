package speedo;

import java.util.Scanner;

public class Speedo {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter command:");
		String line = scanner.nextLine();
		Logic logic = new Logic();
		logic.executeCMD(line);
		scanner.close();

	}

}
