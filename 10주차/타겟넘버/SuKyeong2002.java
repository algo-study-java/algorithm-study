class Solution {
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    /*
     * depth: 현재 탐색한 숫자의 인덱스
     * sum: 현재 타겟 넘버들의 합
     */
    private int dfs(int[] numbers, int target, int depth, int sum) {

        // *** 먼저 검사: 타겟 넘버 배열의 마지막 값일 경우
        if (depth == numbers.length) {
            if (sum == target)
                return 1;
            else
                return 0;
        }

        // 타겟 넘버 배열값을 + 하는 경우
        int plus = dfs(numbers, target, depth + 1, sum + numbers[depth]);

        // 타겟 넘버 배열값을 - 하는 경우
        int minus = dfs(numbers, target, depth + 1, sum - numbers[depth]);

        return 1;
    }
}