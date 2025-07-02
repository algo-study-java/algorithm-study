import java.io.*;
import java.util.*;

public class chtoqur {

	static int N, M;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		int[][] maze = new int[N][M];

		for (int r = 0; r < N; r++) {
			String row = br.readLine();
			for (int c = 0; c < M; c++) {
				maze[r][c] = row.charAt(c) - '0';
			}
		}

		Queue<int[]> Q = new ArrayDeque<>();
		Q.add(new int[] {0, 0, 1}); // dis: 시작위치 포함
		maze[0][0] = 2; // 시작 위치 재방문 방지

		while (!Q.isEmpty()) {
			int[] pt = Q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = pt[0] + dx[d];
				int ny = pt[1] + dy[d];
				int nd = pt[2] + 1;

				if (nx == N - 1 && ny == M - 1) {
					maze[nx][ny] = Math.max(maze[nx][ny], nd);
					System.out.println(maze[N-1][M-1]);
					return;
				}

				// 1) 처음 방문하는 곳이거나, 2) 현재 dis = 최단 dis로 갱신 가능한 경우
				if (checkBounds(nx, ny) && (maze[nx][ny] == 1 || nd < maze[nx][ny])) {
					Q.add(new int[] {nx, ny, nd});
					maze[nx][ny] = nd;
				}
			}
		}
	}

	static boolean checkBounds(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}