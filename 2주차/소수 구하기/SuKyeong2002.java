import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(); // 정수 M 입력받기
        int N = sc.nextInt(); // 정수 N 입력받기

        // 소수 구하기
        for (int i = M; i <= N; i++) {
            if (isPrime(i)) {
                System.out.println(i); // 소수만 출력
            }
        }
    }

    // 소수 판별 함수
    public static boolean isPrime(int num) {
        // 2보다 작을 경우 false 리턴
        if (num < 2)
            return false;

        // i * i <= num : i <= √num
        // 예: 6 * 6 <= 36 이므로 2~6까지만 검사하면 됨
        for (int i = 2; i * i <= num; i++) {

            // 자기 자신으로 나누어 떨어지면 false 리턴
            if (num % i == 0)
                return false;
        }
        return true;

    }
}

/*
 * 소수: 2보다 큰 자연수 중에서 1과 자기 자신을 제외한 자연수로는 나누어 떨어지지 않는 수
 * 약수가 있는지의 여부 확인 (없을 시 소수)
 * 
 * 
 * 3 : 1, 3
 * 5 : 1, 5
 * 7 : 1, 7
 * ...
 */