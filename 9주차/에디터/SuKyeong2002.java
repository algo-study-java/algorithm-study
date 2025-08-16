import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SuKyeong2002 {

    /*
     * 커서를 | 이라고 가정하고 아래는 입출력 예시 (커서 출력 X)
     * abcd|
     * abcdx|
     * abcd|x
     * abcdy|x
     * 방법 1. 선입선출로 출력해서 한 쪽 끝에서만 데이터 삽입/삭제하는 큐 사용?
     * 방법 1 문제점. 커서 위치가 중간일 경우 시간 초과 (O(n)) 나서 안 됨
     * 방법 1 해결방안. 양쪽 끝에서 넣고 뺄 수 있는 덱 (Double Ended Que) 사용?
     * 방법 1 해결방안. 덱 사용 시 커서 위치로 왼/오 각각 덱에 저장해서 중간 조작 필요 없게 만듦
     */

    // readLine() 호출 -> 입출력 오류 발생 가능 -> 'throws IOException' 필수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * 초기 문자열 (1 ≤ sentence.length ≤ 100,000)
         * 명령 개수 (1 ≤ cmdNum ≤ 500,000)
         */
        String sentence = br.readLine();
        int cmdNum = Integer.parseInt(br.readLine());

        /*
         * 인터페이스: Deque
         * 구현 클래스: ArrayDeque, LinkedList 등
         * ArrayDeque: 배열 한 칸(Object 참조) + Character 객체 -> 캐시 효율 좋음
         * LinkedList: 노드 객체(data, prev, next 참조) + Character 객체 -> 메모리 오버헤드 큼
         * 결론: ArrayDeque 사용
         */
        Deque<Character> left = new ArrayDeque<>();
        Deque<Character> right = new ArrayDeque<>();

        /*
         * 커서 맨 뒤 -> 초기 문자열의 각각의 문자를 left에 삽입
         * 예: abcd| -> left[a, b, c, d]
         */
        for (int i = 0; i < sentence.length(); i++) {
            left.push(sentence.charAt(i));
        }

        for (int i = 0; i < cmdNum; i++) {
            // 명령 입력받기
            String line = br.readLine();
            char cmd = line.charAt(0);

            // 명령에 따른 삽입/삭제
            switch (cmd) {
                // 명령이 'L'이고 커서가 문장의 맨 앞이 아닐 경우
                case 'L':
                    if (!left.isEmpty()) {
                        // left의 맨 위 문자를 하나 꺼내서 right에 삽입
                        right.push(left.pop());
                    }
                    break;
                // 명령이 'D'이고 커서가 문장의 맨 뒤가 아닐 경우
                case 'D':
                    if (!right.isEmpty()) {
                        // right의 맨 위 문자를 하나 꺼내서 left에 삽입
                        left.push(right.pop());
                    }
                    break;
                // 명령이 'B'이고 커서가 문장의 맨 앞이 아닐 경우
                case 'B':
                    if (!left.isEmpty()) {
                        // left의 맨 위 문자를 하나 꺼냄(=삭제)
                        left.pop();
                    }
                    break;
                // 명령이 'P'일 경우
                case 'P':
                    // 다음 토큰에서 문자 하나 읽고 left에 삽입
                    char addChar = line.charAt(2);
                    left.push(addChar);
                    break;
            }
        }

        // 결과 문자열 만들기
        StringBuilder out = new StringBuilder(left.size() + right.size());
        /*
         * left 빌 때까지 맨 뒤 문자부터 하나씩 꺼내서 right에 삽입
         * 예: left = [a, b, c, d, y], right = [x]
         * -> left = [], right = [x, y, d, c, b, a]
         */
        while (!left.isEmpty()) {
            right.push(left.pop());
        }
        /*
         * right 빌 때까지 맨 뒤 문자부터 하나씩 꺼내서 out에 삽입
         * 예: out = [], right = [x, y, d, c, b, a]
         * -> out = [a, b, c, d, y, x]
         */
        while (!right.isEmpty()) {
            out.append(right.pop());
        }

        // 결과 문자열로 한 번에 출력

    }
}
