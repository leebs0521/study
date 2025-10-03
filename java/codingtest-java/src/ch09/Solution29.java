package ch09;

import java.util.*;

public class Solution29 {
    static class Node {
        int x, y, idx;
        Node left, right;
        Node(int x, int y, int idx) {
            this.x = x; this.y = y; this.idx = idx;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;

        // 1) (x, y, idx) 노드 생성 (idx는 입력 순서 1..n)
        List<Node> nodes = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }

        // 2) y 내림차순, y 같으면 x 오름차순 정렬
        nodes.sort((a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });

        // 3) 트리 구성: 첫 노드가 루트, 나머지는 x 비교로 BST 삽입
        Node root = nodes.get(0);
        for (int i = 1; i < n; i++) insert(root, nodes.get(i));

        // 4) 전위/후위 순회 결과 수집
        List<Integer> pre = new ArrayList<>(n);
        List<Integer> post = new ArrayList<>(n);
        preorder(root, pre);
        postorder(root, post);

        // 5) 반환 형태로 변환
        int[][] answer = new int[2][n];
        for (int i = 0; i < n; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }
        return answer;
    }

    private static void insert(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else insert(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insert(parent.right, child);
        }
    }

    private static void preorder(Node node, List<Integer> out) {
        if (node == null) return;
        out.add(node.idx);
        preorder(node.left, out);
        preorder(node.right, out);
    }

    private static void postorder(Node node, List<Integer> out) {
        if (node == null) return;
        postorder(node.left, out);
        postorder(node.right, out);
        out.add(node.idx);
        }
}
