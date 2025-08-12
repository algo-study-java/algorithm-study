import java.io.*;
import java.util.*;

class Main{
    static int N, M, V;
    static List<Integer>[] adjList;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        adjList = new List[N+1];
        visited = new boolean[N+1];

        for(int i=0; i<=N; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }

        for(int i=1; i<=N; i++){
            Collections.sort(adjList[i]);
        }

        dfs(V);
        sb.append('\n');
        visited = new boolean[N+1];
        bfs();

        System.out.println(sb);

        br.close();
    }

    static void dfs(int node){
        if(visited[node])
            return;

        visited[node] = true;
        sb.append(node).append(' ');

        for(int next : adjList[node]){
            if(!visited[next]){
                dfs(next);
            }
        }
    }

    static void bfs(){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(V);
        visited[V] = true;

        while (!q.isEmpty()){
            int node = q.poll();
            sb.append(node).append(' ');

            for (int next : adjList[node]){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }
}
