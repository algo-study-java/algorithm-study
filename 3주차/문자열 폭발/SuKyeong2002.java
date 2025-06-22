import java.util.Scanner;
import java.util.Stack;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next(); // 기본 문자열 입력받기
        String bombStr = sc.next(); // 폭발 문자열 입력받기

        // Character 타입의 stack 선언
        Stack<Character> stack = new Stack<>();

        // 기본 문자열을 돌면서 stack에 각각의 문자 추가
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            // 폭발 문자열 길이 이상 쌓였을 경우
            if (stack.size() >= bombStr.length()) {
                boolean isSame = true;

                // 폭발 문자열을 돌면서
                for (int j = 0; j < bombStr.length(); j++) {

                    // 같지 않을 경우 isSame을 false로 변환하고 반복문 중단
                    if (stack.get(stack.size() - bombStr.length() + j) != bombStr.charAt(j)) {
                        isSame = false;
                        break;
                    }
                }

                // 폭발 문자열과 같을 경우
                if (isSame == true) {

                    // 폭발 문자열의 길이만큼 돌면서 stack에서 삭제
                    for (int j = 0; j < bombStr.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        // stack이 비어있을 경우 "FRULA" 출력
        // 비어있지 앟은 경우 제거되고 남은 문자열만 출력 (순서 유지를 위해 StringBuilder 사용)
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {

            // StringBuilder sb 객체 선언
            StringBuilder sb = new StringBuilder();
            for (char c : stack) {
                sb.append(c);
            }

            // 문자열로 변환해서 출력
            System.out.println(sb.toString());
        }
    }
}
