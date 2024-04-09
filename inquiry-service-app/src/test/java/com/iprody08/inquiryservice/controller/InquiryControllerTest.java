package com.iprody08.inquiryservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.entity.Source;

import com.iprody08.inquiryservice.dto.mapper.SourceMapper;
import com.iprody08.inquiryservice.entity.enums.InquiryStatus;
import com.iprody08.inquiryservice.service.InquiryService;
import com.iprody08.inquiryservice.service.SourceService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Log4j2
@Transactional
class InquiryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private SourceService sourceService;

    @Autowired
    private SourceRepository sourceRepository;

    @Autowired
    private SourceMapper sourceMapper;

    @BeforeEach
    void setUpEntity(TestInfo testInfo) {
        // given
        log.info("start method setUpEntity");
        SourceDto sourceDto = new SourceDto();
        sourceDto.setName("Test name");
        sourceService.save(sourceDto);
        List<Source> sourceDto1 = sourceRepository.findAll();
        SourceDto sourceDto2 = sourceMapper.sourceToSourceDto(sourceDto1.get(0));
         InquiryDto one = new InquiryDto(sourceDto2, "YESSSSS", InquiryStatus.NEW, "note");
        InquiryDto two = new InquiryDto(sourceDto2, "comment2", InquiryStatus.REJECTED, "note");

        List<InquiryDto> inquiryDtoList = List.of(one, two);

        inquiryDtoList.forEach(dto -> inquiryService.save(dto));
        log.info("End test method "
                +  testInfo.getDisplayName()
                + " inquiryService.SIZE = " + inquiryService.findAll().size()
                + " sourceService.SIZE  = " + sourceService.findAll().size());
        // when

    }

/*    @AfterEach
    void clearRepository() {
       inquiryService.deleteAll();
       sourceService.deleteAll();
    }*/

    @Test
    @Order(1)
    void FindByIdAndCompareResults(TestInfo testInfo) throws Exception {
        log.info("Start test method "
                +  testInfo.getDisplayName()
                + " inquiryService.SIZE = " + inquiryService.findAll().size()
                + " sourceService.SIZE  = " + sourceService.findAll().size());
        // when
        List<InquiryDto> inquiryDtoList = inquiryService.findAll(0, 10, "id", "asc", null);
        assertFalse(inquiryDtoList.isEmpty(), "The list of inquiries is empty.");
        InquiryDto inquiryDto = inquiryDtoList.get(0);
        mockMvc.perform(get("/api/v1/inquiries/id/{id}", inquiryDto.getId())
                        .contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(inquiryDto.getId()))
                .andExpect(jsonPath("$.status").value(inquiryDto.getStatus().name()))
                .andExpect(jsonPath("$.comment").value(inquiryDto.getComment()))
                .andDo(print());
    }

    @Test
    @Order(2)
    void FindAllAndCheckSize(TestInfo testInfo) throws Exception {
        log.info("Start test method "
                +  testInfo.getDisplayName()
                + "inquiryService.SIZE = " + inquiryService.findAll().size()
                + "sourceService.SIZE  = " + sourceService.findAll().size());
        // when
        mockMvc.perform(get("/api/v1/inquiries")
                        .contentType(MediaType.APPLICATION_JSON))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @Test
    @Order(3)
    void deleteAndCheckDecreaseSize(TestInfo testInfo) throws Exception {
        log.info("Start test method "
                +  testInfo.getDisplayName()
                + " inquiryService.SIZE = " + inquiryService.findAll().size()
                + " sourceService.SIZE  = " + sourceService.findAll().size());
        //when
        mockMvc.perform(delete("/api/v1/inquiries/id/{id}", 1L))
        //then
                .andExpect(status().isOk())
                .andDo(print());
        List<InquiryDto> inquiryDtos = inquiryService.findAll();
        assertEquals(1, inquiryDtos.size());
        assertEquals(2, inquiryDtos.get(0).getId());
    }

    @Test
    @Order(4)
    void createAndCheckIncreaseSize(TestInfo testInfo)  throws Exception {
        log.info("Start test method "
                +  testInfo.getDisplayName()
                + " inquiryService.SIZE = " + inquiryService.findAll().size()
                + " sourceService.SIZE  = " + sourceService.findAll().size());
        //given
        List<InquiryDto> inquiryDtos = inquiryService.findAll();

        assertEquals(2, inquiryDtos.size());
        SourceDto sourceDto = inquiryDtos.get(0).getSourceId();
        InquiryDto newInquiryDto = new InquiryDto(sourceDto, "newInquiryDto", InquiryStatus.PAYMENT, "newInquiryDto");
        //when
        mockMvc.perform(post("/api/v1/inquiries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newInquiryDto)))
        //then
                .andExpect(status().isCreated())
                .andDo(print());
        inquiryDtos = inquiryService.findAll();
        assertEquals(3, inquiryDtos.size());
        assertEquals("newInquiryDto", inquiryDtos.get(2).getComment());

    }

    @Test
    @Order(5)
    void updateAndCheckChangedBody(TestInfo testInfo)  throws Exception {
        log.info("Start test method "
                +  testInfo.getDisplayName()
                + " inquiryService.SIZE = " + inquiryService.findAll().size()
                + " sourceService.SIZE  = " + sourceService.findAll().size());
        //given
        InquiryDto inquiryDto = inquiryService.findAll().get(0);
        inquiryDto.setStatus(InquiryStatus.PAID);

        assertEquals(2, inquiryService.findAll().size());

        //when
        mockMvc.perform(put("/api/v1/inquiries/id/{id}", inquiryDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(inquiryDto)))
        //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(inquiryDto.getStatus().name()))
                .andDo(print());

        assertEquals(2, inquiryService.findAll().size());
    }
}
