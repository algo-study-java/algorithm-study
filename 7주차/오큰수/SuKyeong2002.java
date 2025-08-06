import java.util.Scanner;
import java.util.Stack;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 크기
        int[] sequence = new int[N]; // 수열을 저장할 배열
        int[] answer = new int[N]; // 오큰수 저장할 배열
        Stack<Integer> stack = new Stack<>(); // 인덱스 저장용 스택

        // 수열 배열의 원소 입력 받기
        for (int i = 0; i < N; i++) {
            sequence[i] = sc.nextInt();
        }

        /*
         * 오큰수 구하기 (배열 절망편)
         * for (int i = 0; i < N; i++) {
         * int min = -1; // 오큰수 기본값
         * // 조건 1. Ai보다 큰 수 중
         * for (int j = i + 1; j < N; j++) {
         * // 조건 2. 가장 왼쪽에 있는 수
         * if (sequence[j] > sequence[i]) {
         * min = sequence[j];
         * break;
         * }
         * }
         * // 없을 시 기본값 저장
         * answer[i] = min;
         * }
         */

        // 오큰수 구하기 (스택 희망편)
        for (int i = 0; i < N; i++) {
            /*
             * 스택이 비지 않는 않고, 스택의 맨 위 값 < 현재 값일 경우 반복
             * peek() : 스택의 맨 위 값 보기 (제거 안 함)
             * 예: [0, 1, 2, 3] -> 3 (스택 그대로 [0, 1, 2, 3])
             */
            while (!stack.isEmpty() && sequence[stack.peek()] < sequence[i]) {
                /*
                 * 현재 값을 오큰수 배열에 넣기
                 * pop() : 스택의 맨 위 값 꺼내서 반환 (제거함)
                 * 예: [0, 1, 2, 3] -> 3 (스택 변경 [0, 1, 2)
                 */
                answer[stack.pop()] = sequence[i];
            }
            /*
             * 현재 인덱스를 스택에 넣기
             * 예: push(0) : [] -> [0]
             */
            stack.push(i);
        }

        // 스택이 비어 있지 않을 경우 반복
        while (!stack.isEmpty()) {
            // 오큰수 없을 경우 -1 넣기
            answer[stack.pop()] = -1;
        }

        // 오큰수 출력 (구분: 공백)
        for (int i = 0; i < N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
