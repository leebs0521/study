package ch05;

import java.util.Arrays;

public class Solution01 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{1, -5, 2, 4, 3})));
        System.out.println(Arrays.toString(solution(new int[]{2, 1, 1, 3, 2, 5, 4})));
        System.out.println(Arrays.toString(solution(new int[]{6, 1, 7})));
    }

    // 입력된 정수 배열을 오름차순 정렬하여 반환하는 메서드
    private static int[] solution(int[] arr) {
        int[] clone = arr.clone(); // 원본 배열을 복사하여 정렬로 인한 원본 변경 방지
        Arrays.sort(clone);        // 오름차순 정렬
        return clone;              // 정렬된 배열 반환
    }
}

/*
정리

목적:
- 주어진 int[] 배열을 오름차순 정렬하여 새로운 배열로 반환한다.

주요 포인트:

1. arr.clone()
   - 원본 배열을 직접 정렬하지 않고, 복사본을 정렬함으로써 원본 데이터 보호
   - 얕은 복사이며, 기본형 배열에서는 안전하게 사용 가능

2. Arrays.sort(clone)
   - 기본형 배열에 대해 Dual-Pivot Quicksort 알고리즘을 사용
   - 평균 시간 복잡도는 O(n log n)
   - 오름차순으로 정렬됨

3. Arrays.toString(...)
   - 배열 내용을 보기 좋게 문자열로 출력해주는 유틸리티 메서드

장점:
- 코드가 간결하고 직관적임
- 입력 배열을 보호하여 side-effect가 없음

확장 가능성:
- Arrays.sort(clone, Collections.reverseOrder())는 기본형 배열에는 사용할 수 없음
- 내림차순이 필요하다면 boxed() 후 Comparator를 사용하거나, 배열을 뒤집는 후처리가 필요함
*/
