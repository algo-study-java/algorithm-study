import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[T+1][2][W+1];
        int[] arr = new int[T+1];

        for(int i=1; i<=T; i++){
            arr[i] = Integer.parseInt(br.readLine())- 1;
        }

        int answer = 0;

        for(int i=1; i<=T; i++){
            for(int j=0; j<=W; j++){
                if(j == 0){
                    dp[i][0][j] = dp[i-1][0][j] + (arr[i] == 0 ? 1 : 0);
                }else{
                    dp[i][0][j] = Math.max(dp[i-1][0][j], dp[i-1][1][j-1]) + (arr[i] == 0 ? 1 : 0);
                    dp[i][1][j] = Math.max(dp[i-1][1][j], dp[i-1][0][j-1]) + (arr[i] == 1 ? 1 : 0);
                }

                answer = Math.max(answer, Math.max(dp[i][0][j], dp[i][1][j]));
            }
        }

        System.out.println(answer);

        br.close();
    }
}
