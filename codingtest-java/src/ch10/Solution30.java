package ch10;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution30 {

    public static void main(String[] args) {
        int[][] operations1 = {{0, 0, 1}, {0, 1, 2}, {1, 1, 2}};
        System.out.println(Arrays.toString(solution(3, operations1)));
        int[][] operations2 = {{0, 0, 1}, {1, 1, 2}, {0, 1, 2}, {1, 0, 2}};
        System.out.println(Arrays.toString(solution(3, operations2)));
    }

    private static int[] parent;

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static void union(int a, int b) {
        int ra = find(a), rb = find(b);
        parent[ra] = rb;
    }

    private static Boolean[] solution(int k, int[][] operations) {
        parent = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            parent[i] = i;
        }

        ArrayList<Boolean> answer = new ArrayList<>();

        for (int[] op : operations) {
            if (op[0] == 0) {
                union(op[1], op[2]);
            } else {
                answer.add(find(op[1]) == find(op[2]));
            }
        }
        return answer.toArray(new Boolean[0]);
    }
}
