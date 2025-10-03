package stream.collectors;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collectors4MinMax {

    public static void main(String[] args) {
        Integer max1 = Stream.of(1, 2, 3)
                .collect(Collectors.maxBy(Integer::compareTo))
                .get();
        System.out.println("max1 = " + max1);

        Integer max2 = Stream.of(1, 2, 3)
                .max(Comparator.naturalOrder())
                .get();
        System.out.println("max2 = " + max2);

        int max3 = IntStream.of(1, 2, 3)
                .max()
                .getAsInt();
        System.out.println("max3 = " + max3);
    }
}
