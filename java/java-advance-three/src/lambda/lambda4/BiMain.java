package lambda.lambda4;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class BiMain {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("add.apply(10, 20) = " + add.apply(10, 20));

        BiConsumer<String, Integer> repeat = (s, n) -> {
            for (int i = 0; i < n; i++) {
                System.out.print(s);
            }
            System.out.println();
        };
        repeat.accept("*", 3);

        BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
        System.out.println("isGreater(10, 20) = " + isGreater.test(10, 20));
    }
}
