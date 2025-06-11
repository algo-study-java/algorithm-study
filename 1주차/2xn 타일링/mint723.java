import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[Math.max(2,N)];
        dp[0] = 1;
        dp[1] = 2;
        for(int i=2; i<N; i++){
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }
        
        System.out.println(dp[N-1]);
        
        br.close();
    }
}
