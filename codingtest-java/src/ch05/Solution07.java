package ch05;

import java.util.HashMap;
import java.util.HashSet;

public class Solution07 {

    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }

    private static final HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    public int solution(String dirs) {
        initLocation();
        int x = 5, y = 5; // 시작 좌표
        HashSet<String> answer = new HashSet<>(); // 이동 경로 저장용

        for (int i = 0; i < dirs.length(); i++) {
            int[] offset = location.get(dirs.charAt(i));
            int nx = x + offset[0];
            int ny = y + offset[1];

            if (!isValidMove(nx, ny)) {
                continue; // 범위 밖은 무시
            }

            // 양방향 모두 저장
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);

            x = nx;
            y = ny;
        }

        return answer.size() / 2; // 양방향 저장했으므로 2로 나눔
    }
}
