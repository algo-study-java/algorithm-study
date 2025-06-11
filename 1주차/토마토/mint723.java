import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static int M,N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1,-1, 0, 0};

        int answer = 0;

        Queue<int[]> q = new ArrayDeque<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    q.offer(new int[]{i, j, 0});
                }
            }
        }

        while (!q.isEmpty()){
            int[] info = q.poll();
            int y = info[0];
            int x = info[1];
            int score = info[2];

            answer = Math.max(answer, score);
            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];
                if(isValid(ny, nx)){
                    map[ny][nx] = 1;
                    q.offer(new int[]{ny, nx, score + 1});
                }
            }
        }

        boolean flag = true;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0)
                    flag = false;
            }
        }

        if (flag)
            System.out.println(answer);
        else {
            System.out.println(-1);
        }

    }

    static boolean isValid(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M && map[y][x] == 0;
    }
}
