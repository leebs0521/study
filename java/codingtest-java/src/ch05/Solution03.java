package ch05;

import java.util.HashSet;
import java.util.Set;

public class Solution03 {

    /**
     * [코딩테스트 대비 문제 풀이]
     *
     * ▶ 문제 설명:
     *   - 주어진 정수 배열 numbers에서
     *   - 서로 다른 인덱스에 있는 두 수를 더해 만들 수 있는 모든 경우의 수를 구하고,
     *   - 중복은 제거한 후 오름차순으로 정렬된 int 배열로 반환하는 문제.
     *
     * ▶ 예시 입력: [2, 1, 3, 4, 1]
     * ▶ 예시 출력: [2, 3, 4, 5, 6, 7]
     *
     * ▶ 주요 포인트:
     *   - 중복 제거: HashSet 사용
     *   - 오름차순 정렬: Stream.sorted()
     *   - 모든 2개의 조합을 탐색: 이중 for문
     *
     * ▶ 시간 복잡도:
     *   - 조합 탐색: O(n^2)
     *   - 정렬: O(k log k) (k는 중복 제거된 합의 개수, 최악 O(n^2))
     */

    public static int[] solution(int[] numbers) {
        // Set 사용 이유:
        // - 중복된 합이 있을 수 있으므로 중복 제거가 필요함 (예: 2+1, 1+2는 동일)
        Set<Integer> set = new HashSet<>();

        // 두 수의 합을 구하기 위한 이중 반복문
        // 모든 i < j 쌍에 대해 합을 구한다
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];
                set.add(sum); // HashSet이므로 중복은 자동 제거됨
            }
        }

        // HashSet → Stream 변환 → 정렬 → int 배열로 변환
        return set.stream()
                .sorted()                          // 오름차순 정렬
                .mapToInt(Integer::intValue)       // Integer → int
                .toArray();                        // 배열로 반환
    }
}
