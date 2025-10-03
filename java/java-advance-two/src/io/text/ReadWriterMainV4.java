package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;
import static java.nio.charset.StandardCharsets.UTF_8;

public class ReadWriterMainV4 {

    private static final int BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {
        String writeString = "ABC\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        // 파일 쓰기
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, UTF_8), BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일 읽기
        BufferedReader br =new BufferedReader(new FileReader(FILE_NAME, UTF_8), BUFFER_SIZE);

        StringBuilder content = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            content.append(line).append("\n");
        }
        br.close();

        System.out.println("== Read String ==");
        System.out.println(content);
    }
}
