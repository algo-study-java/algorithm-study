import java.util.ArrayList;
import java.util.Scanner;

public class SuKyeong2002 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 전체 인원수 입력 받기
        int K = sc.nextInt(); // 제거될 사람의 순서 입력 받기

        ArrayList<Integer> list = new ArrayList<>(); // 초기 list 리스트 생성
        for (int i = 1; i <= N; i++) {
            list.add(i); // 순서대로 원소값 저장
        }

        int index = 0; // 인덱스 선언
        ArrayList<Integer> result = new ArrayList<>(); // 결과를 저장할 result 리스트 생성

        while (!list.isEmpty()) { // list가 빌 때까지 반복
            index = (index + K - 1) % list.size(); // 원형 순환
            result.add(list.remove(index)); // list에서 K번째 인덱스 제거해서 result 리스트에 추가
        }

        System.out.print("<");
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i)); // result 리스트의 원소값을 순서대로 출력
            if (i != result.size() - 1) {
                System.out.print(", ");
            }

        }
        System.out.print(">");
    }
}
