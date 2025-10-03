package ch08;

import java.util.HashMap;

public class Solution20 {

    public int solution(String[] want, int[] number, String[] discount) {
        // want, number 배열의 값을 해시맵 저장
        HashMap<String, Integer> wantMap = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int answer = 0; // 총 일수

        // i 일에 회원 가입시 할인 받을 수 있는 품목 체크
        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> discount10d = new HashMap<>();

            for (int j = i; j < i + 10; j++) {
                if (wantMap.containsKey(discount[j])) {
                    discount10d.put(discount[j], discount10d.getOrDefault(discount[j], 0) + 1);
                }
            }

            if (discount10d.equals(wantMap)) {
                answer++;
            }
        }
        return answer;

    }
}

/*
시간 복잡도: O(N * 10), N = discount 배열의 길이
공간 복잡도: O(M), M = want 배열의 길이 (HashMap 크기)

주요 포인트:
1. HashMap을 활용한 제품-수량 매핑
2. 슬라이딩 윈도우 방식으로 연속된 10일 확인
3. getOrDefault를 통한 안전한 값 처리
4. equals 메소드를 통한 정확한 HashMap 비교
*/
