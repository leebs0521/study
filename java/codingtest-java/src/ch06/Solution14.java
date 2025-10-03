package ch06;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution14 {

    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] isDeleted = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // 연결 리스트 초기화
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1; // 마지막 행은 next 없음

        for (String c : cmd) {
            char command = c.charAt(0);

            if (command == 'U') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) {
                    k = prev[k];
                }
            } else if (command == 'D') {
                int x = Integer.parseInt(c.substring(2));
                while (x-- > 0) {
                    k = next[k];
                }
            } else if (command == 'C') {
                stack.push(k);
                isDeleted[k] = true;

                // 연결 끊기
                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];

                // 커서 이동
                k = (next[k] != -1) ? next[k] : prev[k];
            } else if (command == 'Z') {
                int restore = stack.pop();
                isDeleted[restore] = false;

                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }

        // 결과 생성
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(isDeleted[i] ? 'X' : 'O');
        }

        return sb.toString();
    }
}
