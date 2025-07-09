import java.util.*;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 좌표 개수 입력
        int[] originalArr = new int[N]; // 원본 좌표 배열
        int[] sortedArr = new int[N]; // 정렬용 배열

        // 입력 받기
        for (int i = 0; i < N; i++) {
            originalArr[i] = sc.nextInt();
            sortedArr[i] = originalArr[i]; // 정렬용 배열에도 복사
        }

        // 오름차순으로 정렬 (중복 포함)
        Arrays.sort(sortedArr);

        // <값, 순위> 저장하기 위해 Map 사용
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < N; i++) {
            // rankMap에 sortedArr[i] 가 포함되지 않을 경우
            if (!rankMap.containsKey(sortedArr[i])) {
                // sortedArr[i] 한테 rank 부여
                rankMap.put(sortedArr[i], rank);
                // 다음 숫자에게는 하나 높은 순위를 주기 위해 1 증가
                rank++;
            }
        }

        // 문자열을 효율적으로 이어 붙이기 위해 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 예: i = 0, originalArr[0] = 2, rankMap.get(2) = 2, 끝에 공백 추가
            sb.append(rankMap.get(originalArr[i])).append(" ");
        }

        // 결과 출력
        System.out.println(sb);
    }
}

/*
 * Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는 서로 다른 좌표 Xj의 개수와 같아야 한다.
 * 예: 2 4 -10 4 -9 -> 2보다 작은 값은 -10, -9이므로 X'2 = 2
 */
