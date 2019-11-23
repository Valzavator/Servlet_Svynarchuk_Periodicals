package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IssueServiceTest {
    @InjectMocks
    private IssueService issueService = IssueService.getInstance();
    @Mock
    private PeriodicalIssueDao periodicalIssueDao;

    @Test
    void addIssueWithUniqueIssueNumberToPeriodicalTest() {
        Periodical periodical = Periodical.newBuilder()
                .setId(1L)
                .build();
        PeriodicalIssue periodicalIssue = PeriodicalIssue.newBuilder()
                .setId(1L)
                .setIssueNumber("1")
                .setPeriodical(periodical)
                .build();
        when(periodicalIssueDao.existByNumberAndPeriodical(
                periodicalIssue.getIssueNumber(), periodical))
                .thenReturn(false);

        assertTrue(issueService.addIssueToPeriodical(periodical, periodicalIssue));
        verify(periodicalIssueDao, times(1)).insert(periodicalIssue);
    }

    @Test
    void addIssueWithNotUniqueIssueNumberToPeriodicalTest() {
        Periodical periodical = Periodical.newBuilder()
                .setId(1L)
                .build();
        PeriodicalIssue periodicalIssue = PeriodicalIssue.newBuilder()
                .setId(1L)
                .setIssueNumber("1")
                .setPeriodical(periodical)
                .build();
        when(periodicalIssueDao.existByNumberAndPeriodical(periodicalIssue.getIssueNumber(),
                periodical))
                .thenReturn(true);

        assertFalse(issueService.addIssueToPeriodical(periodical, periodicalIssue));
        verify(periodicalIssueDao, never()).insert(periodicalIssue);
    }

    @Test
    void findAllIssuesByPeriodicalTest() {
        Periodical periodical = Periodical.newBuilder()
                .setId(1L)
                .build();
        List<PeriodicalIssue> expected = new ArrayList<PeriodicalIssue>() {{
            add(PeriodicalIssue.newBuilder()
                    .setId(1L)
                    .setIssueNumber("1")
                    .setPeriodical(periodical)
                    .build());
            add(PeriodicalIssue.newBuilder()
                    .setId(2L)
                    .setIssueNumber("2")
                    .setPeriodical(periodical)
                    .build());
        }};
        when(periodicalIssueDao.findByPeriodical(periodical)).thenReturn(expected);

        List<PeriodicalIssue> actual = issueService.findAllIssuesByPeriodical(periodical);

        assertEquals(2, actual.size());
        verify(periodicalIssueDao, times(1)).findByPeriodical(periodical);
    }
}