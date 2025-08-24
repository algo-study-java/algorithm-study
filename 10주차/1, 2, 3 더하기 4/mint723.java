import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dp = new int[10001];

        Arrays.fill(dp, 1);

//        dp[1] = 1; // 1
//        dp[2] = 2; // (1,1), (2)
//        dp[3] = 3; // (1,1,1), (1,2), (3)
//        dp[4] = 4; // (1,1,1,1), (1,1,2), (2,2), (1,3)
//        dp[5] = 5; // (1,1,1,1,1) (1,1,1,2) (1,2,2) (1,1,3) (2,3)
//        dp[6] = 7; // (1,1,1,1,1,1) (1,1,1,1,2) (1,1,2,2) (1,1,1,3) (1,2,3) (2,2,2) (3,3)
//        dp[7] = 8; // (1,1,1,1,1,1,1) (1,1,1,1,1,2) (1,1,1,2,2) (1,2,2,2) (1 1 1 1 3) (1 3 3)
//        // (1 1 2 3) (2 2 3)

        for(int i=2; i<=10000; i++){
            dp[i] += dp[i-2];
        }

        for(int i=3; i<=10000; i++){
            dp[i] += dp[i-3];
        }

        for(int i=0; i<N; i++){
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }

        System.out.println(sb);
    }
}
