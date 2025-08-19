package ch09;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class Solution28 {

    static class Info {
        int node;
        int sheep;
        int wolf;
        HashSet<Integer> visited;

        public Info(int node, int sheep, int wolf, HashSet<Integer> visited) {
            this.node = node;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
    }

    private static ArrayList<Integer>[] tree; // 인접리스트 트리

    // 트리 구축 메서드
    private static void buildTree(int[] info, int[][] edges) {
        tree = new ArrayList[info.length];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }
    }

    public int solution(int[] info, int[][] edges) {
        buildTree(info, edges); // 트리 생성
        int ans = 0;

        ArrayDeque<Info> q = new ArrayDeque<>();
        q.add(new Info(0, 1, 0, new HashSet<>())); // 큐 생성

        // BFS
        while (!q.isEmpty()) {
            Info cur = q.poll(); // 하나 꺼냄
            ans = Math.max(ans, cur.sheep); // 최대 양 최신화
            cur.visited.addAll(tree[cur.node]); // 현재 노드에서 갈 수 있는 이웃 노드 추가

            // 다음
            for (int next : cur.visited) {
                // 방문 리스트 복사(next 제거)
                HashSet<Integer> set = new HashSet<>(cur.visited);
                set.remove(next);

                // 늑대 일 경우
                if (info[next] == 1) {
                    if (cur.sheep > cur.wolf + 1) { // 양 > 늑대 일 경우에만 다음이 존재
                        q.add(new Info(next, cur.sheep, cur.wolf + 1, set));
                    }
                } else { // 양일 경우는 무조건 다음이 존재
                    q.add(new Info(next, cur.sheep + 1, cur.wolf, set));
                }
            }
        }
        return ans;
    }
}
