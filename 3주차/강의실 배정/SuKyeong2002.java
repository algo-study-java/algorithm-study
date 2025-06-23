import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 수업 갯수 N값 입력 받기
        int N = sc.nextInt();

        // N개의 수업의 [시작시간, 종료시간]을 한 쌍으로 저장하는 2차원 배열 classes 선언
        int[][] classes = new int[N][2];
        for (int i = 0; i < N; i++) {
            // 시작 시간 입력 받기
            classes[i][0] = sc.nextInt();
            // 종료 시간 입력 받기
            classes[i][1] = sc.nextInt();
        }

        // 시작시간을 기준으로 오름차순 정렬
        Arrays.sort(classes, (a, b) -> Integer.compare(a[0], b[0]));
        // System.out.println(classes.toString()); // 메모리 주소 해시값 출력
        // System.out.println(Arrays.deepToString(classes)); // 정렬된 배열의 값 출력

        // 우선순위 큐 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 첫 강의 종료시간을 큐에 삽입
        pq.offer(classes[0][1]);

        // 나머지 강의시간을 보면서 더 이른 종료시간이 있는지 비교
        for (int i = 1; i < N; i++) {

            // 이전 수업의 종료시간이 현재 강의 시작시간 보다 작거나 같은 경우
            if (pq.peek() <= classes[i][0]) {

                // 기존의 종료시간을 큐에서 제거
                // 더 일찍 끝나는 강의의 종료시간을 큐에 삽입
                pq.poll();
            }
            pq.offer(classes[i][1]);
        }

        // *** 큐의 크기 = 필요한 강의실의 갯수 출력 ***
        System.out.println(pq.size());
    }
}

/*
 * 
 * 강의실 배정
 * 
 * 문제 요약
 * 
 * 입력값: N : 수업 갯수
 * 출력값: System.out.println(que.size()) : 강의실의 갯수
 * 
 * 1. 강의시간: Si 이상 Ti 이하
 * 
 * 2. 최소의 강의실로 모든 수업 가능
 * 
 * -> N개의 수업의 시작시간과 종료시간 저장
 * -> 2차원 배열 사용 {{시작시간}, {종료시간}}
 * 
 * -> 가장 빨리 끝나는 강의실이 어디인가?
 * -> 시작시간을 시간순으로 오름차순 정렬
 * 
 * -> 강의실 중 가장 빨리 끝나는 시간(최솟값)을 빠르게 꺼내고 싶다!
 * -> 첫 강의 끝나는 시간을 우선순위 큐에 넣기 (add() or offer())
 * -> 나머지 강의를 보면서 더 일찍 끝나는 강의가 있는지 비교
 * -> 있을 경우, 기존의 종료시간을 큐에서 제거하고 (poll() or remove()) 더 일찍 끝나는 강의의 종료시간을 큐에 삽입
 * -> 없을 경우, 다음 강의로 넘어감
 * 
 * -> * 큐의 크기: 필요한 강의실의 갯수
 * 
 * 3. 끝나는 시간과 시작하는 시간이 동일할 경우 같은 강의실 사용 가능
 */