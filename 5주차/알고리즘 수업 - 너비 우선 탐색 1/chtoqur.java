import java.io.*;
import java.util.*;

class chtoqur {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 			// 정점의 수
		int M = Integer.parseInt(st.nextToken()); 			// 간선의 수
		int R = Integer.parseInt(st.nextToken()); 			// 시작 정점
		List<List<Integer>> graph = new ArrayList<>(N + 1);	// 크기 지정: 내부 배열(초기 용량 디폴트 10) resize 비용 절감
		boolean[] visited = new boolean[N + 1];
		int[] ans = new int[N + 1];

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
		
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(R);
		visited[R] = true;
		int cnt = 1; // 방문 순서
		
		while(!Q.isEmpty()) {
			int node = Q.poll();
			ans[node] = cnt++;
			
			for (int next : graph.get(node)) {
				if (!visited[next]) {
					visited[next] = true;
					Q.add(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(ans[i]).append("\n");
		}
		System.out.print(sb);
	}
}