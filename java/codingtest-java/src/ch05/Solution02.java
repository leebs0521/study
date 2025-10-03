package ch05;

import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Solution02 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{4, 2, 2, 1, 3, 4})));
        System.out.println(Arrays.toString(solutionOne(new int[]{2, 1, 1, 3, 2, 5, 4})));
    }

    // 방식 1: boxed + distinct + 배열로 변환 후 정렬 + 다시 int[]로 변환
    private static int[] solution(int[] arr) {
        Integer[] result = Arrays.stream(arr)
                .boxed()                        // int -> Integer (객체 스트림)
                .distinct()                    // 중복 제거
                .toArray(Integer[]::new);      // 배열로 변환

        Arrays.sort(result, Collections.reverseOrder()); // 내림차순 정렬

        return Arrays.stream(result)          // 다시 stream 생성
                .mapToInt(Integer::intValue)  // Integer -> int
                .toArray();                   // int[] 반환
    }

    // 방식 2: stream 정렬 내에서 Comparator 사용
    private static int[] solutionOne(int[] arr) {
        return Arrays.stream(arr)
                .boxed()                        // int -> Integer
                .distinct()                    // 중복 제거
                .sorted((a, b) -> (b - a))     // 내림차순 정렬 (Comparator 사용)
                .mapToInt(Integer::intValue)   // Integer -> int
                .toArray();                    // int[] 반환
    }

    // 방식 3: TreeSet을 이용한 중복 제거 + 자동 내림차순 정렬
    private static int[] solutionTwo(int[] arr) {
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder()); // 자동 내림차순 + 중복 제거
        for (int num : arr) {
            set.add(num);
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }

        return result;
    }
}

/*
각 방식 정리 요약

1. solution (배열로 정렬):
- boxed() → distinct() → 배열로 변환 → Arrays.sort() → 다시 int[] 변환
- 장점: 과정이 명시적이고 직관적임
- 단점: Stream → 배열 → 다시 Stream 재생성으로 인해 성능 손해 가능

2. solutionOne (Stream 내 Comparator 정렬):
- Stream 안에서 모든 작업 수행
- 장점: 코드 간결, stream 파이프라인 유지
- 단점: Comparator 사용을 위해 boxed()는 여전히 필요

3. solutionTwo (TreeSet 사용):
- TreeSet에 넣는 것만으로 중복 제거와 내림차순 정렬이 동시에 처리됨
- 장점: 중복 제거와 정렬을 자동으로 해결, 코드 간결
- 단점: Tree 구조이므로 삽입/삭제가 O(log n), null 허용 안 됨

추가 참고:
- 정렬 기준이 필요 없고 성능이 중요할 땐 IntStream으로 끝까지 처리
- 정렬 기준이 필요하면 boxed() 후 sorted() 사용
- 중복 제거 + 정렬이 동시에 필요하면 TreeSet이 적합
*/
