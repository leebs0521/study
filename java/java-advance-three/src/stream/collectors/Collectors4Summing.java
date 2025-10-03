package stream.collectors;

import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collectors4Summing {

    public static void main(String[] args) {
        Long count1 = Stream.of(1, 2, 3)
                .collect(Collectors.counting());
        System.out.println("count1 = " + count1);

        long count2 = Stream.of(1, 2, 3)
                .count();
        System.out.println("count2 = " + count2);

        Double average1 = Stream.of(1, 2, 3)
                .collect(Collectors.averagingInt(i -> i));
        System.out.println("average1 = " + average1);

        double average2 = Stream.of(1, 2, 3)
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        System.out.println("average2 = " + average2);

        double average3 = IntStream.of(1, 2, 3)
                .average()
                .orElse(0);
        System.out.println("average3 = " + average3);

        // 통계
        IntSummaryStatistics stats = Stream.of("Apple", "Banana", "Tomato")
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(stats.getCount()); // 3
        System.out.println(stats.getSum()); // 17 (5+6+6)
        System.out.println(stats.getMin()); // 5
        System.out.println(stats.getMax()); // 6
        System.out.println(stats.getAverage()); // 5.66...
    }
}
