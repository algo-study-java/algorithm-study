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

/*
퇴사

문제 요약

입력값: N : 일할 수 있는 날
출력값: System.out.println(dp[N]) : N일 동안의 최대 수익 

1. N일 동안 최대 수익 벌기 (오늘 ~ 퇴사 직전날)
	N일 동안 For 반복문 돌기
		1-1) *상담을 안 할 경우(= 다음날 수익이 더 많음): 
			dp[i+1] = max(dp[i+1], d[i]);
		1-2) 상담을 할 경우(= 당일 수익 반영)
			dp[i+T[i]] = max(dp[i+T[i]], dp[i] + P[i])
2. 하루에 하나씩 상담 가능하고 상담은 1일 이상 소요 
3. N+1일은 퇴사날이어서 상담 불가능
4. 하나의 상담 중 다른 상담 불가능

DP(Dynamic Programming): 
이전에 계산한 결과를 저장해서 같은 계산을 반복하지 않도록 도와주는 기술
-> i: 현재 일수 
-> dp[i]: i일까지 누적된 돈
-> T[i]: i일 동안 상담해야 함 (기간)
-> dp[i+T[i]]: i+T[i] 일에 누적된 돈 (*i+T[i]날 돈 받음, 중간에 돈 못받음)
-> P[i]: i일에 받는 돈 (*당일 돈 받음) 
*/
