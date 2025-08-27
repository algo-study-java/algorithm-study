import java.io.*;
import java.util.*;

public class chtoqur {
  static int N;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    
    System.out.println(Z(N, r, c));
  }

  private static int Z(int n, int r, int c) {
    int ans = 0;

    if (n > 0) {
      int mid = (int) Math.pow(2, n) / 2; // 한 변의 길이 / 2

      // 1) 현재 속한 위치 이전의 영역 만큼 미리 더하기
      // 2) 내부 좌표로 바꾼 뒤 재귀
      if (r < mid && c < mid) { // 1: 왼쪽 위
        ans += Z(n - 1, r, c);
      } else if (r < mid && c >= mid) { // 2: 오른쪽 위
        ans += (mid * mid) + Z(n - 1, r, c - mid);
      } else if (r >= mid && c < mid) { // 3: 왼쪽 아래
        ans += (mid * mid * 2) + Z(n - 1, r - mid, c);
      } else { // 4: 오른쪽 아래
        ans += (mid * mid * 3) + Z(n - 1, r - mid, c - mid);
      }
    }
    return ans;
  }
}
