package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapMain2 {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        // 학생 성적 데이터 추가
        map.put("studentA", 90);
        System.out.println(map);

        map.put("studentA", 100); // 같은 키에 저장시 교체
        System.out.println(map);

        System.out.println("map.containsKey(\"studentA\") = " + map.containsKey("studentA"));

        // 특정 값 삭제
        map.remove("studentA");
        System.out.println(map);
    }
}
