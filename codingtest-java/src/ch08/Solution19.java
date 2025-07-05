package ch08;

import java.util.HashMap;

public class Solution19 {
    public String solution(String[] participant, String[] completion) {

        HashMap<String, Integer> map = new HashMap<>();
        for (String name : participant) {
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        for (String name : completion) {
            if (map.containsKey(name)) {
                int count = map.get(name);
                if (count == 1) {
                    map.remove(name);
                } else {
                    map.put(name, count - 1);
                }
            } else {
                return name;
            }
        }
        return map.keySet().iterator().next();
    }
}
