package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.SourceDto;

import com.iprody08.inquiryservice.service.SourceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SourceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SourceService sourceService;

    @BeforeEach
    void setUpEntity() {
        // given
        SourceDto sourceDto = new SourceDto();
        sourceDto.setName("Test name");
        sourceService.save(sourceDto);
        SourceDto sourceDto2 = new SourceDto();
        sourceDto2.setName("Test2 name");
        sourceService.save(sourceDto2);
    }

    @AfterEach
    void clearRepository() {
        sourceService.deleteAll();
    }

    @Test
    void FindAllAndCheckSize() throws Exception {
        // when
        mockMvc.perform(get("/api/v1/sources")
                        .param("pageNo", "0")
                        .param("pageSize", "25")
                        .param("sortBy", "id")
                        .param("sortDirection", "asc")
                        .param("filterBy", "Test2 name")
                        .contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(print());
    }

    @Test
    void createAndCheckIncreaseSize()  throws Exception {
        // when
        SourceDto sourceDto = new SourceDto();
        sourceDto.setName("New source##3");
        sourceService.save(sourceDto);
        mockMvc.perform(get("/api/v1/sources")
            .contentType(MediaType.APPLICATION_JSON))
            //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andDo(print());
    }
}
