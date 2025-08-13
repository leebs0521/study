package ch09;

public class Solution26 {
    public int solution(int n, int a, int b) {
        int round = 0;

        // 두 번호가 같아질 때까지 반복
        while (a != b) {
            a = (a + 1) / 2; // 다음 라운드 번호
            b = (b + 1) / 2;
            round++;         // 라운드 수 증가
        }

        return round;
    }
}
