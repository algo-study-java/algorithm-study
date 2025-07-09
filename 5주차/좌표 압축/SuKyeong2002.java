import java.util.*;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 좌표 개수 입력
        int[] original = new int[N]; // 원본 좌표 배열
        int[] sorted = new int[N]; // 정렬용 배열

        // 입력 받기
        for (int i = 0; i < N; i++) {
            original[i] = sc.nextInt();
            sorted[i] = original[i]; // 정렬용 배열에도 복사
        }

        // 정렬 (중복 포함)
        Arrays.sort(sorted);

        // 중복 제거 + 순위 매기기 위해 Map 사용
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < N; i++) {
            if (!rankMap.containsKey(sorted[i])) {
                rankMap.put(sorted[i], rank);
                rank++; // 다음 숫자에는 순위 하나 증가
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(rankMap.get(original[i])).append(" ");
        }
        System.out.println(sb);
    }
}
