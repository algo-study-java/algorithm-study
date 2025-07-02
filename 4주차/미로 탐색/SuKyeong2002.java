import java.util.*;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행
        int M = sc.nextInt(); // 열

        int[][] maze = new int[N][M]; // 미로 정보
        boolean[][] visited = new boolean[N][M]; // 방문 기록

        // 미로 정보 입력
        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j) - '0'; // 문자 → 숫자
            }
        }

        // 이동을 위한 상하좌우 방향 정의
        int[] dx = { -1, 1, 0, 0 }; // 위, 아래
        int[] dy = { 0, 0, -1, 1 }; // 왼쪽, 오른쪽

        // BFS 시작
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { 0, 0 }); // 시작 위치 (0,0)
        visited[0][0] = true; // 방문 체크

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            // 현재 위치에서 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                // 미로 범위 벗어나면 무시
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
                    continue;

                // 갈 수 없거나 이미 방문한 곳은 무시
                if (maze[nextX][nextY] == 0 || visited[nextX][nextY])
                    continue;

                // 이동 가능 → 거리 갱신
                maze[nextX][nextY] = maze[x][y] + 1;

                // 방문 체크
                visited[nextX][nextY] = true;

                // 다음에 탐색할 위치 큐에 넣기
                queue.add(new int[] { nextX, nextY });
            }
        }

        // 최종 목적지의 값이 최소 칸 수
        System.out.println(maze[N - 1][M - 1]);
    }
}
