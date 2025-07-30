import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사람 수 입력
        int n = sc.nextInt();

        // 심사관 수 입력
        sc.nextLine();

        // 심사관의 심사 시간 입력
        String[] timeStrs = sc.nextLine().split(" ");

        // 각 심사관의 심사 시간 배열
        int[] times = new int[timeStrs.length];

        for (int i = 0; i < timeStrs.length; i++) {
            times[i] = Integer.parseInt(timeStrs[i]);
        }

        // 최소 시간을 찾기 위한 이진 탐색
        long result = findMinTime(n, times);
        System.out.println(result);
    }

    // 이진 탐색을 통해 최소 시간을 찾는 메서드
    public static long findMinTime(int n, int[] times) {
        long left = 1;
        long right = (long) times[0] * n;
        for (int time : times) {
            right = Math.max(right, (long) time * n);
        }

        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long total = 0;

            for (int time : times) {
                total += mid / time;
            }

            if (total >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
