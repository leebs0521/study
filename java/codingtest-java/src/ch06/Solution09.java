package ch06;

import java.util.Stack;

public class Solution09 {
    public static String solution(int decimal) {
        if (decimal == 0) return "0";

        Stack<Integer> stack = new Stack<>();

        while (decimal > 0) {
            stack.push(decimal % 2);
            decimal /= 2;
        }

        StringBuilder answer = new StringBuilder();
        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        return answer.toString();
    }
}
