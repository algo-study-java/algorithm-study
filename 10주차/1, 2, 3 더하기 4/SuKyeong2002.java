import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SuKyeong2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스의 개수 입력받기
        int testCase = Integer.parseInt(br.readLine());

        // 테스트 케이스의 정수를 저장하는 배열 생성
        int[] TestCaseArr = new int[testCase];

        // 테스트 케이스 배열 값 입력받기
        int max = 0;
        for (int i = 0; i < testCase; i++) {
            TestCaseArr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, TestCaseArr[i]);
        }

        /*
         * 배열값이 1, 2, 3으로 나눌 수 있는 방법의 수를 저장하는 배열 생성
         * max + 1: 배열 값의 범위 + 1 -> > [0, 1, ..., max]
         * 4: 나눠질 수 있는 경우의 수 + 1 -> [0, 1, 2, 3]
         */
        int[][] dp = new int[max + 1][4];

        /*
         * 배열값이 1, 2, 3 일 때 dp 초기값 설정
         * dp[2][1] -> (1, 1)
         * dp[2][2] -> (2)
         * dp[3][1] -> (1, 1, 1)
         * dp[3][2] -> (1, 2)
         * dp[3][3] -> (3)
         * *** 조건절이 등호가 아닌 부호인 이유: 점화식에서 사용해야 하기 때문
         */
        dp[1][1] = 1;
        if (max >= 2) {
            dp[2][1] = dp[2][2] = 1;
        }
        if (max >= 3) {
            dp[3][1] = dp[3][2] = dp[3][3] = 1;
        }

        /*
         * 접근 방식: if (n / 2 > 0) → 다시 (n-2)를 1,2,3으로 나눌 수 있는지 재귀적으로 확인
         * -> 문제점 1. 방법의 수 찾기 가능하나, 중복도 카운트
         * -> 예. (2+1+1), (1+1+2), (1+2+1) -> 1인데 3으로 카운트
         * -> 문제점 2. 시간 초과
         * 해결방안. 순서 없는 조합 만들기 -> 누적합 계산하는 dp 사용
         * 배열값이 4부터 max까지 반복
         */
        for (int i = 4; i <= max; i++) {
            /*
             * dp 설정값으로 계산식 세우기 (= 점화식)
             * 
             * dp[4][1] -> (1, 1, 1, 1)
             * 1. (1, 1, 1) + (1) -> dp[3][1]
             * 
             * dp[4][2] -> (1, 1, 2), (2, 2)
             * 1. (1, 1), (2) -> dp[2][1]
             * 2. (2) + (2) -> dp[2][2]
             * 
             * dp[4][3] -> (1, 3)
             * 1. (1), (3) -> dp[1][1]
             * 
             * dp[5][1] -> (1, 1, 1, 1, 1)
             * 1. (1, 1, 1, 1), (1) -> dp[4][1]
             * 
             * dp[5][2] -> (1, 1, 1, 2), (1, 2, 2)
             * 1. (1, 1, 1), (2) -> dp[3][1]
             * 2. (1, 2), (2) -> dp[3][2]
             * 
             * dp[5][3] -> (1, 1, 3), (2, 3)
             * 1. (1, 1), (3) -> dp[2][1]
             * 2. (2, 3) -> (2), (3) -> dp[2][2]
             * 
             * dp[6][1] -> (1, 1, 1, 1, 1, 1)
             * 1. (1, 1, 1, 1, 1), (1) -> dp[5][1]
             * 
             * dp[6][2] -> (1, 1, 1, 1, 2), (1, 1, 2, 2), (2, 2, 2)
             * 1. (1, 1, 1, 1), (2) -> dp[4][1]
             * 2. (2, 2), (2) -> dp[4][2]
             * 
             * dp[6][3] -> (1, 1, 1, 3), (1, 2, 3), (3, 3)
             * 1. (1, 1, 1), (3) -> dp[3][1]
             * 2. (1, 2), (3) -> dp[3][2]
             * 3. (3), (3) -> dp[3][3]
             */

            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3];
        }

        // 결과 (각각의 테스트 케이스의 방법의 수의 합) 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < testCase; i++) {
            sb.append(dp[TestCaseArr[i]][1] + dp[TestCaseArr[i]][2] + dp[TestCaseArr[i]][3]).append('\n');
        }
        System.out.println(sb);
    }
}
