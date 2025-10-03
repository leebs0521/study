package collection.list;

public class BatchProcessorMain {

    public static void main(String[] args) {
        MyList<Integer> list = new MyArrayList<>();
//        MyList<Integer> list = new MyLinkedList<>();

        BatchProcessor processor = new BatchProcessor(list);
        processor.logic(50_000);
    }
}
