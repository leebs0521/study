package lambda.lambda3;

public class GenericMain2 {

    public static void main(String[] args) {

        ObjectFunction upper = s -> ((String)s).toUpperCase();
        System.out.println("upper = " + (String) upper.apply("Hello"));

        ObjectFunction square = n -> (Integer)n * (Integer)n;
        System.out.println("square = " + (Integer) square.apply(3));

    }

    @FunctionalInterface
    interface ObjectFunction {
        Object apply(Object s);
    }

}
