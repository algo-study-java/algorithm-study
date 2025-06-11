import java.io.*;
import java.util.*;

public class chtoqur {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    Queue<Integer> Q = new LinkedList<>();
    for (int i = 1; i <= N; i++) {
    	Q.add(i);
    }
    
    StringBuilder sb = new StringBuilder();
    sb.append('<');
    
    while(Q.size() > 1) {
    	for (int i = 0; i < K - 1; i++) {
    		Q.add(Q.poll());
    	}
    	sb.append(Q.poll()).append(", ");
    }
    
    sb.append(Q.poll()).append('>');
    System.out.println(sb);
  }
}