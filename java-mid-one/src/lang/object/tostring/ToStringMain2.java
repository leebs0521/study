package lang.object.tostring;


public class ToStringMain2 {
    public static void main(String[] args) {
        Car car = new Car("Model Y");
        Dog dogOne = new Dog("Dog One ", 2);
        Dog dogTwo = new Dog("Dog Two ", 2);

        System.out.println("1. 단순 toString 호출");
        System.out.println(car.toString());
        System.out.println(dogOne.toString());
        System.out.println(dogTwo.toString());

        System.out.println("2. println 내부에서 toString 호출");
        //println 내부에서 toString 호출
        System.out.println(car);
        System.out.println(dogOne);
        System.out.println(dogTwo);

        System.out.println("3. Object 다형성 활용");
        ObjectPrinter.print(car);
        ObjectPrinter.print(dogOne);
        ObjectPrinter.print(dogTwo);
    }
}
