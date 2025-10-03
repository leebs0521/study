package lambda.ex3;

import java.util.List;
import java.util.function.IntBinaryOperator;

public class ReduceExample {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4);
        System.out.println("리스트: " + numbers);

        // 1. 합 구하기 (초깃값 0, 덧셈 로직)
        int sum = reduce(numbers, 0, (a, b) -> a + b);
        System.out.println("sum = " + sum);

        // 2. 곱 구하기 (초깃값 1, 곱셈 로직)
        int product = reduce(numbers, 1, (a, b) -> a * b);
        System.out.println("product = " + product);
    }

    private static int reduce(List<Integer> list, int initial, IntBinaryOperator reducer) {
        int res = initial;
        for (Integer val : list) {
            res = reducer.applyAsInt(res, val);
        }
        return res;
    }
}
