package stream.basic;

import java.util.List;
import java.util.stream.Stream;

public class DuplicateExecutionMain {

    public static void main(String[] args) {

        Stream<Integer> stream = Stream.of(1, 2, 3);
        stream.forEach(System.out::println);

        // 오류 메세지: java.lang.IllegalStateException
        // stream.forEach(System.out::println);

        // 대안: 대상 리스트를 스트림으로 새로 생성
        List<Integer> list = List.of(1, 2, 3);
        Stream.of(list).forEach(System.out::println);
        Stream.of(list).forEach(System.out::println);
    }
}
