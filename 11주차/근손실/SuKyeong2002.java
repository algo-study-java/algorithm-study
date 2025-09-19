import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuKyeong2002 {
    static int N; // 운동 키트 개수
    static int K; // 하루 중량 감소량
    static int[] weights; // 각 운동 키트의 중량 증가량 배열
    static boolean[] visited; // 방문 여부 배열
    static int currentWeight = 500; // 현재 중량
    static int caseCount = 0; // 경우의 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weights = new int[N];
        visited = new boolean[N];

        // 각 운동 키트의 중량 증가량 입력
        st = new StringTokenizer(br.readLine());

        // 공백 기준으로 나눠서 배열에 저장
        for (int i = 0; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        /*
         * *** 중량이 500 이상인 경우에만 DFS 탐색
         * DFS 사용한 이유: 한 경우를 끝까지 탐색한 뒤 돌아옴
         * 경우: 0~N날까지의 경우의 수
         */
        DFS(500, 0);

        // 경우의 수 출력
        System.out.println(caseCount);

    }

    // DFS 탐색 메서드 정의
    private static void DFS(int currentWeight, int day) {

        // 마지막 날인 경우
        if (day == N) {
            // 경우의 수 증가
            caseCount++;
            return;
        }

        /*
         * 각 운동 키트에 대해 탐색
         * 운동 기간동안 항상 중량이 500 이상이어야 함
         */
        for (int i = 0; i < N; i++) {

            // 방문하지 않은 운동 키트인 경우
            if (!visited[i]) {

                // *** 다음 중량 계산
                int nextWeight = currentWeight + weights[i] - K;

                // 중량이 500 이상인 경우
                if (nextWeight >= 500) {
                    /*
                     * 각각의 운동 키트는 N일 동안 한 번씩만 사용 가능
                     * -> 방문 처리
                     */
                    visited[i] = true;
                    DFS(nextWeight, day + 1); // 다음 날 운동
                    visited[i] = false; // 방문 해제
                }
            }
        }
    }
}