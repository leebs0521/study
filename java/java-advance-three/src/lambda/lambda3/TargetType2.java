package lambda.lambda3;

import java.util.function.Function;

public class TargetType2 {

    public static void main(String[] args) {
        Function<String, String> upper = s -> s.toUpperCase();
        System.out.println("upper = " + upper.apply("Hello"));

        Function<Integer, Integer> square = n -> n * n;
        System.out.println("square = " + square.apply(3));
    }
}
