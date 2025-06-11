import java.util.Scanner;

public class SuKyeong2002 {

    // 재귀 함수 fib() 설정 : 짧고 직관적이지만 중복호출 많아서 느림
    // static 붙인 이유: main 함수에서 fib() 호출헤야 하기 때문
    /*
     * static int fib(int n) {
     * if (n == 1)
     * return 1;
     * else if (n == 2)
     * return 2;
     * return fib(n - 2) + fib(n - 1);
     * }
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n값 입력받기
        // System.out.println(fib(n) % 10007); // 재귀함수 fib() 호출

        // dp: dynamic programming : 결과 저장했다가 다시 쓰는 방법으로 빠름
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        if (n == 1) {
            System.out.println(dp[1]);
            return;
        } else if (n == 2) {
            System.out.println(dp[2]);
            return;
        }

        // i가 3부터 시작하는 이유: dp[n-2] = dp[1] 로 하기 위함
        for (int i = 3; i < n + 1; i++) {
            // 오버플로우 위험 때문에 매 단계마다 % 10007
            dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
        }
        System.out.println(dp[n]);
    }
}
