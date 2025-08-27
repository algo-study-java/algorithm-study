import java.util.*;

class Point {
    int sum;
    int seq;
    
    Point(int sum, int seq) {
        this.sum = sum;
        this.seq = seq;
    }
}

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int N = numbers.length;

        int[] sums = new int[N];
        int total = numbers[N - 1];
        sums[N - 1] = numbers[N - 1];
        
        for (int i = N - 2; i >= 0; i--) {
            total += numbers[i];
            sums[i] = total;
        }

        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(numbers[0], 1));
        Q.offer(new Point(-numbers[0], 1));

        while (!Q.isEmpty()) {
            Point p = Q.poll();

            // 마지막 숫자까지 모두 사용
            if (p.seq == N) {
                if (p.sum == target) {
                    answer++;
                }
                continue;
            }

            // 가지치기: 남은 합으로 못 맞추면 continue
            int diff = Math.abs(target - p.sum);
            if (diff > sums[p.seq]) {
                continue;
            }

            Q.offer(new Point(p.sum + numbers[p.seq], p.seq + 1));
            Q.offer(new Point(p.sum - numbers[p.seq], p.seq + 1));
        }

        return answer;
    }
}
