import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class SuKyeong2002 {

    static int size;
    static int max = 2; // 블록의 최솟값으로 초기화

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드의 크기 입력 (1 ≤ size ≤ 20)
        size = Integer.parseInt(br.readLine());

        // 이동 횟수 = 깊이 저장
        int depth = 0;

        // 보드의 크기를 가지는 2차원 배열 선언
        int[][] board = new int[size][size];

        /*
         * 공백 기준으로 나눠서 배열에 저장
         * 빈 칸: 0
         * 이외: 블록 (2 <= 블록 <= 1024인 2의 제곱꼴)
         */
        for (int row = 0; row < size; row++) {
            // 새로운 st를 매 행마다 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < size; col++) {
                board[row][col] = Integer.parseInt(st.nextToken());

                // 최대값인 원소를 max에 저장
                max = Math.max(max, board[row][col]);
            }
        }

        /*
         * [로직]
         * 
         * 1. 최대 5번 전체 블록을 상하좌우 네 방향 중 하나로 이동
         * 1-1. 같은 값을 갖는 두 블록이 충돌하면 두 블록은 하나로 합쳐짐
         * 1-1. 같은 값을 갖는 세 블록이 충돌하면 이동하려고 하는 쪽의 칸이 먼저 합쳐짐
         * *** <그림2> -> <그림3> 의 경우 합쳐질 때 빈칸(0)은 무시됨 -> 0 제거 로직 필요
         * 
         * [주의사항]
         * 
         * 한 번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐지기 X
         * 이동할 때마다 블록 추가 X
         * 
         * [궁금증]
         * 
         * 1. 어디서부터 어디로 이동해야 하는가?
         * -> 상 하 좌 우 순으로 이동
         * -> move 함수 생성 (블록 이동 및 합치기 로직)
         * 
         * 2. 상하좌우 이동했을때 나올 수 있는 경우의 수를 모두 탐색해야 하는가?
         * -> 그런듯... 이게 바로 완전탐색?
         * -> 깊이 탐색으로 5번 이동까지 계산
         * 
         * 3. 상하좌우의 양 끝의 범위로 제한을 걸어야 하나?
         * -> 줄 단위 이동이므로 순서만 잘 잡으면 제한 걸 필요 X
         */

        // 깊이 탐색 시작
        dfs(board, depth);

        // 최댓값 출력
        System.out.println(max);
    }

    // dfs 함수 (현재 보드 상태에서 최대 5번 이동하며 최댓값 갱신)
    private static void dfs(int[][] board, int depth) {
        // 5번 이동했을 경우 종료
        if (depth == 5) {
            return;
        }

        // 그렇지 않은 경우 상하좌우 방향 이동하면서 배열 갱신
        for (int dir = 0; dir < 4; dir++) {

            // board를 dir 방향으로 이동한 결과를 새로운 배열에 저장
            int[][] newBoard = move(board, dir);

            // 새로운 배열에서 깊이를 1 증가하여 다시 상하좌우 이동
            dfs(newBoard, depth + 1);
        }
    }

    // move 함수 (보드 상태를 dir 방향으로 이동, 0 제거 후 한 줄로 압축하기)
    private static int[][] move(int[][] board, int dir) {

        // 기존 보드를 복사하여 새로운 보드 생성
        int[][] newBoard = copyBoard(board);

        /*
         * dir 에 따른 줄 단위 이동
         * ( 0: 상, 1: 하, 2: 좌, 3: 우 )
         */
        if (dir == 0) {
            /*
             * ArrayList 사용한 이유
             * 1. 한 줄로 이동 -> 배열 보다 편함
             * 2. 뒤에 값 추가 -> LinkedList 보다 빠름
             */
            for (int col = 0; col < size; col++) {
                // 한 줄로 압축된 상태를 저장할 list
                ArrayList<Integer> line = new ArrayList<>();
                // 위에서 아래 방향으로 탐색
                for (int row = 0; row < size; row++) {
                    // 블록(0)이 아닐 경우
                    if (newBoard[row][col] != 0) {
                        // line의 뒤에 값 추가
                        line.add(newBoard[row][col]);
                    }
                }
                // 합치기
                line = merge(line);
                // 배열에 다시 채우기
                for (int row = 0; row < size; row++) {
                    /*
                     * line에 값이 남아 있을 경우 그 값 꺼내 채움
                     * 그렇지 않을 경우 0으로 채움
                     */
                    newBoard[row][col] = (row < line.size()) ? line.get(row) : 0;
                }
            }
        } else if (dir == 1) {
            for (int col = 0; col < size; col++) {
                ArrayList<Integer> line = new ArrayList<>();
                // 아래에서 위 방향으로 탐색
                for (int row = size - 1; row >= 0; row--) {
                    if (newBoard[row][col] != 0)
                        line.add(newBoard[row][col]);
                }
                // 합치기
                line = merge(line);
                // 다시 보드에 채우기 (아래부터 채움)
                for (int row = size - 1, idx = 0; row >= 0; row--) {
                    newBoard[row][col] = (idx < line.size()) ? line.get(idx++) : 0;
                }
            }
        } else if (dir == 2) {
            for (int row = 0; row < size; row++) {
                ArrayList<Integer> line = new ArrayList<>();
                // 왼쪽에서 오른쪽 방향으로 탐색
                for (int col = 0; col < size; col++) {
                    if (newBoard[row][col] != 0)
                        line.add(newBoard[row][col]);
                }
                // 합치기
                line = merge(line);
                // 다시 보드에 채우기 (왼쪽부터 채움)
                for (int col = 0; col < size; col++) {
                    newBoard[row][col] = (col < line.size()) ? line.get(col) : 0;
                }
            }
        } else if (dir == 3) {
            for (int row = 0; row < size; row++) {
                ArrayList<Integer> line = new ArrayList<>();
                // 오른쪽에서 왼쪽 방향으로 탐색
                for (int col = size - 1; col >= 0; col--) {
                    if (newBoard[row][col] != 0)
                        line.add(newBoard[row][col]);
                }
                // 합치기
                line = merge(line);
                // 다시 보드에 채우기 (오른쪽부터 채움)
                for (int col = size - 1, idx = 0; col >= 0; col--) {
                    newBoard[row][col] = (idx < line.size()) ? line.get(idx++) : 0;
                }
            }
        }

        return newBoard;
    }

    // merge 함수 (압축된 한 줄을 받아 같은 값은 합쳐서 반환)
    private static ArrayList<Integer> merge(ArrayList<Integer> line) {
        // 합쳐진 결과를 담을 list
        ArrayList<Integer> newLine = new ArrayList<>();
        // line의 앞의 값부터 하나씩 확인
        int i = 0;
        // line의 크기만큼 반복
        while (i < line.size()) {
            // 현재 조회한 list 값을 current에 저장
            int current = line.get(i);
            /*
             * line의 다음 값이 범위를 초과하지 않고
             * current와 line의 다음 값이 같을 경우
             */
            if (i + 1 < line.size() && current == line.get(i + 1)) {
                /*
                 * current의 2배값을 newLine의 끝에 추가
                 * 2배를 한 이유: 같은 값을 갖는 두 블록이 충돌하면 두 블록은 더해져서 하나로 합쳐지기 때문
                 */
                newLine.add(current * 2);
                // 기존의 max 값과 current의 2배값 중 더 큰 값으로 갱신
                max = Math.max(max, current * 2);
                // 2개의 블록을 합쳤으므로 다다음 차례로 이동
                i += 2;
            } else {
                // 그렇지 않을 경우 current값을 newLine의 끝에 추가
                newLine.add(current);
                // 다음 차례로 이동
                i++;
            }
        }
        // newLine 반환
        return newLine;
    }

    // 보드를 복사하는 함수 (원본 보드에 영향을 주지 않기 위해 깊은 복사)
    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[size][size];
        for (int i = 0; i < size; i++) {
            // 배열의 한 행씩 복사
            System.arraycopy(board[i], 0, newBoard[i], 0, size);
        }
        return newBoard;
    }
}
