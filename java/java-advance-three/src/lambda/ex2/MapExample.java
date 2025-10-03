package lambda.ex2;

import java.util.ArrayList;
import java.util.List;

public class MapExample {

    public static void main(String[] args) {
        List<String> words = List.of("hello", "java", "lambda");
        System.out.println("원본 리스트: " + words);

        // 1. 대문자 변환
        List<String> upperWords = map(words, s -> s.toUpperCase());
        System.out.println("upperWords = " + upperWords);

        // 2. 앞뒤에 *** 붙이기 (람다로 작성)
        List<String> starsWords = map(words, s -> "***" + s + "***");
        System.out.println("starsWords = " + starsWords);
    }

    private static List<String> map(List<String> list, StringFunction function) {
        List<String> res = new ArrayList<>();
        for (String s : list) {
            res.add(function.apply(s));
        }
        return res;
    }
}
