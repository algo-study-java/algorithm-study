import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 한 줄의 벨트 칸 수
        int K = sc.nextInt(); // 내구도 0인 칸 수

        // A1, A2, ..., A2N (1 ≤ K ≤ 2N)
        // 두 줄의 벨트 칸 수를 저장할 배열 (0 ~ N-1)
        int[] belt = new int[2 * N];

        // 벹르 배열의 값 (내구도) 입력 받기
        for (int i = 0; i < belt.length; i++) {
            belt[i] = sc.nextInt();
        }

        boolean[] robot = new boolean[2 * N]; // 로봇 배열
        int zeroCount = 0; // 내구도 0인 칸 수 카운트
        int step = 0; // 단계 카운트

        /*
         * 종료 조건 확인
         * 조건 1. zeroCount >= K
         * 아닐 경우 반복
         */
        while (zeroCount < K) {
            // 단계 1씩 증가
            step++;

            /*
             * 벨트 한 칸 회전 (다음 번호의 칸으로 이동)
             * 1 ~ N-1 까지 한 칸씩 오른쪽값으로 저장
             *** 0은 마지막 값으로 저장
             * 예: [1, 2, 1, 2, 1, 2] -> [1, 1, 2, 1, 2, 1] -> [2, 1, 2, 1, 2, 1]
             */
            int lastBelt = belt[2 * N - 1];
            boolean lastrobot = robot[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; i--) {
                belt[i] = belt[i - 1];
                robot[i] = robot[i - 1];
            }
            belt[0] = lastBelt;
            robot[0] = lastrobot;

            // 회전 후 내리는 위치 비우기
            robot[N - 1] = false;

            /*
             *** 한 번 회전할 때마다 로봇 한 칸씩만 이동 가능(역순으로 검사)-> 내구도 - 1
             * 조건 1. 이동하려는 칸에 로봇 존재 X
             * 조건 2. 이동하려는 칸의 내구도 > 0
             *** 조건 3. 이동하기 전 칸에 로봇 존재 O
             */
            for (int i = N - 2; i >= 0; i--) {
                if (robot[i] && !robot[i + 1] && (belt[i + 1] > 0)) {
                    robot[i] = false;
                    robot[i + 1] = true;
                    belt[i + 1]--;
                    if (belt[i + 1] == 0) {
                        zeroCount++;
                    }
                }
            }

            // 이동 후 내리는 위치 비우기
            robot[N - 1] = false;

            /*
             * 로봇 올리기 -> 내구도 - 1
             * 조건 1. 이동하려는 칸에 로봇 존재 X
             * 조건 2. 이동하려는 칸의 내구도 > 0
             * 로봇 이동 조건3 안하는 이유: 로봇이 이동하는 게 아니라 생성되기 때문
             */
            if (!robot[0] && belt[0] > 0) {
                robot[0] = true;
                belt[0]--;
                if (belt[0] == 0) {
                    zeroCount++;
                }
            }
        }

        // 종료 될 때의 단계 출력
        System.out.println(step);
    }
}
