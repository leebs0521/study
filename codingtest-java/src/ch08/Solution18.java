package ch08;

import java.util.HashSet;
import java.util.Set;

public class Solution18 {

    private static boolean solution(int[] arr, int target) {
        Set<Integer> set = new HashSet<>();
        for (int n : arr) {
            if (set.contains(target - n)) {
                return true;
            }

            set.add(n);
        }

        return false;
    }

    public static void main(String[] args) {
        Solution18 solution = new Solution18();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 11;
        System.out.println(solution.solution(arr, target));
    }
}
