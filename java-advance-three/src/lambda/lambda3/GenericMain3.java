package lambda.lambda3;

public class GenericMain3 {

    public static void main(String[] args) {
        GenericFunction<String, String> upper = s -> s.toUpperCase();
        System.out.println("upper = " + upper.apply("Hello"));

        GenericFunction<Integer, Integer> square = n -> n * n;
        System.out.println("square = " + square.apply(3));
    }

    @FunctionalInterface
    interface GenericFunction<T, R> {
        R apply(T t);
    }
}
