package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetMain {

    public static void main(String[] args) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();

        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }

        System.out.println("====");
        Charset charset1 = Charset.forName("ms949");
        System.out.println("charset1 = " + charset1);

        Set<String> aliases = charset1.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        // UTF-8 문자로 조회
        Charset charset2 = Charset.forName("UTF-8");
        System.out.println("charset2 = " + charset2);

        // UTF-8 상수로 조회
        Charset charset3 = StandardCharsets.UTF_8;
        System.out.println("charset3 = " + charset3);

        // 시스템의 기본 문자셋 조회
        Charset charset4 = Charset.defaultCharset();
        System.out.println("charset4 = " + charset4);
    }
}
