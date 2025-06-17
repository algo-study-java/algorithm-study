import java.io.*;
import java.util.*;
import java.awt.*;

public class chtoqur {
	static int N, M;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static LinkedList<Point> virusList = new LinkedList<Point>();
	static int emptyCnt = 0;
	static int[][] emptyPt = new int[64][2];
	static int max = 0;
	
	static boolean checkBounds(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	static void spreadVirus() {
		int safeArea = emptyCnt - 3;
		boolean[][] visited = new boolean[N][M];
		Queue<Point> Q = new ArrayDeque<>();
		
		int size = virusList.size();
		for (int i = 0; i < size; i++) {
			Q.add(virusList.get(i));
		}
		
		while (Q.size() > 0) {
			Point p = Q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if (checkBounds(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny]) {
					visited[nx][ny] = true;
					Q.add(new Point(nx, ny));
					safeArea--;
				}
			}
			
			if (safeArea == 0) {
				return;
			}
		}
		
		max = Math.max(safeArea, max);
	}
	
	static void buildWall(int cnt, int start) {
		if (cnt == 3) {
			spreadVirus();
			return;
		}
		
		for (int i = start; i < emptyCnt; i++) {
			int x = emptyPt[i][0];
			int y = emptyPt[i][1];
			map[x][y] = 1;
			buildWall(cnt + 1, i + 1);
			map[x][y] = 0;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				int num = Integer.parseInt(st.nextToken());
				map[r][c] = num;
				
				if (num == 0) {
					emptyPt[emptyCnt][0] = r;
					emptyPt[emptyCnt][1] = c;
					emptyCnt++;
				} else if (num == 2) {
					virusList.add(new Point(r, c));
				}
			}
		}
		
		buildWall(0, 0);
		System.out.println(max);
	}
}