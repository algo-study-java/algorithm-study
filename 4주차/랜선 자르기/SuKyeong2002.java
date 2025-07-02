import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(); // 이미 가지고 있는 랜선의 갯수 K 입력받기
        int N = sc.nextInt(); // 필요한 랜선의 갯수 N 입력받기

        // K을 크기로 하는 정수형 배열 lines 선언
        int[] lines = new int[K];

        // 실수형 max 선언
        long max = 0;

        for (int i = 0; i < K; i++) {
            lines[i] = sc.nextInt();
            if (lines[i] > max) {
                max = lines[i];
            }
        }

        // 최소 랜선 길이 left 선언
        long left = 1;
        // 최대 랜선 길이 max 선언
        long right = max;
        // 만들 수 있는 쵀대 랜선 길이 answer 선언
        long answer = 0;

        // 이분탐색 사용: 완전탐색 사용 시 시간 초과할 수도 있음
        while (left <= right) {
            long mid = (left + right) / 2;
            long count = 0;

            // mid 길이로 잘랐을 때 랜선 개수 계산
            for (int line : lines) {
                count += line / mid;
            }

            // N개 이상 만드는 경우
            if (count >= N) {
                answer = mid; // answer에 저장
                left = mid + 1; // 더 길게 짜르기
            } else { // N개 이상 못 만드는 경우
                right = mid - 1; // 더 짧게 자르기
            }
        }

        // 최대 랜선의 길이 출력
        System.out.println(answer);

    }
}
