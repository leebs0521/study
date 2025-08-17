package ch09;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
build idx from enroll
build parent from referral ( "-" -> -1 )
profit = int[n]  // 0으로 초기화

for each (s, cnt) in (seller, amount):
    cur = idx[s]
    money = cnt * 100
    while cur != -1 and money > 0:
        pass = money / 10
        keep = money - pass
        profit[cur] += keep
        cur = parent[cur]
        money = pass
return profit
*/
public class Solution27 {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        // 1) 이름 -> 인덱스
        Map<String, Integer> idx = new HashMap<>(n * 2);
        for (int i = 0; i < n; i++) idx.put(enroll[i], i);

        // 2) 부모 인덱스 구성
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            String p = referral[i];
            if (!"-".equals(p)) {
                Integer pid = idx.get(p);
                parent[i] = (pid != null) ? pid : -1; // 방어적 처리(없으면 센터 취급)
            }
        }

        // 3) 이익 전파
        int[] profit = new int[n];
        for (int k = 0; k < seller.length; k++) {
            int cur = idx.get(seller[k]);
            int money = amount[k] * 100; // 개당 100원

            while (cur != -1 && money > 0) {
                int pass = money / 10;      // 절사
                int keep = money - pass;    // 자신 몫
                profit[cur] += keep;

                cur = parent[cur];
                money = pass;               // 위로 전파
            }
        }
        return profit;
    }
}
