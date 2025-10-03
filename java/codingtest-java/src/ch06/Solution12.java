package ch06;

import java.util.Stack;

public class Solution12 {
    /*
    * 1. 스택 선언 (stack<int>) → 가격이 떨어지지 않은 시작 인덱스 저장용
    * 2. stack.push(0) → 초기 인덱스 0 저장
    * 3. 반복문: i = 1부터 prices.length까지
    *   - 현재 가격이 이전 가격보다 낮다면 (가격 하락):
    *    - 스택에서 pop하며 answer 계산:
    *        answer[stack.peek] = 현재 인덱스 - stack.peek
    *   - 현재 인덱스 push
    *
    * 4. 반복이 끝난 뒤에도 스택에 남은 인덱스들:
    *   - 끝까지 가격이 떨어지지 않았던 것
    *   - answer[stack.peek] = prices.length - stack.peek - 1
    * */
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = n - j - 1;
        }

        return answer;
    }
}
