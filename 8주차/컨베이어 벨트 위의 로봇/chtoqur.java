import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 위쪽 길이
    int K = Integer.parseInt(st.nextToken()); // 종료 조건
    int[] durability = new int[2 * N];
    boolean[] robot = new boolean[2 * N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < 2 * N; i++) {
      durability[i] = Integer.parseInt(st.nextToken());
    }

    int start = 0; // 올리는 위치
    int end = N - 1; // 내리는 위치
    int ans = 0; // 진행 중인 단계

    while (true) {
      ans++;

      // 1. 벹트 회전: 벨트가 한 칸 회전 >> 인덱스를 뒤로 한칸씩 밀기
      start = (start - 1 + 2 * N) % (2 * N);
      end = (end - 1 + 2 * N) % (2 * N);

      if (robot[end]) {
        robot[end] = false;
      }

      // 2. 가장 먼저 벨트에 올라간 로봇(내리는 위치에서 가까운 로봇)부터 이동
      for (int i = 0; i < N; i++) {
        int idx = (end - 1 - i + 2 * N) % (2 * N);
        int next = (idx + 1) % (2 * N);

        // idx에 로봇 존재 && 다음 칸에 로봇 없음 && 내구도 0 이상 일 때: 로봇 이동, 내구도 감소
        if (robot[idx] && !robot[next] && durability[next] > 0) {
          robot[idx] = false;
          robot[next] = true;
          durability[next]--;

          if (next == end) {
            robot[next] = false;
          }
        }
      }

      // 3. 올리는 위치에 로봇 올리기
      if (durability[start] > 0 && !robot[start]) {
        robot[start] = true;
        durability[start]--;
      }

      // 4. 내구도 0인 칸의 개수가 K개 이상: break
      int cnt = 0;
      for (int d : durability) {
        if (d == 0)
          cnt++;
      }
      if (cnt >= K)
        break;
    }

    System.out.println(ans);
  }
}
