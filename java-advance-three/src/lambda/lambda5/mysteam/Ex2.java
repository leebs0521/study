package lambda.lambda5.mysteam;

import lambda.lambda5.filter.GenericFilter;
import lambda.lambda5.map.GenericMapper;

import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        // 점수가 80점 이상인 학생의 이름을 추출해라.
        List<Student> students = List.of(
                new Student("Apple", 100),
                new Student("Banana", 80),
                new Student("Berry", 50),
                new Student("Tomato", 40)
        );

        List<String> lambdaResult = lambda(students);
        System.out.println("lambdaResult = " + lambdaResult);
    }

    private static List<String> lambda(List<Student> students) {
        return GenericMapper.map(
                GenericFilter.filter(students, s1 -> s1.getScore() >= 80),
                s -> s.getName()
        );
    }
}
