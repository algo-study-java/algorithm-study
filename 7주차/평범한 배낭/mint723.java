import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][2];
        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            arr[i][0] = W; arr[i][1] = V;
        }

        for(int i=1; i<N+1; i++){
            for(int j=1; j<K+1; j++){
                dp[i][j] = dp[i-1][j];
                if(arr[i][0]<=j){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i][0]] + arr[i][1]);
                }
            }
        }
        System.out.println(dp[N][K]);

        br.close();
    }
}
