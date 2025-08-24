class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    void dfs(int[] numbers, int target, int sum, int depth){
        if(target == sum && depth == numbers.length){
            answer += 1;
            return;
        }
        if(depth<numbers.length){
            dfs(numbers, target, sum+numbers[depth], depth+1);
            dfs(numbers, target, sum-numbers[depth], depth+1);
        }
    }
}
