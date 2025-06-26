import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            arr[i][0] = S;
            arr[i][1] = T;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr[0][1]); // 첫번째 강의가 끝나는 시간

        for(int i=1; i<N; i++){
            if(!pq.isEmpty() && arr[i][0]>=pq.peek()){ 
                // 강의실을 같이 쓸 수 있는 경우
                // 강의의 시작 시간이, 가장 빨리 끝나는 강의 시간보다 크거나 같은 경우
                pq.poll();
            }
            pq.offer(arr[i][1]); // 이번 강의가 끝나는 시간 offer
        }
        System.out.println(pq.size()); // pq의 개수 == 사용하는 강의실의 개수

        br.close();
    }
}
