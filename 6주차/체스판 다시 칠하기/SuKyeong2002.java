import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행 수
        int M = sc.nextInt(); // 열 수
        sc.nextLine(); // 개행 문자 제거

        // 입력 보드
        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'W') {
                    board[i][j] = 1; // 흰색
                } else if (c == 'B') {
                    board[i][j] = 0; // 검정색
                }
            }
        }

        int minRepaint = Integer.MAX_VALUE;

        // 8x8 체스판을 잘라서 검사
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                int repaint1 = 0; // 시작이 W
                int repaint2 = 0; // 시작이 B

                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        int current = board[i + x][j + y];
                        int expected = (x + y) % 2; // 번갈아야 함

                        if (current != expected)
                            repaint1++; // W시작: W=1
                        if (current != (1 - expected))
                            repaint2++; // B시작: B=0
                    }
                }

                minRepaint = Math.min(minRepaint, Math.min(repaint1, repaint2));
            }
        }

        System.out.println(minRepaint);
    }
}
