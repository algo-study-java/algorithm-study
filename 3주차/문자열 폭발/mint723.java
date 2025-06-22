import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        String target = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);

            if(stack.size()>=target.length()){
                int count = 0;
                for (int j = 0; j < target.length(); j++) {
                    if(stack.get(stack.size()-target.length()+j) == target.charAt(j)){
                        count++;
                    }
                }

                if(count == target.length()){
                    for(int j=0; j < target.length(); j++){
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty())
            sb.append(stack.pop());

        System.out.println(sb.length() == 0?"FRULA":sb.reverse());

        br.close();
    }
}
