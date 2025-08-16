import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuKyeong2002 {

    /*
     * 부분합 반복 탐색
     * 중첩for문: 시간복잡도: O(n의 2승) 이상 -> (10의 5승)의 2승 이상 -> 100억 번 연산
     * 1초 안에 초리 가능한 연산 수: 10의 8승 정도
     * 결론: 0.5초 안에 풀어야 하는데 시간초과 남
     * -> 해결방안: 한 번의 반복으로 모든 요소 처리하고 O(n)인 투포인터 사용
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
         * 공백 기준으로 잘라서 저장하기
         * 수열 (10 ≤ sequenceLength < 100,000)
         * 부분합 중 최소 합 (0 < partialMinSum ≤ 100,000,000)
         */
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sequenceLength = Integer.parseInt(st.nextToken());
        int partialMinSum = Integer.parseInt(st.nextToken());

        // 수열 배열 생성
        int[] sequenceArr = new int[sequenceLength];

        // 공백 기준으로 잘라서 배열의 원소 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sequenceLength; i++) {
            sequenceArr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0, start = 0, end = 0;
        int minLength = Integer.MAX_VALUE;

        while (true) {
            /*
             * 연속된 수들의 부분합 중에 그 합이 S 이상인 경우
             * 길이 기록 -> start를 오른쪽으로 한 칸 줄여서 더 짧은 구간 탐색
             * 예:
             * 24 >= 15, minLength = Math.min(Integer.MAX_VALUE, 5 - 0) = 5,
             * sum -= sequenceArr[0] = 24 - 5 = 19, start = 1
             * 19 >= 15, minLength = Math.min(5, 5 - 1) = 4,
             * sum -= sequenceArr[1] = 19 - 1 = 18, start = 2
             * 18 >= 15, minLength = Math.min(4, 5 - 2) = 3,
             * sum -= sequenceArr[2] = 18 - 3 = 15, start = 3
             * 15 >= 15, minLength = Math.min(3, 5 - 3) = 2,
             * sum -= sequenceArr[3] = 15 - 5 = 10, start = 4
             * 17 >= 15, minLength = Math.min(2, 6 - 4) = 2,
             * sum -= sequenceArr[4] = 17 - 10 = 7, start = 5
             * 20 >= 15, minLength = Math.min(2, 8 - 5) = 2,
             * sum -= sequenceArr[5] = 20 - 7 = 13, start = 6
             * 15 >= 15, minLength = Math.min(2, 9 - 6) = 2,
             * sum -= sequenceArr[6] = 15 - 4 = 11, start = 7
             */
            if (sum >= partialMinSum) {
                minLength = Math.min(minLength, end - start);
                sum -= sequenceArr[start++];
            } /*
               * 마지막 번째일 경우
               * 반복문 종료
               * 예:
               * 10 == 10, 종료
               */
            else if (end == sequenceLength) {
                break;
            } /*
               * 연속된 수들의 부분합 중에 그 합이 S 미만인 경우
               * end를 오른쪽으로 한 칸 확장
               * 예:
               * sum += sequenceArr[0] = 5, end = 1
               * 5 < 15, sum += sequenceArr[1] = 5 + 1 = 6, end = 2
               * 6 < 15, sum += sequenceArr[2] = 6 + 3 = 9, end = 3
               * 9 < 15, sum += sequenceArr[3] = 9 + 5 = 14, end = 4
               * 14 < 15, sum += sequenceArr[4] = 14 + 10 = 24, end = 5
               * 10 < 15, sum += sequenceArr[5] = 10 + 7 = 17, end = 6
               * 7 < 15, sum += sequenceArr[6] = 7 + 4 = 11, end = 7
               * 11 < 15, sum += sequenceArr[7] = 11 + 9 = 20, end = 8
               * 13 < 15, sum += sequenceArr[8] = 13 + 2 = 15, end = 9
               * 11 < 15, sum += sequenceArr[9] = 11 + 8 = 19, end = 10
               */
            else {
                sum += sequenceArr[end++];
            }
        }

        /*
         * 결과:
         * minLength이 초기값과 같은 경우 0 출력
         * 그렇지 않을 경우 minLength 출력
         */

    }
}
