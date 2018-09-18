package com.qa.springboot.dvd.test.repository;

import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.model.UserModel;
import com.qa.springboot.dvd.repository.DvdRepository;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {UserModel.class})
@DataJpaTest
public class DvdRepositoryTest {

    private DvdModel model1;

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DvdRepository dvdRepository;

    @Before
    public void setup() {
        model1 = new DvdModel("Avengers 3", false, "" ,new Date());
        entityManager.persist(model1);
        entityManager.flush();
    }

    @Test
    public void getDvdById() {
        assertTrue(dvdRepository.findById(model1.getId()).isPresent());
    }

    @Test
    public void getAllDvd() {
        assertEquals(1, dvdRepository.findAll().size());
        dvdRepository.save(new DvdModel("Tremors",false,"CAN YOU FLY!?!?", new Date())
        );
        assertEquals(2, dvdRepository.findAll().size());
    }

    @Test
    public void testDeleteDvd() {
        dvdRepository.delete(model1);
        assertEquals(0, dvdRepository.findAll().size());
    }

    @Test
    public void testDeleteDvdById() {
        dvdRepository.deleteById(model1.getId());
        assertFalse(dvdRepository.findById(model1.getId()).isPresent());
    }

    @Test
    @Ignore
    public void testUpdateDvdById() {
//        dvdRepository.updateById(model1.getId());
        assertEquals("Avengers: Winter Soldier", dvdRepository.findById(model1.getId()));
    }
    @Test
    public void testFindDvdByTitle() {
        assertEquals(true, dvdRepository.findByTitle("Avengers 3").isPresent());
    }
}
