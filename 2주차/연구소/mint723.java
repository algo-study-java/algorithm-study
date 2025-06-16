import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int N,M;
    static List<int[]> virusArr = new ArrayList<>();
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,1,-1};
    static int answer = 0;
    static int wall = 3; // 총 벽의 개수 (벽 3개를 반드시 세워야하기에 3으로 초기화)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
         /*
            1. 바이러스 퍼뜨리기
            2. 백트래킹으로 좌표 하나씩 벽 세워보기
         */

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                if(n==2){
                    virusArr.add(new int[]{i,j});
                }else if(n==1){
                    wall++;
                }

            }

        }

        dfs(3);
        System.out.println(answer);

        br.close();
    }

    static void dfs(int count){
        if(count>0){ // 벽을 3개 다 세우지 않았을 때
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] == 0){
                        map[i][j] = 1;
                        dfs(count-1);
                        map[i][j] = 0;
                    }
                }
            }
        }else{
            // 벽을 모두 세웠을 때 바이러스를 퍼뜨리고 안전영역 계산
            int[][] arr = deepCopy(map);
            bfs(arr);
        }
    }

    static void bfs(int[][] arr){
        // 바이러스 퍼뜨리기
        Queue<int[]> q = new ArrayDeque<>();
        for(int[] virus : virusArr){
            // 초기 바이러스 위치 q에 offer
            q.offer(new int[]{virus[0], virus[1]});
        }

        int count = virusArr.size(); // 초기 바이러스의 개수

        while(!q.isEmpty()){
            int[] info = q.poll();
            int y = info[0];
            int x = info[1];

            for(int i=0; i<4; i++){
                int ny = y + dy[i];
                int nx = x + dx[i];

                if(isValid(ny, nx, arr)){
                    arr[ny][nx] = 2;
                    count++; // 조건에 맞았을 때 전염시킨다면 바이러스의 개수 ++
                    q.offer(new int[]{ny, nx});
                }
            }
        }
        
        // 안전영역 == 전체 맵 크기 - 벽의 개수 - 바이러스의 개수
        answer = Math.max(M*N - wall - count, answer);
    }

    static boolean isValid(int y, int x, int[][] arr){
        return y>=0 && x>=0 && y<N && x<M && arr[y][x] == 0 ;
    }

    static int[][] deepCopy(int[][] arr){
        // 2차원 배열 깊은 복사
        int[][] result = new int[N][M];

        for(int i=0; i<arr.length; i++){
            System.arraycopy(arr[i], 0 , result[i], 0, arr[i].length);
        }

        return result;
    }
}
