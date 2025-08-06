import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[][] nums = new int[N][];

    for (int i = 0; i < N; i++) {
      nums[i] = new int[i + 1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j <= i; j++) {
        nums[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = N - 2; i >= 0; i--) { // 아래에서 두번째 줄부터 Bottom-Up
      int len = nums[i].length;
      for (int j = 0; j < len; j++) {
        nums[i][j] += Math.max(nums[i + 1][j], nums[i + 1][j + 1]);
      }
    }

    System.out.println(nums[0][0]);
  }
}
