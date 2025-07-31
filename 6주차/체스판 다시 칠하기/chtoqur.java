import java.io.*;
import java.util.*;

class chtoqur {
	static char[][] board;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		board = new char[N][M];	// W = true, B = false
		
		for (int r = 0; r < N; r++) {
			String line = br.readLine();
			for (int c = 0; c < M; c++) {
				board[r][c] = line.charAt(c);
			}
		}
		
		// 8x8 조각으로 잘라냄
		// i: 8 ~ N, 시작점: i - 8 
		// j: 8 ~ M, 시작점: j - 8
		for (int i = 8; i <= N; i++) {
			for (int j = 8; j <= M; j++) {
				paintBoard(i, j);
			}
		}
		
		System.out.println(ans);
	}
	
	public static void paintBoard(int n, int m) {
		int r = n - 8;	// 가로 시작점
		int c = m - 8;	// 세로 시작점
		
		// 시작점이 흰색이라고 가정하고 시작
		boolean color = true;	// W: true, B: false
		int count = 0;
		
		for (int i = r; i < n; i++) {
			for (int j = c; j < m; j++) {
				boolean bColor = board[i][j] == 'W' ? true : false;
				if (bColor != color) {
					count++;
				}
				color = !color;
			}
			color = !color;	// row 끝 색깔 = 다음 row 시작 색깔과 동일
		}
		
		// 시작점이 검은색인 경우와 비교
		int minCount = Math.min(count, 64 - count);
		ans = Math.min(ans, minCount);
	}
}