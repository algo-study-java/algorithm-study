import java.util.*;

public class chtoqur {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		System.out.println(N % 2 == 0 ? "CY" : "SK");
		sc.close();
	}
}