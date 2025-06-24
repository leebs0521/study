package ch06;

import java.util.Stack;

public class Solution13 {
    //    1. 각 열(j)을 기준으로 Stack[] lanes를 초기화
    //    2. board를 아래에서 위로 순회하면서 0이 아닌 인형만 lanes[j]에 push
    //    3. moves를 하나씩 순회하며
    //        - 해당 열에서 인형이 있다면 pop
    //        - 바구니가 비어있지 않고, top과 같으면 둘 다 제거 후 +2
    //        - 아니면 바구니에 push
    //    4. 결과값 반환
    public int solution(int[][] board, int[] moves) {
        int n = board.length;
        Stack<Integer>[] lanes = new Stack[n];

        // 열별 스택 초기화
        for (int i = 0; i < n; i++) {
            lanes[i] = new Stack<>();
        }

        // 보드를 아래에서 위로 순회하며 스택에 인형 쌓기
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    lanes[j].push(board[i][j]);
                }
            }
        }

        Stack<Integer> bucket = new Stack<>();
        int answer = 0;

        // 크레인 동작 수행
        for (int move : moves) {
            int col = move - 1;
            if (!lanes[col].isEmpty()) {
                int doll = lanes[col].pop();
                if (!bucket.isEmpty() && bucket.peek() == doll) {
                    bucket.pop(); // 같은 인형 제거
                    answer += 2;
                } else {
                    bucket.push(doll); // 바구니에 인형 추가
                }
            }
        }

        return answer;
    }
}
