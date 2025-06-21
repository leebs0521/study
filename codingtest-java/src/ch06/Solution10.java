package ch06;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution10 {
    public int solution(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        int n = s.length();
        int answer = 0;

        for (int i = 0; i < n; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            boolean isValid = true;

            for (int j = 0; j < n; j++) {
                char ch = s.charAt((i + j) % n);

                if (!map.containsKey(ch)) {
                    stack.push(ch);
                } else {
                    if (stack.isEmpty() || !stack.pop().equals(map.get(ch))) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (isValid && stack.isEmpty()) answer++;
        }

        return answer;
    }
}
