package lambda.lambda5.mysteam;

import lambda.lambda5.filter.GenericFilter;
import lambda.lambda5.map.GenericMapper;

import java.util.List;

public class Ex1 {

    public static void main(String[] args) {
        // 짝수만 남기고, 남은 값의 2배를 반환
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> lambdaResult = lambda(numbers);
        System.out.println("lambdaResult = " + lambdaResult);
    }

    static List<Integer> lambda(List<Integer> numbers) {
        return GenericMapper.map(
                GenericFilter.filter(numbers, n1 -> n1 % 2 == 0),
                n -> n * 2);
    }
}
