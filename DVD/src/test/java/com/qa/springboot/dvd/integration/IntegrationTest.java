package com.qa.springboot.dvd.integration;

import com.qa.springboot.dvd.DvdApplication;
import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.repository.DvdRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DvdApplication.class})
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DvdRepository repository;

    @Before
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void retrieveDvdFromDatabase() throws Exception {
        repository.save(new DvdModel());
//        mvc.perform(get());
    }


}
