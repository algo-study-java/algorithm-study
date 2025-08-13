import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] belt = new int[2*N];
        boolean[] robots = new boolean[N];

        for (int i = 0; i < 2*N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;

        while(true) {
            answer++;

            int last = belt[2*N-1];
            for(int i=2*N-1; i>0; i--) {
                belt[i] = belt[i-1];
            }
            belt[0] = last;

            for(int i=N-1; i>0; i--) {
                robots[i] = robots[i-1];
            }
            robots[0] = false;

            if(robots[N-1]) {
                robots[N-1] = false;
            }

            for(int i=N-2; i>=0; i--) {
                if(robots[i]) {
                    int nextPos = i+1;
                    if(!robots[nextPos] && belt[nextPos] > 0) {
                        robots[i] = false;
                        robots[nextPos] = true;
                        belt[nextPos]--;

                        if(nextPos == N-1) {
                            robots[nextPos] = false;
                        }
                    }
                }
            }

            if(belt[0] > 0) {
                robots[0] = true;
                belt[0]--;
            }

            int cnt = 0;
            for(int i=0; i<2*N; i++) {
                if(belt[i] == 0) cnt++;
            }

            if(cnt >= K) break;
        }

        System.out.println(answer);
        br.close();
    }
}
