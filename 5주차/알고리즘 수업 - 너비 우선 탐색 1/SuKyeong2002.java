import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SuKyeong2002 {
    static ArrayList<Integer>[] graph; // 인접 리스트 배열
    static int[] visitOrder; // 방문 순서를 저장할 배열
    static int order = 1; // 시작 정점의 방문 순서

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 정점의 수
        int M = sc.nextInt(); // 간선의 수
        int R = sc.nextInt(); // 시작 정점

        // 2차원 배열 가능 범위: 3000 x 3000 정도
        // 문제에서 최대 100000 x 100000 이므로 메모리 초과 발생!
        // 인접 리스트 배열 사용 (정점 수 10만, 간선 수 200만까지 가능)
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 정보 입력 + 양방향 간선 연결
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        // 각 정점의 인접 리스트를 오름차순으로 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        // 방문 순서를 저장할 배열 초기화
        visitOrder = new int[N + 1];

        bfs(R); // BFS 시작

        // 정점 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.println(visitOrder[i]);
        }
    }

    static void bfs(int R) {
        // 인접 정점부터 방문 = 들른 순서대로 꺼냄 = 선입선출 = 큐
        Queue<Integer> queue = new LinkedList<>();
        visitOrder[R] = order++; // 시작 정점(1) 방문하고 1씩 증가
        queue.add(R); // 시작 정점을 큐에 추가

        // 큐가 비지 않는다면 반복
        while (!queue.isEmpty()) {
            int now = queue.poll(); // 먼저 들어간 정점 꺼냄
            // 인접 정점 하나씩 확인
            for (int next : graph[now]) {
                // next 정점을 방문 안했다면
                if (visitOrder[next] == 0) {
                    visitOrder[next] = order++; // 인접 정점 방문하고 1씩 증가
                    queue.add(next); // 인접 정점을 큐에 추가
                }
            }
        }
    }
}
