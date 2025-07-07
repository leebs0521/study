package ch08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution21 {
    public String[] solution(String[] record) {
        Map<String, String> nameMap = new HashMap<>(); // 유저ID -> 닉네임 매핑

        // 1차 순회: 닉네임 최신 상태로 갱신 (Enter, Change 명령만)
        for (String cmd : record) {
            String[] token = cmd.split(" ");
            if (!token[0].equals("Leave")) {
                nameMap.put(token[1], token[2]); // ID별 최신 닉네임 저장
            }
        }

        List<String> answer = new ArrayList<>(); // 최종 메시지 리스트

        // 2차 순회: 메시지 생성
        for (String cmd : record) {
            String[] token = cmd.split(" ");
            String action = token[0];
            String uid = token[1];

            if (action.equals("Enter")) {
                answer.add(nameMap.get(uid) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                answer.add(nameMap.get(uid) + "님이 나갔습니다.");
            }
        }
        return answer.toArray(new String[0]); // 리스트 → 배열로 변환
    }
}
