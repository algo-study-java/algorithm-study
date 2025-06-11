import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SuKyeong2002 {

    static class Point {
        int x, y;

        public Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(); // 상자의 가로칸수 M 입력 받기
        int N = sc.nextInt(); // 상자의 세로칸수 N 입력 받기

        int[][] box = new int[N][M]; // 2차원 배열 상자 선언

        Queue<Point> queue = new LinkedList<>(); // queue 큐 선언

        // 토마토 상태값 입력받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                box[i][j] = sc.nextInt();
                if (box[i][j] == 1) {
                    queue.offer(new Point(i, j));
                }
            }
        }

        // 큐가 빌 때까지 반복
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && box[ny][nx] == 0) {
                    box[ny][nx] = box[p.y][p.x] + 1;
                    queue.offer(new Point(ny, nx));
                }
            }
        }

        int max = 0;
        // 토마토가 다 익었는지 검사하고 걸린 일수 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) { // 안 익은 토마토가 있을 경우
                    System.out.println(-1); // 프로그램 종료
                    return;
                }
                max = Math.max(max, box[i][j]);
            }
        }

        System.out.println(max - 1);
    }
}
