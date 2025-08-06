import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 삼각형의 크기 입력 받기

        // 2차원 삼각형 배열
        int[][] triangle = new int[n][n];

        // 삼각형의 각 행마다 열의 값 입력 받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = sc.nextInt();
            }
        }

        // 누적합 저장할 dp 선언
        int dp[][] = new int[n][n];

        // 누락된 0번째 줄 0번째 dp값 추가
        dp[0][0] = triangle[0][0];

        // 누적합 계산 (i는 1부터 시작)
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) { // 왼쪽 끝일 경우
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == i) { // 오른쪽 끝일 경우
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else { // 중간일 경우
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }
        // 최대값 저장 변수
        int max = 0;

        // 최대값 계산
        for (int i = 0; i < n; i++) {
            // 마지막 줄의 모든 칸 확인
            max = Math.max(max, dp[n - 1][i]);
        }

        // 최대값 출력
        System.out.println(max);
    }
}
