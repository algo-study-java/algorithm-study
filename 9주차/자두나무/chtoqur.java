import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int T = Integer.parseInt(st.nextToken()); // T초 동안 낙하
    int W = Integer.parseInt(st.nextToken()); // 이동 횟수

    int[] pos = new int[T + 1];
    for (int i = 1; i <= T; i++) {
      pos[i] = Integer.parseInt(br.readLine());
    }

    int[][] dp = new int[T + 1][W + 1];
    for (int t = 1; t <= T; t++) {

      // 이동하지 않은 경우: 자두 초기값 == 1
      if (pos[t] == 1) { // 자두 위치 1: +1
        dp[t][0] = dp[t - 1][0] + 1;
      } else { // 자두 위치 2: +0
        dp[t][0] = dp[t - 1][0];
      }

      // 이동이 있는 경우
      for (int w = 1; w <= W; w++) {
        // 자두가 떨어지는 위치와 내 위치가 동일할 때
        if ((pos[t] == 1 && w % 2 == 0) || (pos[t] == 2 && w % 2 == 1)) {
          dp[t][w] = Math.max(dp[t - 1][w - 1], dp[t - 1][w]) + 1;
        } else {
          dp[t][w] = Math.max(dp[t - 1][w - 1], dp[t - 1][w]);
        }
      }
    }

    int max = 0;
    for (int w = 0; w <= W; w++) {
      max = Math.max(max, dp[T][w]);
    }

    System.out.println(max);
  }
}
