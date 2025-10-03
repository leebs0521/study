package lambda.lambda5.mysteam;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStreamV2 {

    private final List<Integer> internalList;

    private MyStreamV2(List<Integer> list) {
        this.internalList = list;
    }

    static MyStreamV2 of(List<Integer> list) {
        return new MyStreamV2(list);
    }

    public MyStreamV2 filter(Predicate<Integer> predicate) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer element : internalList) {
            if (predicate.test(element)) {
                filtered.add(element);
            }
        }
        return MyStreamV2.of(filtered);
    }

    public MyStreamV2 map(Function<Integer, Integer> mapper) {
        List<Integer> mapped = new ArrayList<>();
        for (Integer element : internalList) {
            mapped.add(mapper.apply(element));
        }
        return MyStreamV2.of(mapped);
    }

    public List<Integer> toList() {
        return internalList;
    }
}
