import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 돌의 갯수 N 입력받기

        if (N % 4 == 1 || N % 4 == 3) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}

/*
 * 완벽하게 게임한다 = 두 사람 다 절대로 실수하지 않고 자기에게 가장 유리한 선택만 한다
 * 
 * 규칙 찾기
 * N = 1, SK -> N % 4 == 1
 * N = 2, CY
 * N = 3, SK -> N % 4 == 1
 * N = 4, CY
 * N = 5, CY
 * N = 6, CY
 * N = 7, SK -> N % 4 == 3
 * N = 8, CY
 * N = 9, SK -> N % 4 == 1,
 * ...
 */