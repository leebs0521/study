package collection.set;

public class StringHashMain {

    private static final int CAPACITY = 10;
    public static void main(String[] args) {

        // char
        char charA = 'A';
        char charB = 'B';
        System.out.println("(int) charA = " + (int) charA);
        System.out.println("(int) charB = " + (int) charB);

        // hashCode
        System.out.println("hashCode(\"A\") = " + hashCode("A"));
        System.out.println("hashCode(\"B\") = " + hashCode("B"));
        System.out.println("hashCode(\"AB\") = " + hashCode("AB"));

        // hashIndex
        System.out.println("hashIndex(hashCode(\"A\")) = " + hashIndex(hashCode("A")));
        System.out.println("hashIndex(hashCode(\"B\")) = " + hashIndex(hashCode("B")));
        System.out.println("hashIndex(hashCode(\"AB\")) = " + hashIndex(hashCode("AB")));
    }

    private static int hashIndex(int value) {
        return value % CAPACITY;
    }

    private static int hashCode(String str) {
        return str.chars()
                .sum();
    }
}
