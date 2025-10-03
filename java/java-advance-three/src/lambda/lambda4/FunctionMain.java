package lambda.lambda4;

import java.util.function.Function;

public class FunctionMain {

    public static void main(String[] args) {

        Function<String, Integer> length = s -> s.length();
        System.out.println("length = " + length.apply("Hello"));
    }
}
