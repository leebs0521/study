package lambda.ex1;

import lambda.MyFunction;

public class M5Return {

    // operator에 따라 다른 람다(=함수)를 반환
    public static void main(String[] args) {
        MyFunction add = getOperation("add");
        System.out.println("add(1, 2) = " + add.apply(1, 2));

        MyFunction sub = getOperation("sub");
        System.out.println("sub(1, 2) = " + sub.apply(1, 2));

        MyFunction xxx = getOperation("xxx");
        System.out.println("xxx(1, 2) = " + xxx.apply(1, 2));
    }

    public static MyFunction getOperation(String operator) {
        return switch (operator) {
            case "add" -> (a, b) -> a + b;
            case "sub" -> (a, b) -> a - b;
            default -> (a, b) -> 0; // 잘못된 연산자일 경우 0 반환
        };
    }
}
