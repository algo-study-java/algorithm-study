import java.io.*;

public class chtoqur {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    int MAX = 10000;
    int[] dp = new int[MAX + 1];

    dp[0] = 1;
    for (int k = 1; k <= 3; k++) {
      for (int i = 1; i <= MAX; i++) {
        if (i >= k) {
          dp[i] += dp[i - k];
        }
      }
    }

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      sb.append(dp[N]).append('\n');
    }

    System.out.println(sb);
  }
}
