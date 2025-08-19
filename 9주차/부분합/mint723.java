import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int answer = Integer.MAX_VALUE;

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());


        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int min = 0;
        int max = 0;

        while(min<=max && max<=N){
            if(sum < S){
                sum+=arr[max++];
            }else{
                answer = Math.min(answer, max-min);
                sum-=arr[min++];
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);

        br.close();
    }
}
