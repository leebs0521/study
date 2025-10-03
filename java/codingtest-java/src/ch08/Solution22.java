package ch08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
1. genreMap: <장르, [곡 인덱스, 재생 수]> 저장
2. playMap: <장르, 총 재생 수> 저장
3. 모든 곡을 순회하며 위 두 맵 채우기
4. playMap을 총 재생 수 기준 내림차순 정렬
5. 정렬된 각 장르에 대해:
    - 해당 장르의 곡들을 재생 수 기준 내림차순 정렬
    - 최대 2개 곡만 골라 정답에 추가
6. 리스트를 배열로 변환하여 반환
*/
public class Solution22 {

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<Song>> genreMap = new HashMap<>();
        HashMap<String, Integer> playMap = new HashMap<>();

        // Map 데이터 추가
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genreMap.putIfAbsent(genre, new ArrayList<>());
            playMap.put(genre, playMap.getOrDefault(genre, 0) + play);
            genreMap.get(genre).add(new Song(i, play));
        }

        ArrayList<Integer> answer = new ArrayList<>();

        // 장르별 재생수 내림차순
        List<String> genreSorted = playMap.keySet().stream()
                .sorted((a, b) -> playMap.get(b) - playMap.get(a))
                .toList();

        // 장르별 노래 중 재생 내림차순 2개
        genreSorted.forEach(g -> {
            genreMap.get(g).stream()
                    .sorted((s1, s2) -> Integer.compare(s2.play, s1.play))
                    .limit(2)
                    .forEach(s -> answer.add(s.idx));
        });

        return answer.stream().mapToInt(i -> i).toArray();
    }

    static class Song {
        int idx;
        int play;

        public Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }
}
