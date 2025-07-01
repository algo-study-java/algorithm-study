import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 0;

        int[] arr = new int[N];
        long low = 1;
        long mid;
        long high = 0;

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
            high = Math.max(high, arr[i]);
        }
        

        while(low<=high){
            mid = (low + high)/ 2;

            int n = 0;
            for(int i=0; i<N; i++){
                if(mid != 0){
                    n+=arr[i]/mid;
                }
            }

            if(n<K){ // 목표 개수보다 적게 나왔을 때 -> 더 잘게 자르기
                high = mid -1;
            }else{ 
                // 목표 개수보다 더 크거나 같게 나왔을 때 -> 더 크게 자르기
                // 최대 조각 내에서 최댓값을 찾기 위한 조건이기도 함
                answer = mid;
                low = mid + 1;
            }
        }
        System.out.println(answer);
        br.close();
    }
}
