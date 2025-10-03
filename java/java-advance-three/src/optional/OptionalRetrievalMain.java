package optional;

import java.util.Optional;

public class OptionalRetrievalMain {

    public static void main(String[] args) {
        Optional<String> optVal = Optional.of("Hello");
        Optional<String> optEmpty = Optional.empty();

        // isPresent()
        System.out.println("=== 1. isPresent() / isEmpty() ===");
        System.out.println("optVal.isPresent() = " + optVal.isPresent());
        System.out.println("optEmpty.isPresent() = " + optEmpty.isPresent());
        System.out.println("optEmpty.isEmpty() = " + optEmpty.isEmpty());

        // get()
        System.out.println("=== 2. get() ===");
        System.out.println("optVal.get() = " + optVal.get());
//        System.out.println("optEmpty.get() = " + optEmpty.get());

        // orElse()
        System.out.println("=== 3. orElse() ===");
        System.out.println("optVal.orElse(\"기본값\") = " + optVal.orElse("기본값"));
        System.out.println("optEmpty.orElse(\"기본값\") = " + optEmpty.orElse("기본값"));

        // orElseGet()
        System.out.println("=== 4. orElseGet() ===");
        System.out.println("optVal.orElseGet(() -> \"기본값\") = " + optVal.orElseGet(() -> "기본값"));
        System.out.println("optEmpty.orElseGet(() -> \"기본값\") = " + optEmpty.orElseGet(() -> "기본값"));

        // orElseThrow()
        System.out.println("=== 5. orElseThrow() ===");
        String value3 = optVal.orElseThrow(() -> new RuntimeException("값이 없 습니다!"));
        System.out.println("value3 = " + value3);
        try {
            // optEmpty는 값이 없으므로 예외 발생
            String empty3 = optEmpty.orElseThrow(() -> new RuntimeException("값 이 없습니다!"));
            System.out.println("empty3 = " + empty3); // 실행되지 않음
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        // Optional을 반환
        System.out.println("=== 6. or() ===");
        Optional<String> result1 = optVal.or(() -> Optional.of("Fallback"));
        System.out.println(result1); // Optional[Hello], 값이 이미 존재 -> 원본 그대로
        Optional<String> result2 = optEmpty.or(() -> Optional.of("Fallback"));
        System.out.println(result2); // Optional[Fallback], 비어있으므로 대체 Optional 반환
    }
}
