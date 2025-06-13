import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.print(N%2 == 0 ? "CY" : "SK" );
        sc.close();
    }
}
