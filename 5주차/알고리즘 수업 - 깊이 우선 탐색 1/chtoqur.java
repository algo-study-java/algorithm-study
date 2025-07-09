import java.io.*;
import java.util.*;

class Main {
	static int N;
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int[] ans;
	static int cnt = 1; // 방문 순서

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); 		// 정점의 수
		int M = Integer.parseInt(st.nextToken()); 	// 간선의 수
		int R = Integer.parseInt(st.nextToken()); 	// 시작 정점

		visited = new boolean[N + 1];
		ans = new int[N + 1];

		// 그래프 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 그래프 정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		// 오름차순 정렬
		for (int i = 1; i <= N; i++) {
			Collections.sort(graph.get(i));
		}

		dfs(R);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int r) {
		visited[r] = true;
		ans[r] = cnt++;

		for (int node : graph.get(r)) {
			if (!visited[node]) {
				dfs(node);
			}
		}
	}
}