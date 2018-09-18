package com.qa.springboot.dvd.test.integration;

import com.qa.springboot.dvd.DvdApplication;
import com.qa.springboot.dvd.model.DvdModel;
import com.qa.springboot.dvd.repository.DvdRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        repository.save(new DvdModel("Tremors", false, "", new Date()));

    }

    @Test
    public void retrieveDvdFromDatabase() throws Exception  {
        mvc.perform(MockMvcRequestBuilders.get("/dvd/getAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Tremors"));
    }

    @Test
    public void postDvd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/dvd/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"title\": \"Batman\", \"checkedOut\": \"false\", \"reference\": \"1234\", \"timeStamp\": \"2009-09-09\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Batman"));
    }

    @Test
    public void getByDvdId() throws Exception {
        repository.save(new DvdModel("Tremors", false, "", new Date()));
        mvc.perform(MockMvcRequestBuilders.get("/dvd/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Tremors"));
    }

    @Test
    public void putDvd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/dvd/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"title\": \"Crazy Rich Asians\" }"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Crazy Rich Asians"));
    }

    @Test
    public void rentDvd() throws Exception {
        repository.save(new DvdModel("Tremors", false, "", new Date()));
        mvc.perform(MockMvcRequestBuilders.put("/dvd/rent/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Tremors\", \"checkedOut\": \"true\", \"reference\": \"hello\", \"timeStamp\": \"" + new Date().toString() +"\" }")
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.checkedOut").value("true"));
    }

    @Test
    public void returnDvd() throws Exception {
        repository.save(new DvdModel());
        mvc.perform(MockMvcRequestBuilders.put("/dvd/return/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Tremors\", \"checkedOut\": \"false\", \"reference\": \"null\", \"timeStamp\": \"" + new Date().toString() + "\")}")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.checkedOut").value("false"));
    }


}
