import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        visited = new boolean[N];
        answer = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(1, 500 + arr[i] - K);
            visited[i] = false;
        }
        System.out.println(answer);

        br.close();
    }

    static void dfs(int depth, int score) {
        if (depth == N) {
            answer++;
            return;
        }

        if (score >= 500) {
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(depth + 1, score - K + arr[i]);
                    visited[i] = false;
                }
            }
        }
    }
}
