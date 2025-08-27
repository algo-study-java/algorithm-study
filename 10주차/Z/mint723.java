import java.io.*;
import java.util.*;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);
        count(size, r, c);
        System.out.println(answer);

        br.close();
    }

    public static void count(int size, int r, int c){
        if(size == 1)
            return;

        int n = size / 2;

        if(r < n && c < n){ // left top
            answer += n * n * 0;
            count(n, r, c);
        }

        if(r < n && c>=n){ // right top
            answer += n * n * 1;
            count(n, r, c-n);
        }

        if(r>=n && c<n){ // left bottom
            answer += n * n * 2;
            count(n, r-n, c);
        }

        if(r>=n && c>=n){ // right bottom
            answer += n * n * 3;
            count(n, r-n, c-n);
        }
    }
}
