package ch08;

import java.util.*;

public class Solution24 {

    /*
     1. 모든 주문(order)을 정렬 → 가능한 조합 생성 (길이 = course[] 중 하나)
     2. 조합을 comboMap에 기록 (빈도수 카운트)
     3. course[i] 길이별로 최다 주문 조합만 TreeSet에 삽입
     4. TreeSet을 배열로 변환하여 반환
     */
    public String[] solution(String[] orders, int[] course) {
        Map<String, Integer> comboMap = new HashMap<>();

        // 1. 모든 order에 대해 가능한 조합 생성
        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            for (int len : course) {
                combination(chars, new StringBuilder(), 0, len, comboMap);
            }
        }

        // 2. course별 최대 빈도 조합만 선별
        Set<String> ans = new TreeSet<>();

        for (int len : course) {
            int max = 2; // 2번 이상 주문된 조합만 유효
            for (String key : comboMap.keySet()) {
                if (key.length() == len) {
                    max = Math.max(max, comboMap.get(key));
                }
            }
            for (String key : comboMap.keySet()) {
                if (key.length() == len && comboMap.get(key) == max) {
                    ans.add(key);
                }
            }
        }

        return ans.toArray(String[]::new);
    }


    // 조합 생성 메서드
    private void combination(char[] arr, StringBuilder sb, int idx, int targetLen, Map<String, Integer> comboMap) {
        if (sb.length() == targetLen) {
            String key = sb.toString();
            comboMap.put(key, comboMap.getOrDefault(key, 0) + 1);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            sb.append(arr[i]);
            combination(arr, sb, i + 1, targetLen, comboMap);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
