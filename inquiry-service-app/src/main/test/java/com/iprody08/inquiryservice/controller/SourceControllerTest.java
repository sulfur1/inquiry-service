package com.iprody08.inquiryservice.controller;

import com.iprody08.inquiryservice.dto.InquiryDto;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.exception_handlers.NotFoundException;
import com.iprody08.inquiryservice.service.SourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class SourceControllerTest {

    @Mock
    SourceService sourceService;

    @InjectMocks
    SourceController sourceController;

    @Test
    void contextLoads() throws Exception {
        assertThat(sourceController).isNotNull();
    }


    @Test
    public void findAll_ReturnsValidSourceDto(){

        //given
        List<SourceDto> expectedSources = Arrays.asList(
                new SourceDto(),
                new SourceDto()
        );
        when(sourceService.findAll()).thenReturn(expectedSources);

        //when
        List<SourceDto> actualSources = sourceController.findAll();

        //then
        assertEquals(expectedSources.size(), actualSources.size());
        assertTrue(actualSources.containsAll(expectedSources));

    }

    @Test
    public void findAll_ReturnsEmptyList_WhenNoSourcesExist() {

        //given
        when(sourceService.findAll()).thenReturn(Collections.emptyList());

        // when
        List<SourceDto> actualSources = sourceController.findAll();

        // then
        assertTrue(actualSources.isEmpty());
    }

    @Test
    public void findAll_ThrowsException_WhenDatabaseConnectionFails() {

        // given
        when(sourceService.findAll()).thenThrow(new RuntimeException("Database connection failed"));

        // then
        assertThrows(RuntimeException.class, () -> sourceController.findAll());
    }

    @Test
    public void findById_ReturnsValidSourceDto() {

        // given
        long id = 1L;
        SourceDto expectedSource = new SourceDto();
        when(sourceService.findById(id)).thenReturn(Optional.of(expectedSource));

        // when
        SourceDto actualSource = sourceController.findById(id);

        // then
        assertEquals(expectedSource, actualSource);
    }

    @Test
    public void findById_ThrowsNotFoundException_WhenSourceNotFound() {

        // given
        long id = 1L;
        when(sourceService.findById(id)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> sourceController.findById(id));
    }

    @Test
    public void deleteById_NoExceptionsThrown() {

        // given
        long id = 1L;

        // when
        assertDoesNotThrow(() -> sourceController.deleteById(id));
    }

    @Test
    public void save_ReturnsCreatedStatus() {

        // given
        SourceDto sourceDto = new SourceDto();

        // when
        ResponseEntity<Void> responseEntity = sourceController.save(sourceDto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void update_ReturnsUpdatedSourceDto() {

        // given
        long id = 1L;
        SourceDto sourceDto = new SourceDto();
        when(sourceService.update(id, sourceDto)).thenReturn(Optional.of(sourceDto));

        // when
        ResponseEntity<SourceDto> responseEntity = sourceController.update(id, sourceDto);

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sourceDto, responseEntity.getBody());
    }

    @Test
    public void update_ThrowsNotFoundException_WhenSourceNotFound() {

        // given
        long id = 1L;
        SourceDto sourceDto = new SourceDto();
        when(sourceService.update(id, sourceDto)).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundException.class, () -> sourceController.update(id, sourceDto));
    }

}
