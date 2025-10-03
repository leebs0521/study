package lambda.lambda4;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperatorMain {

    public static void main(String[] args) {

        UnaryOperator<Integer> sqaure = i -> i * i;
        System.out.println("sqaure.apply(3) = " + sqaure.apply(3));

        BinaryOperator<Integer> add = (a, b) -> a + b;
        System.out.println("add.apply(10, 20) = " + add.apply(10, 20));
    }
}
