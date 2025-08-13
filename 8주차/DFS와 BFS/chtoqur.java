import java.io.*;
import java.util.*;

public class chtoqur {

	static boolean[] visited;
	static List<Integer>[] list;
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int V = Integer.parseInt(st.nextToken()); // 탐색 시작 정점

		list = new ArrayList[N + 1];
		
        for (int i = 1; i <= N; i++) {
        	list[i] = new ArrayList<>();
        }

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(list[i]);
		}
		
		visited = new boolean[N + 1];
        DFS(V);
        sb.append("\n");

        visited = new boolean[N + 1];
        BFS(V);

        System.out.println(sb);
	}

	public static void DFS(int node) {
        visited[node] = true;
        sb.append(node).append(" ");

        for (int next : list[node]) {
            if (!visited[next]) {
                DFS(next);
            }
        }
    }
	
	public static void BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append(node).append(" ");

            for (int next : list[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
    }
}
