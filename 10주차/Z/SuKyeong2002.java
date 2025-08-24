import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SuKyeong2002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /*
         * 한 줄에 입력 받은 승, 행, 열을 공백 기준으로 잘라서 저장
         * 승 (1 ≤ power ≤ 15)
         * 행, 열 (0 ≤ row, column < 2의 power승)
         * -> ***(0, 0)부터 시작이니까 방문 순서도 0부터 시작
         */
        int power = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        // 방문 순서
        int visitNum = 0;

        // 반으로 쪼갤 기준
        int half = 0;

        /*
         * 정사각형을 어떻게 만드는 게 좋을까? (좌표 or 배열)
         * -> 2의 15승 X 2의 15승 = 32768 × 32768 = 약 10억
         * -> 자바 int형 1개: 4byte -> 약 10억 x 4byte = 약 40억 -> 4GB
         * -> 문제의 메모리 제한: 512 MB -> 메모리 초과!
         * -> 결론: 좌표 사용
         * 
         * X, Y 좌표를 어떻게 표현할까?
         * -> 2의 1승씩 줄어듦 -> 반씩 줄어듦
         * -> (0, 0) 부터 시작하니까 -1 추가
         * 
         * 예.
         * -> (0, 0) -> (0, 0)
         * -> (0, 1) -> (0, 2의 (power-1)승 - 1)
         * -> (1, 0) -> (2의 (power-1)승 - 1, 0)
         * -> (1, 1) -> (2의 (power-1)승 - 1, 2의 (power-1)승 - 1)
         */

        /*
         * power = 1 인 경우
         * (0,0) -> (row, column) 방문 순서 누적
         * -> 방문 순서: 왼쪽 위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래
         */
        if (power == 1) {
            /*
             * (row, column)
             * (0, 0) -> visitNum = 0
             * (0, 1) -> visitNum = 1
             * (1, 0) -> visitNum = 2
             * (1, 1) -> visitNum = 3
             */
            visitNum = row * 2 + column;
        }

        /*
         * power >= 2 인 경우
         * -> 1. 2의 (power - 1)승 x 2의 (power - 1)승 사각형으로 쪼개기
         * -> 2. *** 생성된 4개의 블록 범위에 따른 row, column 쪼개기
         * -> 3. 방문 순서: 왼쪽위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래
         * -> 4. 방문 순서 누적
         * -> 1 ~ 4 를 power = 1 일 때까지 반복
         */
        else {
            while (power > 1) {
                // 1. 1승 감소
                half = (int) Math.pow(2, power - 1);

                // 2. half와 row, column을 비교하며 블록 탐색
                if ((row < half) && (column < half)) {
                    /*
                     * 왼쪽 위 블록
                     * 1번째 블록이므로 방문 순서 0부터 시작
                     */
                    visitNum += 0;

                } else if ((row < half) && (column >= half)) {
                    /*
                     * 오른쪽 위 블록
                     * -> 2번째 블록이므로 이전 블록의 크기 (half * half) 더하기
                     * -> 블록 내부 좌표로 변환: column을 0~(half-1)로 다시 세야 함
                     * -> column을 half만큼 감소
                     */
                    visitNum += half * half;
                    column -= half;

                } else if ((row >= half) && (column < half)) {
                    /*
                     * 왼쪽 아래 블록
                     * -> 3번째 블록이므로 이전 블록의 크기 (2 * half * half) 더하기
                     * -> 블록의 크기: half * half * 2
                     * -> 블록 내부 좌표로 변환: row을 0~(half-1)로 다시 세야 함
                     * -> row에서 half만큼 감소
                     */
                    visitNum += 2 * half * half;
                    row -= half;

                } else {
                    /*
                     * 오른쪽 아래 블록
                     * -> 4번째 블록이므로 이전 블록의 크기 (3 * half * half) 더하기
                     * -> 블록 내부 좌표로 변환: row, column을 0~(half-1)로 다시 세야 함
                     * -> row, column에서 half만큼 감소
                     */
                    visitNum += 3 * half * half;
                    row -= half;
                    column -= half;
                }

                // 1승 감소
                power--;
            }

            // power == 1 일 경우 방문 순서 추가
            visitNum += row * 2 + column;
        }

        // 결과 (방문 순서) 출력
        System.out.print(visitNum);
    }
}
