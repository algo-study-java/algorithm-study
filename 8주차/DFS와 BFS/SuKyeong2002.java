import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SuKyeong2002 {
    // 그래프를 저장할 인접 리스트 배열 (정점 번호별로 연결된 정점들을 저장)
    static ArrayList<Integer>[] graph;
    // 방문 여부를 저장하는 배열
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 정점의 개수
        int M = sc.nextInt(); // 간선의 개수
        int V = sc.nextInt(); // 탐색 시작 정점 번호

        // 그래프 배열 초기화 (정점 개수만큼 ArrayList 생성)
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 연결 정보 입력 (양방향)
        for (int i = 0; i < M; i++) { // ← 원래 N이 아니라 M이어야 함
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b); // a번 정점에서 b번 정점으로 연결
            graph[b].add(a); // b번 정점에서 a번 정점으로 연결
        }

        // 각 정점의 인접 리스트를 오름차순으로 정렬
        // 예: graph[1] = [3, 2] -> graph[1] = [2, 3]
        for (int i = 1; i <= N; i++) { // ← N까지 해야 모든 정점 정렬 가능
            Collections.sort(graph[i]);
        }

        visited = new boolean[N + 1]; // 방문 여부 초기화
        dfs(V); // DFS 탐색 시작
        System.out.println();

        visited = new boolean[N + 1]; // 방문 여부 초기화
        bfs(V); // BFS 탐색 시작
    }

    // 깊이 우선 탐색(DFS) - 재귀 방식
    static void dfs(int v) {
        visited[v] = true; // 현재 정점 방문 처리
        System.out.print(v + " "); // 방문한 정점 출력

        // 현재 정점과 연결된 정점들을 차례대로 확인
        for (int nxt : graph[v]) {
            if (!visited[nxt]) { // 아직 방문하지 않은 정점이면
                dfs(nxt); // 재귀 호출
            }
        }
    }

    // 너비 우선 탐색(BFS) - 큐 사용
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>(); // BFS에 사용할 큐
        visited[start] = true; // 시작 정점 방문 처리
        q.offer(start); // 큐에 시작 정점 추가

        while (!q.isEmpty()) { // 큐가 빌 때까지 반복
            int cur = q.poll(); // 큐에서 꺼낸 현재 정점
            System.out.print(cur + " "); // 현재 정점 출력

            // 현재 정점과 연결된 정점들을 확인
            for (int nxt : graph[cur]) {
                if (!visited[nxt]) { // 방문하지 않았다면
                    visited[nxt] = true; // 방문 처리
                    q.offer(nxt); // 큐에 추가
                }
            }
        }
    }
}
