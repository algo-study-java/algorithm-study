import java.io.*;
import java.util.*;

class chtoqur {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] input = new int[N];
		Set<Integer> set = new TreeSet<>();	// 오름차순 자동 정렬
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			input[i] = num;
			set.add(num);
		}
		
		// 각 숫자 별 만족하는 좌표 압축 결과 저장
		Map<Integer, Integer> seq = new LinkedHashMap<>();
		int cnt = 0;
		for (int num : set) {
			seq.put(num, cnt++);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N - 1; i++) {
			sb.append(seq.get(input[i])).append(" ");
		}
		sb.append(seq.get(input[N-1]));
		
		System.out.println(sb.toString());
	}
}