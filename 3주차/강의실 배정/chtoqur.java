import java.io.*;
import java.util.*;

class Lecture {
	int st;		// start time
	int et;		// end time
	
	public Lecture(int st, int et) {
		this.st = st;
		this.et = et;
	}
}

public class chtoqur {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Lecture[] lectures = new Lecture[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			lectures[i] = new Lecture(start, end);
		}
		
		Arrays.sort(lectures, (a, b) -> a.st - b.st);
		
		PriorityQueue<Integer> endTimes = new PriorityQueue<>();
		endTimes.add(lectures[0].et);
		
		for (int i = 1; i < N; i++) {
			if (lectures[i].st >= endTimes.peek()) {
				endTimes.poll();
			}
			endTimes.add(lectures[i].et);
		}
		
		System.out.println(endTimes.size());
	}
}