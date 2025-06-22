package collection.list.test.ex1;

import java.util.ArrayList;
import java.util.Scanner;

public class ListEx3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();
        int sum = 0;
        System.out.println("n개의 정수를 입력하세요: 종료(0)");
        while (true) {
            int input = scanner.nextInt();
            if(input == 0) break;
            sum += input;
            numbers.add(input);
        }

        double avg = (double) sum / numbers.size();
        System.out.println("sum = " + sum);
        System.out.println("avg = " + avg);
    }
}
