package speedo;
import java.util.Scanner;

public class Speedy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);

			System.out.print("Enter command:");
			String line = scanner.nextLine();
			Logic l = new Logic();
			l.executeCMD(line);

	}

}
