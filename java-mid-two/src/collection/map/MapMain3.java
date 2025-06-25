package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain3 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        // 학생 성적 데이터 추가
        map.put("studentA", 50);
        System.out.println(map);

        // 학생이 없는 경우에만 추가
        map.putIfAbsent("studentA", 100); // 안들어감
        map.putIfAbsent("studentB", 100);
        System.out.println(map);
    }
}
