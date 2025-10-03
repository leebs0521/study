package lambda.lambda5.filter;

import lambda.ex2.MyPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilterMain3 {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 짝수
        List<Integer> evens = filter(list, n -> n % 2 == 0);
        System.out.println("evens = " + evens);
        
        // 홀수
        List<Integer> odds = filter(list, n -> n % 2 != 0);
        System.out.println("odds = " + odds);
    }

    private static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer num : list) {
            if (predicate.test(num)) {
                filtered.add(num);
            }
        }
        return filtered;
    }
}
