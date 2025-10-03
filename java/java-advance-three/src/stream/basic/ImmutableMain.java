package stream.basic;

import java.util.List;

public class ImmutableMain {

    public static void main(String[] args) {

        List<Integer> origin = List.of(1, 2, 3, 4, 5);
        System.out.println("origin = " + origin);

        List<Integer> filtered = origin.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        System.out.println("filtered = " + filtered);
        System.out.println("origin = " + origin);
    }
}
