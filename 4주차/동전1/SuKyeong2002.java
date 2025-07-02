import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 동전의 종류의 갯수 n 입력받기
        int k = sc.nextInt(); // 동전의 가치의 합 k 입력받기

        // n을 크기로 하는 정수형 배열 coins 선언
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        // k+1을 크기로 하는 정수형 dp 선언
        // K+1인 이유: 0원부터 k원까지 구해야 하기 때문
        int dp[] = new int[k + 1];

        // 1원을 만들 수 있는 경우는 1가지
        dp[0] = 1;

        // 각 동전에 대해
        for (int coin : coins) {
            /*
             * 동전의 가치 계산
             * coin = 1, i = 1 ~ 10, i - coin = 0 ~ 9: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
             * coin = 2, i = 2 ~ 10, i - coin = 0 ~ 8 : [1, 2, 2, 3, 3, 4, 4, 5, 5, 6]
             * coin = 5, i = 5 ~ 10, i - coin = 0 ~ 5 : [1, 2, 2, 3, 4, 5, 6, 7, 8, 10]
             */
            for (int i = coin; i < k + 1; i++) {
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        // k의 경우의 수 = dp[k] 출력
        System.out.println(dp[k]);
    }
}
