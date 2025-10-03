package nested.nested;

public class NestedOuter {

    private static int outClassValue = 3;
    private int outInstanceValue = 2;

    static class Nested {
        private int nestedInstatnceValue = 1;

        public void print() {

            // 자신의 멤버에 접근
            System.out.println(nestedInstatnceValue);

            // 바깥 클래스의 인스턴스 멤버에는 접근 불가
            // System.out.println(outInstanceValue);

            // 바깥 클래스의 클래스 멤버에는 접근할 수 있다. private 도 가능
            System.out.println(NestedOuter.outClassValue);
        }

    }
}
