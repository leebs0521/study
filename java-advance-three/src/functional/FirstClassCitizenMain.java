package functional;

import java.util.function.Function;

public class FirstClassCitizenMain {

    public static void main(String[] args) {
        Function<Integer, Integer> func = x -> x * 2;

        appyFunction(10, func);

        getFunc().apply(10);
    }

    private static Function<Integer, Integer> getFunc() {
        return x -> x * 2;
    }

    private static void appyFunction(int i, Function<Integer, Integer> func) {
        func.apply(i);
    }
}
