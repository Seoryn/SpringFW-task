package mylab.notification.di.annot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// Spring 설정 클래스
public class NotificationConfig {

    @Bean
    public EmailNotificationService emailNotificationService() {
        return new EmailNotificationService("smtp.gmail.com", 587);	// SMTP 서버와 포트 번호 설정
    }

    @Bean
    public SmsNotificationService smsNotificationService() {
        return new SmsNotificationService("SKT");	// 제공업체 설정
    }

    @Bean
    public NotificationManager notificationManager(
    		// 이미 등록된 이메일과 SMS 서비스 주입
            EmailNotificationService emailNotificationService,
            SmsNotificationService smsNotificationService) {
        return new NotificationManager(emailNotificationService, smsNotificationService);
    }
}
