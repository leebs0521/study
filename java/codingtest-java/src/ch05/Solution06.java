package ch05;

import java.util.*;

class Solution06 {
    public int[] solution(int N, int[] stages) {
        // 스테이지별 도전자 수 배열 (N+2 크기: 1~N 사용, N+1은 모든 스테이지 클리어한 사람)
        int[] challenger = new int[N + 2];

        for (int stage : stages) {
            challenger[stage]++;
        }

        Map<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for (int i = 1; i <= N; i++) {
            if (challenger[i] == 0) {
                fails.put(i, 0.0); // 도달한 사람이 없음 → 실패율 0
            } else {
                fails.put(i, (double)challenger[i] / total); // 실패율 계산
                total -= challenger[i]; // 다음 스테이지로 넘어갈 사람 수 감소
            }
        }

        // 실패율 기준 내림차순, 같으면 스테이지 번호 오름차순
        return fails.entrySet().stream()
                .sorted((e1, e2) -> {
                    int cmp = Double.compare(e2.getValue(), e1.getValue());
                    return cmp != 0 ? cmp : Integer.compare(e1.getKey(), e2.getKey());
                })
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
