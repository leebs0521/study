package lambda.lambda1;

import lambda.MyFunction;

public class LambdaSimple3 {

    public static void main(String[] args) {

        // 타입 샐략 전
        MyFunction function1 = (int a, int b) -> a + b;

        // MyFunction 타입을 통해 타입 추론 가능, 람다는 타입 생략 가능
        MyFunction function2 = (a, b) -> a + b;
        System.out.println("function2: " + function2.apply(1, 2));
    }
}
