package defaultmethod.ex2;

import java.time.LocalDateTime;
import java.util.List;

public class NotifierMainV2 {

    public static void main(String[] args) {
        List<Notifier> notifiers = List.of(new AppPushNotifier(), new EmailNotifier(), new SMSNotifier());
        notifiers.forEach(
                notifier -> notifier.notify("서비스 가입을 환영합니다.")
        );

        LocalDateTime plus1Day = LocalDateTime.now().plusDays(1);
        notifiers.forEach(
                n -> n.scheduleNotification("hello!", plus1Day)
        );
    }
}
