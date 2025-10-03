package collection.iterable;

import java.util.Iterator;

public class MyArrayIterator implements Iterator<Integer> {


    private int currentIdx = -1;
    private int[] targetArr;

    public MyArrayIterator(int[] targetArr) {
        this.targetArr = targetArr;
    }

    @Override
    public boolean hasNext() {
        return currentIdx < targetArr.length - 1;
    }

    @Override
    public Integer next() {
        return targetArr[++currentIdx];
    }
}
