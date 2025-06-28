import java.util.*;
import java.io.*;

class Main {
    static int[][] map;
    static int N, M;
    static final int[] dy = {1,-1,0,0};
    static final int[] dx = {0,0,1,-1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 1});
        int answer = 0;

        while (!q.isEmpty()) {
            int[] info = q.poll();
            int y = info[0];
            int x = info[1];
            int score = info[2];

            if(y==N-1 && x == M-1){
                answer = score;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(!isValid(ny,nx))
                    continue;
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx, score+1});
            }
        }

        System.out.println(answer);

        br.close();
    }

    static boolean isValid(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M && map[y][x] == 1 && !visited[y][x];
    }
}
