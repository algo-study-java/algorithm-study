import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class SuKyeong2002 {
    static ArrayList<Integer>[] graph; // 인접 리스트 배열
    static int[] visitOrder; // 방문 순서를 저장할 배열
    static int order = 1; // 시작 정점의 방문 순서
    static boolean[] visited; // 방문 유무저장하는 배열

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

        // 각 정점의 인접 리스트를 역순으로 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], Collections.reverseOrder());
        }

        // 방문 순서를 저장할 배열 초기화
        visitOrder = new int[N + 1];
        visited = new boolean[N + 1];

        dfs(R); // DFS 시작

        // 정점 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.println(visitOrder[i]);
        }
    }

    static void dfs(int R) {
        // 마지막에 들른 것부터 꺼냄 = 후입선출 = 스택
        Stack<Integer> stack = new Stack<>();
        stack.push(R); // 시작 정점을 스택에 추가

        // 스택이 비지 않는다면 반복
        while (!stack.isEmpty()) {
            int now = stack.pop();

            if (!visited[now]) {
                visited[now] = true;
                visitOrder[now] = order++; // 인접 정점 방문하고 1씩 증가

                // 역순으로 넣었기 때문에 작은 번호가 나중에 꺼내짐 → 오름차순 방문 보장
                for (int next : graph[now]) {
                    if (!visited[next]) {
                        stack.push(next);
                    }
                }
            }
        }
    }
}
