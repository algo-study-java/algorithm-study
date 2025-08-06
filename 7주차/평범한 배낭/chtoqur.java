import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 물품의 수
		int K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

		int[] weights = new int[N];
		int[] vals = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i] = Integer.parseInt(st.nextToken());
			vals[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N+1][K+1];

		for (int i = 1; i <= N; i++) {
			for (int w = 0; w <= K; w++) {
				if (weights[i-1] <= w) {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - weights[i-1]] + vals[i-1]);
				} else {
					dp[i][w] = dp[i-1][w];
				}
			}
		}

		System.out.println(dp[N][K]);
	}
}