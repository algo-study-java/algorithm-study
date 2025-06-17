import java.util.*;

public class SuKyeong2002 {
    static int N, M;
    static int[][] map; // 원본 연구소 지도
    static int[][] temp; // 벽을 세운 뒤 시뮬레이션용 지도
    static int max = 0; // 최대 안전영역 크기
    static int[] dx = { -1, 1, 0, 0 }; // 상하좌우 방향
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 세로 크기
        M = sc.nextInt(); // 가로 크기

        map = new int[N][M];
        temp = new int[N][M];

        // 연구소 지도 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 벽 3개 세우는 모든 조합 탐색
        dfs(0);

        // 최대 안전영역 출력
        System.out.println(max);
    }

    // 벽을 3개 세우는 조합을 찾기 위한 DFS
    static void dfs(int count) {
        if (count == 3) { // 벽 3개 세운 경우
            copyMap(); // temp 맵에 복사
            spreadVirus(); // 바이러스 퍼뜨리기
            max = Math.max(max, countSafeZone()); // 안전구역 크기 계산 및 최대값 저장
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { // 빈 칸이라면
                    map[i][j] = 1; // 벽 세우기
                    dfs(count + 1); // 다음 벽 세우러 가기
                    map[i][j] = 0; // 다시 빈 칸으로 되돌리기 (백트래킹)
                }
            }
        }
    }

    // map을 temp에 복사
    static void copyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = map[i][j];
            }
        }
    }

    // 바이러스 퍼뜨리기 (DFS 방식)
    static void spreadVirus() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 2) {
                    dfsVirus(i, j);
                }
            }
        }
    }

    // 바이러스 전파 시뮬레이션
    static void dfsVirus(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i]; // 상하좌우 이동
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (temp[nx][ny] == 0) { // 빈 칸이면 전파
                    temp[nx][ny] = 2;
                    dfsVirus(nx, ny); // 계속 전파
                }
            }
        }
    }

    // 안전 영역 계산 (0의 개수)
    static int countSafeZone() {
        int safe = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0)
                    safe++;
            }
        }
        return safe;
    }
}

/*
 * 새로 세울 수 있는 벽의 개수: 3 (아무런 벽도 안 세우면 모든 칸 감염)
 * 0: 빈 칸, 1: 벽, 2: 바이러스
 */
