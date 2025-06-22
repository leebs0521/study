package ch06;

import java.util.Stack;

public class Solution11 {

//    입력 문자열 s
//    각 문자 c:
//            스택이 비었거나 peek != c → push
//            peek == c → pop
//    최종적으로 스택이 비었으면 1, 아니면 0
    public int solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if(!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }
}
