import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(i==0){
                    dp[i][j] = arr[i][j];
                } else {
                    if(j==0){
                        dp[i][j] += dp[i-1][j] + arr[i][j];
                    }else if(j == i){
                        dp[i][j] += dp[i-1][j-1] + arr[i][j];
                    }
                }
            }
        }

        for(int i=2; i<n; i++){
            for(int j=1; j<i; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + arr[i][j];
            }
        }

        System.out.println(Arrays.stream(dp[n - 1]).max().getAsInt());

        br.close();
    }
}
