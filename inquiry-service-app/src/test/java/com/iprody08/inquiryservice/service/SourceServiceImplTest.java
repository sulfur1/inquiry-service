package com.iprody08.inquiryservice.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.iprody08.inquiryservice.dao.SourceRepository;
import com.iprody08.inquiryservice.dto.SourceDto;
import com.iprody08.inquiryservice.dto.mapper.SourceMapper;
import com.iprody08.inquiryservice.entity.Source;
import com.iprody08.inquiryservice.filter.SourceFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SourceServiceImplTest {
    @Mock
    private SourceRepository sourceRepository;

    @Mock
    private SourceMapper sourceMapper;

    @InjectMocks
    private SourceServiceImpl sourceService;

    private Source source;
    private SourceDto sourceDto;

    @BeforeEach
    void setUp() {
        source = new Source();
        sourceDto = new SourceDto(null, "Test Name");
    }

    @Test
    void whenFiltersAreProvided_thenSourcesAreFiltered() {
        //given
        SourceFilter filterBy = new SourceFilter();
        filterBy.setName("Test Name");

        when(sourceRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(Collections.singletonList(source)));
        when(sourceMapper.sourceToSourceDto(any(Source.class))).thenReturn(sourceDto);

        //when
        sourceService.save(sourceDto);
        List<SourceDto> results = sourceService.findAll(0, 10, "name", "asc", filterBy);

        //then
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(sourceDto, results.get(0));
    }

    @Test
    void testSave() {
        //given
        when(sourceMapper.sourceDtoToSource(any(SourceDto.class))).thenReturn(source);

        //when
        sourceService.save(sourceDto);

        //then:
        verify(sourceMapper).sourceDtoToSource(sourceDto);
        verify(sourceRepository).save(source);
    }

    @Test
    void testDeleteAll() {
        //given
        sourceService.deleteAll();

        //then
        verify(sourceRepository).deleteAll();
    }

}
