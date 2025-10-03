package lambda.lambda5.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class GenericMapper {

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        List<R> mappedList = new ArrayList<>();
        for (T element : list) {
            mappedList.add(mapper.apply(element));
        }
        return mappedList;
    }
}
