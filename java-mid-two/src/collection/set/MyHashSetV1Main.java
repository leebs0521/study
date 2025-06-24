package collection.set;

public class MyHashSetV1Main {

    public static void main(String[] args) {

        MyHashSetV1 set = new MyHashSetV1(10);
        set.add(1);
        set.add(2);
        set.add(5);
        set.add(8);
        set.add(14);
        set.add(99);
        set.add(9); // hashIndex 중복
        System.out.println("set = " + set);

        // 검색
        int searchValue = 9;
        System.out.println("set.contains(searchValue) = " + set.contains(searchValue));

        // 삭제
        System.out.println("set.remove(searchValue) = " + set.remove(searchValue));
        System.out.println("set = " + set);
    }
}
