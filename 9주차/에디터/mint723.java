import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        int N = sb.length();
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            String cmd = br.readLine();
            if(cmd.startsWith("P")){
                String[] s = cmd.split(" ");
                sb.insert(N, s[1]);
                N++;
                continue;
            }
            if(cmd.equals("B")){
                if(N == 0)
                    continue;
                sb.deleteCharAt(N-1);
                N--;
                continue;
            }

            if(cmd.equals("L")){
                if(N==0)
                    continue;
                N--;
            }else if(cmd.equals("D")){
                if(N==sb.length())
                    continue;
                N++;
            }
        }

        System.out.println(sb);
        br.close();
    }
}
