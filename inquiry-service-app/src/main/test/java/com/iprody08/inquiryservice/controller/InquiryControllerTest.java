package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.exception_handlers.NotFoundException;
import com.iprody08.inquiryservice.service.InquiryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class InquiryControllerTest {

    @Mock
    InquiryService inquiryService;

    @InjectMocks
    InquiryController inquiryController;

    @Test
    void contextLoads() throws Exception {
        assertThat(inquiryController).isNotNull();
    }

    @Test
    public void findAll_ReturnsValidSourceDto(){

        //given
        List<InquiryDto> expectedInquiries = Arrays.asList(
                new InquiryDto(),
                new InquiryDto()
        );
        when(inquiryController.findAll()).thenReturn(expectedInquiries);

        //when
        List<InquiryDto> actualInquiries = inquiryController.findAll();

        //then
        assertEquals(expectedInquiries.size(), actualInquiries.size());
        assertTrue(actualInquiries.containsAll(expectedInquiries));

    }

    @Test
    public void findAll_ReturnsEmptyList_WhenNoSourcesExist() {

        //given
        when(inquiryService.findAll()).thenReturn(Collections.emptyList());

        // when
        List<InquiryDto> actualInquiries = inquiryController.findAll();

        // then
        assertTrue(actualInquiries.isEmpty());
    }

    @Test
    public void findAll_ThrowsException_WhenDatabaseConnectionFails() {

        // given
        when(inquiryService.findAll()).thenThrow(new RuntimeException("Database connection failed"));

        // then
        assertThrows(RuntimeException.class, () -> inquiryController.findAll());
    }

    @Test
    public void findById_ReturnsValidSourceDto() {

        // given
        long id = 1L;
        InquiryDto expectedInquiry= new InquiryDto();
        when(inquiryService.findById(id)).thenReturn(Optional.of(expectedInquiry));

        // when
        InquiryDto actualSource = inquiryController.findById(id);

        // then
        assertEquals(expectedInquiry, actualSource);
    }

    @Test
    public void findById_ThrowsNotFoundException_WhenSourceNotFound() {

        // given
        long id = 1L;
        when(inquiryService.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> inquiryController.findById(id));
    }

    @Test
    public void deleteById_NoExceptionsThrown() {

        // given
        long id = 1L;

        // when
        assertDoesNotThrow(() -> inquiryController.deleteById(id));
    }

    @Test
    public void save_ReturnsCreatedStatus() {

        // given
        InquiryDto inquiryDto = new InquiryDto();

        // when
        ResponseEntity<Void> responseEntity = inquiryController.save(inquiryDto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void update_ReturnsUpdatedSourceDto() {

        // given
        long id = 1L;
        InquiryDto inquiryDto = new InquiryDto();
        when(inquiryService.update(id, inquiryDto)).thenReturn(Optional.of(inquiryDto));

        // when
        ResponseEntity<InquiryDto> responseEntity = inquiryController.update(id, inquiryDto);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(inquiryDto, responseEntity.getBody());
    }

    @Test
    public void update_ThrowsNotFoundException_WhenSourceNotFound() {

        // given
        long id = 1L;
        InquiryDto inquiryDto = new InquiryDto();
        when(inquiryService.update(id, inquiryDto)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> inquiryController.update(id, inquiryDto));
    }

}
