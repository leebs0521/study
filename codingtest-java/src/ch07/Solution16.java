package ch07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution16 {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        int n = progresses.length;

        for (int i = 0; i < n; i++) {
            int time = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            queue.add(time);
        }

        int data = queue.poll();
        int cnt = 1;

        while (!queue.isEmpty()) {
            if (data >= queue.peek()) {
                cnt++;
                queue.poll();
            } else {
                answer.add(cnt);
                cnt = 1;
                data = queue.poll();
            }
        }

        answer.add(cnt); // 마지막 배포 추가

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
