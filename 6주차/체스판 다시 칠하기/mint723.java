import java.io.*;
import java.util.*;

class Main {
    static int N,M;
    static char[][] map;
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int answer = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            String s = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int result = bfs(newMap(i, j));
                answer = Math.min(answer,result);
            }
        }
        System.out.println(answer);
        br.close();
    }

    static int bfs(char[][] map){
        if(map==null)
            return Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[8][8];
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        int count = 0;

        while (!q.isEmpty()){
            int[] info = q.poll();
            int y = info[0];
            int x = info[1];

            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(!(ny>=0 && nx>=0 && ny<8 && nx<8 && !visited[ny][nx]))
                    continue;

                if(map[y][x] == map[ny][nx]){
                    count++;
                    map[ny][nx] = map[ny][nx] == 'W'?'B':'W';
                }
                visited[ny][nx] = true;
                q.offer(new int[]{ny,nx});
            }
        }

        return Math.min(count, 64 - count);
    }

    static char[][] newMap(int y, int x){
        if(!(y+8 <= N && x+8 <= M))
            return null;

        char[][] newMap = new char[8][8];

        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                newMap[i][j] = map[y+i][x+j];
            }
        }
        return newMap;
    }
}
