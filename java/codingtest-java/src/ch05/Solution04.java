package ch05;

import java.util.*;

class Solution04 {

    /**
     * [코딩테스트 대비 문제 풀이]
     *
     * ▶ 문제 설명:
     *   - 3명의 수포자가 각각 일정한 규칙(패턴)으로 정답을 찍음
     *   - 실제 정답이 주어졌을 때, 각 수포자가 몇 문제를 맞췄는지 계산하고
     *   - 가장 많이 맞춘 수포자의 번호(여러 명이면 모두)를 오름차순 배열로 반환
     *
     * ▶ 입력:
     *   - int[] answers : 시험의 정답 배열
     * ▶ 출력:
     *   - int[] : 가장 많이 맞춘 수포자의 번호 목록 (1-indexed, 오름차순)
     *
     * ▶ 예시:
     *   answers = [1,3,2,4,2] → return [1,2,3]
     *
     * ▶ 주요 개념:
     *   - 반복되는 패턴은 배열로 정의
     *   - 패턴 길이를 벗어나는 경우 % 연산으로 순환
     *   - 최대 점수를 받은 사람(또는 사람들)만 골라내기
     *
     * ▶ 시간복잡도:
     *   - O(n), n = answers.length (정답 수만큼 순회)
     */

    public int[] solution(int[] answers) {

        // 각 수포자의 찍는 패턴을 정의
        int[][] pattern = {
                {1, 2, 3, 4, 5},              // 수포자 1
                {2, 1, 2, 3, 2, 4, 2, 5},      // 수포자 2
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5} // 수포자 3
        };

        // 각 수포자의 점수를 저장할 배열
        int[] scores = new int[3];

        // 정답과 각 수포자의 답을 비교하여 점수 계산
        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < pattern.length; j++) {
                // i번째 문제의 정답이 수포자의 패턴과 일치하면 점수 +1
                if (answers[i] == pattern[j][i % pattern[j].length]) {
                    scores[j]++;
                }
            }
        }

        // 가장 높은 점수를 찾기
        int maxScore = Arrays.stream(scores).max().getAsInt();

        // 최고 점수를 받은 수포자의 번호를 리스트에 저장
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == maxScore) {
                answer.add(i + 1); // 수포자 번호는 1부터 시작
            }
        }

        // 리스트를 int 배열로 변환 후 반환
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
