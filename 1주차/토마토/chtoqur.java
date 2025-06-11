import java.io.*;
import java.util.*;
import java.awt.*;

public class chtoqur {
	static int N, M;
	
	static boolean checkBounds(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	public static void main(String[] args) throws IOException {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][M];
		int target = 0;
		int ans = 0;
		Queue<Point> Q = new LinkedList<Point>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				arr[i][j] = val;
				// 1: 익은 토마토, 0: 익지 않은 토마토, -1: 들어있지 않은 칸
				if (val == 0) {
					target++;
				} else if (val == 1) {
					Q.add(new Point(i, j));
				}
			}
		}
		
		// 이미 모두 익어있는 상태: 0 리턴
		if (target == 0) {
			System.out.println(0);
			return;
		}

		while (Q.size() > 0) {
			ans++;
			int s = Q.size();

			for (int i = 0; i < s; i++) {
				Point p = Q.poll();
				int x = p.x, y = p.y;

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (checkBounds(nx, ny) && arr[nx][ny] == 0) {
						arr[nx][ny] = 1;
						target--;
						Q.add(new Point(nx, ny));
					}
				}
			}

			if (target == 0)
				break;
		}
		
		if (target > 0) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}
}
