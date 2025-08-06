import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 물품의 수
        int K = sc.nextInt(); // 준서가 버틸 수 있는 무게

        int[] W = new int[N + 1]; // 물건의 무게 저장 (1 ~ N)
        int[] V = new int[N + 1]; // 물건의 가치 저장 (1 ~ N)

        // 각 물건의 무게(W)와 가치(V) 입력받기
        for (int i = 1; i <= N; i++) {
            W[i] = sc.nextInt();
            V[i] = sc.nextInt();
        }

        // 누적합 저장 dp
        int[] dp = new int[K + 1]; // 크기: 배낭 무게 (1 ~ K+1)

        // 누적합 (최대 가치) 계산 -> 첫번째 반복문: 물건
        for (int i = 1; i <= N; i++) {
            /*
             * 오름차순으로 계산 시 같은 물건 두 번 이상 사용하는 중복 문제 발생
             * 예: n=1, k=4, w=1, v=10
             * 답: 10 이어야 하는데 20 출력
             * for (int j = 1; j <= K; j++) {
             * if (j >= W[i]) {
             * dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
             * }
             * }
             */

            /*
             * 중복 문제 해결: dp[j - W] -> 항상 이번 물건을 넣기 전 값 참조
             * 2번째 반복문: 각각의 물건의 무게
             */
            for (int j = K; j >= 0; j--) {
                // 조건: 배낭의 무게가 현재 물건의 무게보다 크거나 같은 경우
                if (j >= W[i]) {
                    // dp[j]: 물건을 더 담지 않은 현재 가치
                    // dp[j - W[i]] + V[i]: 물건을 더 담은 후 가치
                    dp[j] = Math.max(dp[j], dp[j - W[i]] + V[i]);
                }
            }
        }

        /*
         * 누적합의 최댓값 출력
         * dp[K]: 배낭의 무게가 K 이하일 때 만들 수 있는 최대 가치 (O)
         * dp[K]: 배낭의 무게가 K 일 때 만들 수 있는 최대 가치 (X)
         */
        System.out.println(dp[K]);

    }
}
