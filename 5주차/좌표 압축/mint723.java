import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int[] sortedList = list.stream().distinct().sorted().mapToInt(i -> i).toArray();
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;

        for (int i : sortedList) {
            map.put(i, num);
            num++;
        }

        for(int i : list){
            sb.append(map.get(i)).append(" ");
        }

        System.out.println(sb);
        br.close();
    }
}
