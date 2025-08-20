import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    String str = br.readLine();

    Stack<Character> left = new Stack<>();
    Stack<Character> right = new Stack<>();

    for (int i = 0; i < str.length(); i++) {
      left.push(str.charAt(i));
    }

    int M = Integer.parseInt(br.readLine());
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      char cmd = st.nextToken().charAt(0);

      switch (cmd) {
        case 'L': // 커서 왼쪽으로 이동
          if (!left.isEmpty()) {
            right.push(left.pop());
          }
          break;
        case 'D': // 커서 오른쪽으로 이동
          if (!right.isEmpty()) {
            left.push(right.pop());
          }
          break;
        case 'B': // 커서 왼쪽 문자 삭제
          if (!left.isEmpty()) {
            left.pop();
          }
          break;
        case 'P': // 커서 왼쪽에 문자 삽입
          char target = st.nextToken().charAt(0);
          left.push(target);
          break;
      }
    }

    for (char c : left) {
      sb.append(c);
    }
    while (!right.isEmpty()) {
      sb.append(right.pop());
    }

    System.out.print(sb.toString());
  }
}
