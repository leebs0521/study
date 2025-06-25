package collection.set.test;

import java.util.HashSet;
import java.util.Set;

public class RectangleTest {

    public static void main(String[] args) {
        Set<Rectangle> set = new HashSet<>();
        set.add(new Rectangle(10, 10));
        set.add(new Rectangle(20, 20));
        set.add(new Rectangle(20, 20));

        for (Rectangle rectangle : set) {
            System.out.println("rectangle = " + rectangle);
        }
    }
}
