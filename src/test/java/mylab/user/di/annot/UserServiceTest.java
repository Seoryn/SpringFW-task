package mylab.user.di.annot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;	// UserService 주입 받기

    @Test
    public void testDependencyInjection() {
        assertNotNull(userService);		// UserService의 레퍼런스가 Not Null인지 검증
        assertNotNull(userService.getUserRepository());		// userService.getUserRepository()가 Not Null인지 검증
        assertEquals("MySQL", userService.getUserRepository().getDbType());		// userService.getUserRepository().getDbType() 값이 MySQL인지 값 비교
        assertNotNull(userService.getSecurityService());	// userService.getSecurityService()가 Not Null인지 검증
    }

    // userService.registerUser() 메서드가 True와 False인지 검증
    @Test
    public void testRegisterUser() {
        assertTrue(userService.registerUser("user01", "홍길동", "1234"));	// 비밀번호가 있는 경우
        assertFalse(userService.registerUser("user02", "김철수", ""));	// 비밀번호가 빈 문자열인 경우
        assertFalse(userService.registerUser("user03", "이영희", null));	// 비밀번호가 null인 경우
    }
}