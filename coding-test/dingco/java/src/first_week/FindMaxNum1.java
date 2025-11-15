package first_week;

public class FindMaxNum1 {

    public static void main(String[] args) {
        System.out.println("정답 = 6 / 현재 풀이 값 = " + findMaxNum(new int[]{3, 5, 6, 1, 2, 4}));
        System.out.println("정답 = 6 / 현재 풀이 값 = " + findMaxNum(new int[]{6, 6, 6}));
        System.out.println("정답 = 1888 / 현재 풀이 값 = " + findMaxNum(new int[]{6, 9, 2, 7, 1888}));
    }

    private static int findMaxNum(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (max < n) {
                max = n;
            }
        }
        return max;
    }

}
