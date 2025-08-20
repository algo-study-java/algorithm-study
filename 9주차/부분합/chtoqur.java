import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());

    int[] arr = new int[N];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int minLen = Integer.MAX_VALUE;
    int sum = 0;
    int start = 0, end = 0;

    while (true) {
      if (sum >= S) { // S 이상: 최소 길이 갱신, start 이동
        minLen = Math.min(minLen, end - start);
        sum -= arr[start++];
      } else if (end == N) { // 배열 끝
        break;
      } else { // S 미만: end 확장
        sum += arr[end++];
      }
    }

    System.out.println(minLen == Integer.MAX_VALUE ? 0 : minLen);
  }
}
