package lambda.ex2;

import java.util.ArrayList;
import java.util.List;

public class FilterExample {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(-3, -2, -1, 1, 2, 3, 5);
        System.out.println("원본 리스트: " + numbers);

        List<Integer> negatives = filter(numbers, val -> val < 0);
        System.out.println("negatives: " + negatives);

        List<Integer> evens = filter(numbers, val -> val % 2 == 0);
        System.out.println("evens = " + evens);
    }

    private static List<Integer> filter(List<Integer> list, MyPredicate predicate) {
        List<Integer> res = new ArrayList<>();
        for (Integer val : list) {
            if (predicate.test(val)) {
                res.add(val);
            }
        }
        return res;
    }
}
