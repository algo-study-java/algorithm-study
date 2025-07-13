class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long max = 1000000000L * 100000L;
        long min = 0;
        long mid;
        
        while(min<=max){
            long count = 0;
            mid = (max+min)/2;
            
            for(int i=0; i<times.length; i++){
                count += mid/times[i];
            }
            
            if(count>=n){
                max = mid - 1;
            }else{
                min = mid + 1;
            }
        }
        
        return min;
    }
}
