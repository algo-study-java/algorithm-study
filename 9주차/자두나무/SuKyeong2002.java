import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuKyeong2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * 공백 기준으로 잘라서 저장하기
         * 시간 (1 ≤ time ≤ 1,000)
         * 최대 움직임 수 (1 ≤ walk ≤ 30)
         */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int time = Integer.parseInt(st.nextToken());
        int walk = Integer.parseInt(st.nextToken());

        // 자두가 떨어지는 나무의 번호 저장하는 배열 (1 ~ time+1)
        int[] tree = new int[time + 1];

        /*
         * time 동안 나무 배열의 원소 입력받기 (1 or 2)
         * 예: tree = [null, 2, 1, 1, 2, 2, 1, 1]
         */
        for (int i = 1; i <= time; i++) {
            tree[i] = Integer.parseInt(br.readLine());
        }

        // time 동안 walk번 이동했을 때 받을 수 있는 자두의 누적합 dp
        int[][] dp = new int[time + 1][walk + 1];

        /*
         * '자두는 1번 자두나무 아래에 위치해 있다고 한다.' -> 초기값 설정
         * 1번 나무의 번호에 따른 이동 횟수 확인 -> dp 계산
         * 예: dp[1][0] = 0, dp[1][1]=1
         */
        dp[1][0] = (tree[1] == 1) ? 1 : 0;
        dp[1][1] = (tree[1] == 2) ? 1 : 0;

        /*
         * 나머지 dp 계산
         * 첫번째 반복문: 2초부터 마지막 시간까지 반복
         * 두번째 반복문: 0번부터 2번 이동까지 반복
         */
        for (int i = 2; i <= time; i++) {
            for (int j = 0; j <= walk; j++) {

                /*
                 * 현재 위치 확인 (1 or 2)
                 * person 배열 만들기 -> 굳이?
                 * dp 배열 활용하기 -> 이동횟수 = 짝수면 1번 나무, 홀수면 2번 나무
                 */
                int currentPos = j % 2 == 0 ? 1 : 2;

                /*
                 * *** 이전 dp값 받아야 함 !!!
                 * 예: dp[2][0] = dp[1][0] = 0, dp[2][1] = dp[1][1] = 1
                 */
                dp[i][j] = dp[i - 1][j];

                /*
                 * i번째 나무의 번호와 현재 위치가 동일한 경우 dp 1씩 증가
                 * 그렇지 않은 경우 현재 dp와 이전 dp 중 최댓값으로 dp 저장
                 * -> 조건 추가: j-1 >= 0 이어야 인덱스 범위 안 벗어남
                 */
                if (j >= 1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                }

                if (tree[i] == currentPos) {
                    dp[i][j]++;
                }
            }
        }

        // 결과 (최대로 받을 수 있는 자두의 수) 계산 및 출력
        int maxPlum = 0;
        for (int i = 0; i <= walk; i++) {
            maxPlum = Math.max(maxPlum, dp[time][i]);
        }
        System.out.print(maxPlum);
    }
}
