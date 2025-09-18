class Solution {
    boolean[] visited;
    int[][] map;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        map = computers;
        
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    void dfs(int idx){
        visited[idx] = true;
        for(int i=0; i<map.length; i++){
            if(i!=idx && !visited[i] && map[idx][i] == 1){
                dfs(i);
            }
        }
    }
}
