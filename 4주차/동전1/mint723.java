import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        int[] dp = new int[k+1];

        for(int i=0; i<n; i++){
            coins[i] = Integer.parseInt(br.readLine());
        }
        //  1   1   1   1   1   1   1   1   1   1
        //  1   2   2   3   3   4   4   5   5   6
        //  1   2   2   3   4   5   6   7   8   10

        /*
        동전의 종류의 개수만큼 반복한다고 가정했을 때 -> i
        해당 반복문 안에서 coins[i] 부터 반복 수행 -> j
        dp[j]의 값은 이전에 이미 수행된 dp[j] 값 + dp[j-현재 동전의 크기]
        dp[j] = dp[j] + dp[j-coins[i]];
         */

        dp[0] = 1;

        for(int i=0; i<n; i++){
            for(int j=coins[i]; j<=k; j++){
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }

        System.out.println(dp[k]);

        br.close();
    }
}
