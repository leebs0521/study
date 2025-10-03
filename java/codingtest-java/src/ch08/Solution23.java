package ch08;

import java.util.HashMap;
import java.util.HashSet;

/*
1. id_list의 각 유저 이름을 인덱스로 매핑 (HashMap)
2. 각 유저가 신고한 유저를 저장할 HashSet[] set1
3. 각 유저가 신고당한 신고자 리스트를 저장할 HashSet[] set2
4. report 배열을 순회하며 중복 없이 set1, set2에 저장
5. 정지된 유저(banList)는 set2[i].size() >= k인 유저
6. 각 유저가 신고한 유저 중 정지된 유저가 있는 경우 count++
7. answer[i] = 메일을 받은 횟수로 저장
* */
public class Solution23 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int n = id_list.length;

        // 유저 이름을 인덱스로 매핑
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(id_list[i], i);
        }

        // 각 유저가 신고한 유저 집합
        HashSet<String>[] set1 = new HashSet[n];
        // 각 유저를 신고한 유저 집합
        HashSet<String>[] set2 = new HashSet[n];
        for (int i = 0; i < n; i++) {
            set1[i] = new HashSet<>();
            set2[i] = new HashSet<>();
        }

        // 중복 신고 제거 후 저장
        for (String r : report) {
            String[] token = r.split(" ");
            String reporter = token[0];
            String target = token[1];

            set1[map.get(reporter)].add(target);
            set2[map.get(target)].add(reporter);
        }

        // 정지된 유저 목록
        HashSet<String> banList = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (set2[i].size() >= k) {
                banList.add(id_list[i]);
            }
        }

        // 각 유저가 정지된 유저를 몇 명 신고했는지 확인
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (String banned : banList) {
                if (set1[i].contains(banned)) {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }
}

