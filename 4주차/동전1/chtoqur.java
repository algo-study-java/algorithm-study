import java.io.*;

class chtoqur {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);		// n가지 종류의 동전
		int k = Integer.parseInt(input[1]);		// 가치: k원이 되도록

		int[] vals = new int[n];	// 동전 별 가치
		for (int i = 0; i < n; i++) {
			vals[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= k; j++) {
				// 현재 동전의 가치(val)가 반영될 수 있는 j에 한해서
				if (j >= vals[i]) {
					// 1) j-1 동전까지 포함한 경우의 수 +
					// 2) j 동전까지 포함한 경우의 수
					dp[j] = dp[j] + dp[j - vals[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
	}
}