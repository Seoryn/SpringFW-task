package mylab.notification.di.annot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationConfig.class)	// NotificationConfig 설정 기반으로 Spring 컨텍스트 로드
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        assertNotNull(notificationManager);		// NotificationManager 레퍼런스가 Not Null인지 검증
        assertNotNull(notificationManager.getEmailService());
        assertNotNull(notificationManager.getSmsService());
    }

    // 이메일 서비스 검증
    @Test
    public void testEmailService() {
        assertTrue(notificationManager.getEmailService() instanceof EmailNotificationService);
        // Casting
        EmailNotificationService emailService =
            (EmailNotificationService)notificationManager.getEmailService();
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());
    }

    // SMS 서비스 검증
    @Test
    public void testSmsService() {
        assertTrue(notificationManager.getSmsService() instanceof SmsNotificationService);
        // Casting
        SmsNotificationService smsService =
            (SmsNotificationService)notificationManager.getSmsService();
        assertEquals("SKT", smsService.getProvider());
    }

    // 메서드 실행 테스트
    @Test
    public void testSendNotification() {
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}
