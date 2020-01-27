package com.iantonov.client.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iantonov.client.domain.ClientInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientInformationControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private ClientInformation info;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
        info =
                new ClientInformation("sale", "anna",
                        new Date(0, 0, 0, 0, 0, 0), "december");
    }

    @Test
    public void getInfo() throws Exception {
        mvc.perform(get("/client").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.managerList").exists());;
    }

    @Test
    public void postInfo() throws Exception {
        mvc.perform(
                post("/client")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(info)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.success").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
