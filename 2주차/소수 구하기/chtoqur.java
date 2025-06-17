import java.io.*;
import java.util.*;

public class chtoqur {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		boolean[] isComp = new boolean[1000001];
		isComp[1] = true;

		for (int n = 2; n * n <= N; n++) {
			if (isComp[n]) {
				continue;
			}
			
			for (int num = n * n; num <= N; num += n) {
				isComp[num] = true;
			}
		}
		
		for (int i = M; i <= N; i++) {
			if (!isComp[i]) {
				sb.append(i).append("\n");
			}
		}
		
		System.out.println(sb.toString().trim());
	}
}