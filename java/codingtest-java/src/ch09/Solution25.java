package ch09;

public class Solution25 {
    /*
     * 이진 트리의 순회 결과를 반환하는 솔루션
     * 1. 배열로 표현된 이진 트리를 전위/중위/후위 순회
     * 2. 각 순회 결과를 문자열로 변환
     * 3. 세 가지 순회 결과를 배열로 반환
     *
     * @param nodes 이진 트리를 나타내는 정수 배열
     * @return [전위순회결과, 중위순회결과, 후위순회결과]
     */
    public static String[] solution(int[] nodes) {
        String[] result = new String[3];
        result[0] = preorder(nodes, 0).trim();  // 전위 순회
        result[1] = inorder(nodes, 0).trim();   // 중위 순회
        result[2] = postorder(nodes, 0).trim(); // 후위 순회
        return result;
    }

    /*
     * 전위 순회 수행 (루트 -> 왼쪽 -> 오른쪽)
     * @param nodes 이진 트리 배열
     * @param idx 현재 노드의 인덱스
     * @return 순회 결과 문자열
     */
    private static String preorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }
        return nodes[idx] + " " +                // 현재 노드 방문
                preorder(nodes, 2 * idx + 1) +   // 왼쪽 자식 순회
                preorder(nodes, 2 * idx + 2);    // 오른쪽 자식 순회
    }

    /*
     * 중위 순회 수행 (왼쪽 -> 루트 -> 오른쪽)
     * @param nodes 이진 트리 배열
     * @param idx 현재 노드의 인덱스
     * @return 순회 결과 문자열
     */
    private static String inorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }
        return inorder(nodes, 2 * idx + 1) +     // 왼쪽 자식 순회
                nodes[idx] + " " +               // 현재 노드 방문
                inorder(nodes, 2 * idx + 2);     // 오른쪽 자식 순회
    }

    /*
     * 후위 순회 수행 (왼쪽 -> 오른쪽 -> 루트)
     * @param nodes 이진 트리 배열
     * @param idx 현재 노드의 인덱스
     * @return 순회 결과 문자열
     */
    private static String postorder(int[] nodes, int idx) {
        if (idx >= nodes.length) {
            return "";
        }
        return postorder(nodes, 2 * idx + 1) +   // 왼쪽 자식 순회
                postorder(nodes, 2 * idx + 2) +  // 오른쪽 자식 순회
                nodes[idx] + " ";                // 현재 노드 방문
    }
}
