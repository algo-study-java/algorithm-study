import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 수열 크기
        int[] sequence = new int[N]; // 수열을 저장할 배열
        int[] answer = new int[N]; // 오큰수 저장할 배열

        // 수열 배열의 원소 입력 받기
        for (int i = 0; i < N; i++) {
            sequence[i] = sc.nextInt();
        }

        // 오큰수 구하기
        for (int i = 0; i < N; i++) {
            int min = -1; // 오큰수 기본값
            // 조건 1. Ai보다 큰 수 중
            for (int j = i + 1; j < N; j++) {
                // 조건 2. 가장 왼쪽에 있는 수
                if (sequence[j] > sequence[i]) {
                    min = sequence[j];
                    break;
                }
            }
            // 없을 시 기본값 저장
            answer[i] = min;
        }

        // 오큰수 출력 (구분: 공백)
        for (int i = 0; i < N; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
