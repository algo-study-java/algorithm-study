import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 수열 길이
        int[] A = new int[N]; // 수열
        int[] dp = new int[N]; // 각 위치까지의 최장 증가 수열 길이 저장

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            dp[i] = 1; // 최소 1 (자기 자신)
        }

        // 최장 증가 수열 계산
        for (int i = 1; i < N; i++) {
            // A[i]보다 작은 A[j]를 찾아 dp[i] 업데이트
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 최장 증가 수열의 길이 찾기
        int maxLen = 0;
        for (int i = 0; i < N; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }

        System.out.println(maxLen);
    }
}
