import java.util.*;

class Solution {
  public long solution(int n, int[] times) {
    long answer = 0;

    Arrays.sort(times);
    int len = times.length;
    long lt = 0;
    long rt = (long) n * times[len - 1];

    while (lt <= rt) {
      long mid = (lt + rt) / 2;
      long sum = 0;

      for (int i = 0; i < len; i++) {
        sum += mid / times[i];
      }

      if (sum < n) {
        lt = mid + 1;
      } else {
        answer = mid;
        rt = mid - 1;
      }
    }

    return answer;
  }
}
