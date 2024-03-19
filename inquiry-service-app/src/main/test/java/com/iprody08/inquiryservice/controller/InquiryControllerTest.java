package com.iprody08.inquiryservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class InquiryControllerTest {

    @Autowired
    private InquiryController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}