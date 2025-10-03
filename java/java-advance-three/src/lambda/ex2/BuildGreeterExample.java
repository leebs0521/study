package lambda.ex2;

public class BuildGreeterExample {

    public static void main(String[] args) {
        StringFunction helloGreeter = buildGreeter("Hello");
        StringFunction hiGreeter = buildGreeter("Hi");

        // 함수가 반환되었으므로, apply()를 호출해 실제로 사용
        System.out.println(helloGreeter.apply("Java")); // Hello, Java
        System.out.println(hiGreeter.apply("Lambda")); // Hi, Lambda
    }

    private static StringFunction buildGreeter(String greeting) {
        return name -> greeting + ", " + name;
    }
}
