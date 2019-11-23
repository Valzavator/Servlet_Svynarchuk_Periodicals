package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.*;
import com.gmail.maxsvynarchuk.persistence.entity.*;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeriodicalServiceTest {
    @InjectMocks
    private PeriodicalService periodicalService = PeriodicalService.getInstance();
    @Mock
    private PeriodicalDao periodicalDao;
    @Mock
    private PeriodicalTypeDao periodicalTypeDao;
    @Mock
    private FrequencyDao frequencyDao;
    @Mock
    private PublisherDao publisherDao;

    @Test
    void createPeriodicalTest() {
        Long periodicalId = 1L;
        Periodical periodical = Periodical.newBuilder().build();
        when(periodicalDao.insert(periodical)).then((Answer<Periodical>) invocationOnMock -> {
            Periodical toReturn = invocationOnMock.getArgument(0);
            toReturn.setId(periodicalId);
            return toReturn;
        });

        Periodical actual = periodicalService.createPeriodical(periodical);

        assertEquals(periodicalId, actual.getId());
        verify(periodicalDao, times(1)).insert(periodical);
    }

    @Test
    void updatePeriodicalTest() {
        Periodical periodical = Periodical.newBuilder().build();
        periodicalService.updatePeriodical(periodical);
        verify(periodicalDao, times(1)).update(periodical);
    }

    @Test
    void changeStatusOnAnotherStatusTest() {
        Periodical periodical = Periodical.newBuilder()
                .setStatus(PeriodicalStatus.ACTIVE)
                .build();

        periodicalService.changeStatus(periodical, PeriodicalStatus.SUSPENDED);

        assertEquals(periodical.getStatus(), PeriodicalStatus.SUSPENDED);
        verify(periodicalDao, times(1)).update(periodical);
    }

    @Test
    void changeStatusOnSameStatusTest() {
        Periodical periodical = Periodical.newBuilder()
                .setStatus(PeriodicalStatus.ACTIVE)
                .build();

        periodicalService.changeStatus(periodical, PeriodicalStatus.ACTIVE);

        assertEquals(periodical.getStatus(), PeriodicalStatus.ACTIVE);
        verify(periodicalDao, never()).update(periodical);
    }

    @Test
    void findPeriodicalByIdWithExistingPeriodicalTest() {
        Long periodicalId = 1L;
        Optional<Periodical> expected = Optional.of(
                Periodical.newBuilder()
                        .setId(periodicalId)
                        .build());
        when(periodicalDao.findOne(periodicalId)).thenReturn(expected);

        Optional<Periodical> actual = periodicalService.findPeriodicalById(periodicalId);

        assertEquals(expected, actual);
        verify(periodicalDao, times(1)).findOne(periodicalId);
    }

    @Test
    void findPeriodicalByIdWithNotExistingPeriodicalTest() {
        Long periodicalId = 1L;
        when(periodicalDao.findOne(periodicalId)).thenReturn(Optional.empty());

        Optional<Periodical> periodicalOpt = periodicalService.findPeriodicalById(periodicalId);

        assertFalse(periodicalOpt.isPresent());
        verify(periodicalDao, times(1)).findOne(periodicalId);
    }

    @Test
    void findAllPeriodicalsTest() {
        long skip = 0;
        long limit = 3;
        List<Periodical> expected = new ArrayList<Periodical>() {{
            add(Periodical.newBuilder().setId(1L).build());
            add(Periodical.newBuilder().setId(2L).build());
            add(Periodical.newBuilder().setId(3L).build());
        }};
        when(periodicalDao.findAll(skip, limit)).thenReturn(expected);

        List<Periodical> actual = periodicalService.findAllPeriodicals(skip, limit);

        assertEquals(3, actual.size());
        verify(periodicalDao, times(1)).findAll(skip, limit);
    }

    @Test
    void findAllPeriodicalsByStatusTest() {
        long skip = 0;
        long limit = 3;
        PeriodicalStatus status = PeriodicalStatus.ACTIVE;
        List<Periodical> expected = new ArrayList<Periodical>() {{
            add(Periodical.newBuilder().setId(1L).build());
            add(Periodical.newBuilder().setId(2L).build());
            add(Periodical.newBuilder().setId(3L).build());
        }};
        when(periodicalDao.findByStatus(status, skip, limit))
                .thenReturn(expected);

        List<Periodical> actual = periodicalService.findAllPeriodicalsByStatus(status, skip, limit);

        assertEquals(3, actual.size());
        verify(periodicalDao, times(1)).findByStatus(status, skip, limit);
    }

    @Test
    void getPeriodicalsCountTest() {
        long expected = 10;
        when(periodicalDao.getCount()).thenReturn(expected);

        long actual = periodicalService.getPeriodicalsCount();

        assertEquals(expected, actual);
        verify(periodicalDao, times(1)).getCount();
    }

    @Test
    void getPeriodicalsCountByStatusTest() {
        PeriodicalStatus status = PeriodicalStatus.ACTIVE;
        long expected = 10;
        when(periodicalDao.getCountByStatus(status)).thenReturn(expected);

        long actual = periodicalService.getPeriodicalsCountByStatus(status);

        assertEquals(expected, actual);
        verify(periodicalDao, times(1)).getCountByStatus(status);
    }

    @Test
    void findAllPeriodicalTypesTest() {
        List<PeriodicalType> expected = new ArrayList<PeriodicalType>() {{
            add(PeriodicalType.newBuilder().setId(1).build());
            add(PeriodicalType.newBuilder().setId(2).build());
            add(PeriodicalType.newBuilder().setId(3).build());
        }};
        when(periodicalTypeDao.findAll()).thenReturn(expected);

        List<PeriodicalType> actual = periodicalService.findAllPeriodicalTypes();

        assertEquals(3, actual.size());
        verify(periodicalTypeDao, times(1)).findAll();
    }

    @Test
    void findAllFrequenciesTest() {
        List<Frequency> expected = new ArrayList<Frequency>() {{
            add(Frequency.newBuilder().setId(1).build());
            add(Frequency.newBuilder().setId(2).build());
            add(Frequency.newBuilder().setId(3).build());
        }};
        when(frequencyDao.findAll()).thenReturn(expected);

        List<Frequency> actual = periodicalService.findAllFrequencies();

        assertEquals(3, actual.size());
        verify(frequencyDao, times(1)).findAll();
    }

    @Test
    void findAllPublishersTest() {
        List<Publisher> expected = new ArrayList<Publisher>() {{
            add(new Publisher(1L, "Publisher 1"));
            add(new Publisher(2L, "Publisher 2"));
            add(new Publisher(3L, "Publisher 3"));
        }};
        when(publisherDao.findAll()).thenReturn(expected);

        List<Publisher> actual = periodicalService.findAllPublishers();

        assertEquals(3, actual.size());
        verify(publisherDao, times(1)).findAll();
    }
}