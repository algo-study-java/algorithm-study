class Solution {
    public int solution(int n, int[][] computers) {
        // 각 컴퓨터의 방문 여부를 저장하는 배열
        boolean[] visited = new boolean[n];

        // 최종적으로 반환할 네트워크 개수
        int networkCount = 0;

        // 모든 컴퓨터를 순회
        for (int i = 0; i < n; i++) {

            // 아직 방문하지 않은 컴퓨터라면 새로운 네트워크 발견
            if (!visited[i]) {
                dfs(i, computers, visited); // 연결된 모든 컴퓨터 탐색
                networkCount++; // 네트워크 개수 증가
            }
        }
        return networkCount;
    }

    // 깊이 우선 탐색(DFS) 메서드
    private void dfs(int node, int[][] computers, boolean[] visited) {
        visited[node] = true; // 현재 노드 방문 처리

        // 해당 노드와 연결된 다른 노드 확인
        for (int j = 0; j < computers.length; j++) {

            // computers[node][j] == 1 → 연결되어 있음
            // !visited[j] → 아직 방문하지 않은 경우
            if (computers[node][j] == 1 && !visited[j]) {
                dfs(j, computers, visited); // 재귀적으로 탐색
            }
        }
    }
}
