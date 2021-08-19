package com.rb.RB.user;

import com.rb.RB.entity.User;
import com.rb.RB.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    public static final String API = "/api/1.0/users";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void postUser_whenUserValid_receiveOk(){
        User user = getUser();

        ResponseEntity<Object> objectResponseEntity = testRestTemplate.postForEntity(API, user, Object.class);

        assertThat(objectResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postUser_whenUserIsValid_UserSavedToDataBase(){
         User user = getUser();

        testRestTemplate.postForEntity(API, user, Object.class);

        assertThat(userRepository.count()).isEqualTo(1);

    }

    private User getUser() {
        User user = new User();
        user.setUserName("test-user");
        user.setDisplayName("test-display");
        user.setPassword("T3st-password");
        return user;
    }

}
