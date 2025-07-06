import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        List<Integer>[] adjList = new List[N+1];
        boolean[] visited = new boolean[N+1];
        int[] answer = new int[N+1];
        int count = 1;

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

        for(List<Integer> list : adjList){
            list.sort((o1,o2) -> o1 - o2);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(R);
        visited[R] = true;
        answer[R] = count++;

        while(!q.isEmpty()){
            int curNode = q.poll();

            for(int nextNode : adjList[curNode]){
                if(!visited[nextNode]) {
                    q.offer(nextNode);
                    visited[nextNode] = true;
                    answer[nextNode] = count++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++){
            sb.append(answer[i]).append('\n');
        }

        System.out.println(sb);

        br.close();
    }
}
