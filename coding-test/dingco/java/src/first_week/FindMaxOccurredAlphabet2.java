package first_week;

public class FindMaxOccurredAlphabet2 {

    public static void main(String[] args) {
        System.out.println("정답 = i 현재 풀이 값 = " + findMaxOccurredAlphabet("hello my name is dingcodingco"));
        System.out.println("정답 = e 현재 풀이 값 = " + findMaxOccurredAlphabet("we love algorithm"));
        System.out.println("정답 = b 현재 풀이 값 = " + findMaxOccurredAlphabet("best of best youtube"));
    }

    private static char findMaxOccurredAlphabet(String string) {
        int[] alphabet = new int[26];
        for(char ch : string.toCharArray()){
            if(Character.isAlphabetic(ch)) alphabet[ch - 'a']++;
        }
        int maxIdx = 0;
        for(int i=1; i<alphabet.length; i++) {
            if(alphabet[i] > alphabet[maxIdx]) maxIdx = i;
        }
        return (char) ('a' + maxIdx);
    }
}
