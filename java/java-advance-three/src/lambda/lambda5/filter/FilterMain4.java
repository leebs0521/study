package lambda.lambda5.filter;

import java.util.List;

import static lambda.lambda5.filter.IntegerFilter.filter;

public class FilterMain4 {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 짝수
        List<Integer> evens = filter(list, n -> n % 2 == 0);
        System.out.println("evens = " + evens);

        // 홀수
        List<Integer> odds = filter(list, n -> n % 2 != 0);
        System.out.println("odds = " + odds);
    }
}
