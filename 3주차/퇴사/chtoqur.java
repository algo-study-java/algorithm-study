import java.io.*;
import java.util.*;

public class chtoqur {
	static String str, expStr;
	static char[] expArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][2]; // [0]: Ti, [1]: Pi
		int[] dp = new int[N + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			info[i][0] = Integer.parseInt(st.nextToken());
			info[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i]);
			
			int endDay = i + info[i][0];
			if (endDay <= N) { // 퇴사 전 처리 가능한 상담이라면
				dp[endDay] = Math.max(dp[endDay], dp[i] + info[i][1]);
			}
		}
		System.out.println(dp[N]);
	}
}