package com.example.springwebservice;

import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.model.specification.UserSpecifications;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringWebServiceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void search_with_one_spec__shouldWork() {
        List<User> users = userRepository.findAll(UserSpecifications.likeFirstName("Yang"));
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void search_with_all_spec__shouldWork() {
        Specification spec = Specification.where(UserSpecifications.likeFirstName("Yang"))
                .and(UserSpecifications.likeLastName("Bill"))
                .and(UserSpecifications.equalGender("M"));
        List<User> users = userRepository.findAll(spec);
        assertNotNull(users);
        assertEquals(1, users.size());
    }


    @Test
    void search_using_or_spec__shouldWork() {
        Specification spec = Specification.where(UserSpecifications.likeFirstName("Yang"))
                .or(UserSpecifications.likeFirstName("Chen"));

        List<User> users = userRepository.findAll(spec);
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void contextLoads() {
    }

}
