package lambda.lambda6;

public class OuterMain {

    private final String message = "외부 클래스";

    public void execute() {
        Runnable anonymous = new Runnable() {

            private final String message = "내부 클래스";

            @Override
            public void run() {
                // 익명 클래스에서의 this는 익명 클래스의 인스턴스를 가리킴
                System.out.println("[익명 클래스] this: " + this);
                System.out.println("[익명 클래스] this.class: " + this.getClass());
                System.out.println("[익명 클래스] this.message: " + this.message);
            }
        };

        Runnable lambda = () -> {
            // 람다에서의 this는 람다가 선언된 클래스의 인스턴스(즉, 외부 클래스) 가리킴
            System.out.println("[익명 클래스] this: " + this);
            System.out.println("[익명 클래스] this.class: " + this.getClass());
            System.out.println("[익명 클래스] this.message: " + this.message);
        };

        anonymous.run();
        System.out.println();
        lambda.run();
    }

    public static void main(String[] args) {
        OuterMain outerMain = new OuterMain();
        System.out.println("outerMain = " + outerMain);
        System.out.println();
        outerMain.execute();
    }

}
