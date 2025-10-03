package nested.inner;

public class InnerMain {

    public static void main(String[] args) {
        InnerOuter outer = new InnerOuter();
        InnerOuter.Inner inner = outer.new Inner();
        inner.print();
        System.out.println("inner.getClass() = " + inner.getClass());
    }
}
