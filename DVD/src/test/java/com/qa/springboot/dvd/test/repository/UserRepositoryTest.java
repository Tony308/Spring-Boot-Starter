package com.qa.springboot.dvd.test.repository;

import com.qa.springboot.dvd.UserModel;
import com.qa.springboot.dvd.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserModel.class})
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    private UserModel userModel;

    @Before
    public void setup() {
        Date dob = new Date(1996,9,4);
        userModel = new UserModel("test", "data", dob, new Date(), new Date());
        testEntityManager.persist(userModel);
        testEntityManager.flush();
    }

    @Test
    public void testGetAllUsers() {
        assertEquals(1, userRepository.findAll().size());
    }
//
//    @Test
//    public void testGetUserById() {
//        assertTrue(userRepository.findById(userModel.getId()).isPresent());
//    }
//
//    @Test
//    public void testDeleteUser() {
//        userRepository.deleteById(userModel.getId());
//        assertFalse(userRepository.findById(userModel.getId()).isPresent());
//    }
//
//    @Test
//    public void testDeleteAllUsers() {
//        userRepository.deleteAll();
//        assertEquals(0, userRepository.findAll().size());
//    }
//
//    @Test
//    public void findByName() {
//        assertTrue(userRepository.findByName(userModel.getFirstName()).isPresent());
//    }
}
