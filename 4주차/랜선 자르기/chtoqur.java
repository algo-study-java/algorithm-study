import java.io.*;
import java.util.*;

public class chtoqur {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());	// 랜선 개수
		int N = Integer.parseInt(st.nextToken());	// 필요한 랜선의 개수
		
		int[] lines = new int[K];
		long max = Integer.MIN_VALUE;
		
		for (int i = 0; i < K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lines[i]);
		}
		
		long min = 1;
		
		while (min <= max) {
			long mid = (min + max) / 2;
			long cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += lines[i] / mid;
				
				if (cnt >= N)
					break;
			}
			
			// 탐색에 실패한 mid 값을 제외한 +=1 값을 새로 설정
			if (cnt < N) {
				max = mid - 1;
			} else {	// N 충족시킨 경우: 최대 랜선의 길이 구하기
				min = mid + 1;
			}
		}
		
		System.out.println(max);
	}
}