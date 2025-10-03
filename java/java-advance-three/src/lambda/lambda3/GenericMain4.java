package lambda.lambda3;

public class GenericMain4 {

    public static void main(String[] args) {
        GenericFunction<String, String> upper = s -> s.toUpperCase();
        GenericFunction<String, Integer> length = s -> s.length();
        GenericFunction<Integer, Integer> square = n -> n * n;
        GenericFunction<Integer, Boolean> isEven = n -> n % 2 == 0;

        System.out.println("upper = " + upper.apply("Hello"));
        System.out.println("length = " + length.apply("Hello"));
        System.out.println("square = " + square.apply(3));
        System.out.println("isEven = " + isEven.apply(3));
    }

    @FunctionalInterface
    interface GenericFunction<T, R> {
        R apply(T t);
    }
}
