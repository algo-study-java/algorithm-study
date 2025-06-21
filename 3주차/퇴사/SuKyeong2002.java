import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 일할 수 있는 기간인 N값 입력받기

        // T[0]=3: 1일 상담은 3일 소요
        int[] T = new int[N];

        // P[0]=10: 1일 상담 후 받는 돈
        int[] P = new int[N];

        // dp[7]: 7일 동안 누적된 돈 (N일까지 상담하므로 N+1일의 수익을 봐야 함)
        int[] dp = new int[N + 1];

        for (int i = 0; i < N; i++) {
            T[i] = sc.nextInt(); // 상담기간 입력받기
            P[i] = sc.nextInt(); // 상담수익 입력받기
        }

        for (int i = 0; i < N; i++) {
            // 1. 상담을 안 하는 경우 (= 다음날 수익이 더 많은 경우)
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // 2. 상담을 하는 경우 (= 상담기간이 퇴사 전날 & 다음날 수익이 더 많지 않은 경우)
            if (i + T[i] <= N) {
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i] + P[i]);
            }
        }

        // N일 동안 누적된 돈 출력
        System.out.println(dp[N]);
    }
}
