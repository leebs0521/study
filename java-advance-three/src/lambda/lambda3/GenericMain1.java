package lambda.lambda3;

public class GenericMain1 {

    public static void main(String[] args) {

        StringFunction upper = s -> s.toUpperCase();
        System.out.println("upper = " + upper.apply("Hello"));

        NumberFunction square = n -> n * n;
        System.out.println("square = " + square.apply(3));

    }

    @FunctionalInterface
    interface StringFunction {
        String apply(String s);
    }

    @FunctionalInterface
    interface NumberFunction {
        Integer apply(int n);
    }
}
