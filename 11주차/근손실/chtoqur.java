import java.io.*;
import java.util.*;

public class chtoqur {
  static int N, K;
  static int[] arr;
  static boolean[] visited;
  static int ans = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // N개의 운동 키트
    K = Integer.parseInt(st.nextToken()); // 하루에 K씩 중량 감소
    arr = new int[N];
    visited = new boolean[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    dfs(0, 500);
    System.out.println(ans);
  }

  static void dfs(int day, int weight) {
    if (day == N) {
      ans++;
      return;
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i]) {
        int next = weight + arr[i] - K; // 오늘 운동 후 중량
        if (next >= 500) {
          visited[i] = true;
          dfs(day + 1, next);
          visited[i] = false;
        }
      }
    }
  }
}
