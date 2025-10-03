package ch07;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution15 {

    public int solution(int N, int K) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        while (queue.size() != 1) {
            for (int i = 0; i < K - 1; i++) {
                queue.add(queue.poll());
            }

            queue.poll();
        }

        return queue.poll();
    }
}
