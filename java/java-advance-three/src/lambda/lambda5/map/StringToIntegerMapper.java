package lambda.lambda5.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class StringToIntegerMapper {

    public static List<Integer> map(List<String> list, Function<String, Integer> mapper) {
        List<Integer> mappedList = new ArrayList<>();
        for (String num : list) {
            mappedList.add(mapper.apply(num));
        }
        return mappedList;
    }
}
