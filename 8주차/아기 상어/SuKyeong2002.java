import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 공간의 크기
        int[][] status = new int[N][N]; // 공간의 크기를 가지는 2차원 공간의 상태 배열

        // 공간의 상태 값 입력받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                status[i][j] = sc.nextInt();
            }
        }

        // 상, 좌, 우, 하 (탐색 순서 고정)
        int[] dx = { -1, 0, 0, 1 };
        int[] dy = { 0, -1, 1, 0 };

        int eatTime = 0; // 물고기를 잡아먹을 수 있는 시간 카운트

        /*
         * 실행 조건: 공간에 물고기가 있을 경우
         */
        while (true) {
            /*
             * 가장 가까이에 있는 물고기 위치 탐색
             * 조건 1. 물고기 수 == 1, 그 물고기 먹으러 감
             * 조건 2. 물고기 수 >= 2, 거리가 가장 가까운 물고기 먹으로 감
             * 조건 2-1. 거리가 가까운 물고기 수 >= 2, 가장 위, 가장 왼쪽에 있는 물고기 순으로 먹음
             */

            /*
             * 가장 가까이에 있는 물고기 위치로 이동 -> 1초 소요
             */

            /*
             * 자신의 크기와 같은 수의 물고기 섭취 시 크기 1 증가 -> 물고기 칸 빔
             * 예: 크기 2 아기 상어 -> 크기 2 물고기 섭취 -> 크기 3 아기 상어
             */
            break;
        }

        // 반복문 종료 시 시간 출력
        System.out.print(eatTime);
    }
}
