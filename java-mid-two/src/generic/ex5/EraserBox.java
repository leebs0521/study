package generic.ex5;

public class EraserBox<T> {

    public boolean instanceCheck(Object param) {
//        return param instanceof T; //오류
        return false;
    }

    public T create() {
//        return new T(); // 오류
        return null;
    }
}
