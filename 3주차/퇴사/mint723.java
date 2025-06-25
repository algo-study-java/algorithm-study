import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int[] dp = new int[N+1];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            arr[i][0] = T;
            arr[i][1] = P;
        }

        for(int i=0; i<N; i++){
            // i == 현재 시간
            // arr[i][0] == 걸리는 시간
            // arr[i][1] == 받는 금액
            // dp[i] == i 번째 날에 받을 수 있는 최대 금액

            // 두 가지 경우의 수
            // 1. 상담한다
            // 2. 상담하지 않는다
            if(i + arr[i][0] <= N) {
                dp[i+arr[i][0]] = Math.max(dp[i+arr[i][0]], dp[i] + arr[i][1]);
            }

            dp[i+1] = Math.max(dp[i], dp[i + 1]); // 최댓값 누적
        }

        System.out.println(dp[N]);

        br.close();
    }
}
