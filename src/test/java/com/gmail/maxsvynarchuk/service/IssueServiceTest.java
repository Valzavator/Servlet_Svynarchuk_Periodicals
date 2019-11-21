package com.gmail.maxsvynarchuk.service;

import com.gmail.maxsvynarchuk.persistence.dao.PeriodicalIssueDao;
import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.persistence.entity.PeriodicalIssue;
import com.gmail.maxsvynarchuk.provider.EntityProvider;
import com.gmail.maxsvynarchuk.util.type.PeriodicalStatus;
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
    void addIssueWithUniqueIssueNumberToPeriodical() {
        Periodical periodical = EntityProvider.getPeriodical(1L,
                PeriodicalStatus.ACTIVE, "3");
        PeriodicalIssue periodicalIssue =
                EntityProvider.getPeriodicalIssue(1L, periodical);

        when(periodicalIssueDao.existByNumberAndPeriodical(periodicalIssue.getIssueNumber(),
                periodical))
                .thenReturn(false);

        assertTrue(issueService.addIssueToPeriodical(periodical, periodicalIssue));
        verify(periodicalIssueDao).insert(periodicalIssue);
    }

    @Test
    void addIssueWithNotUniqueIssueNumberToPeriodical() {
        Periodical periodical = EntityProvider.getPeriodical(1L,
                PeriodicalStatus.ACTIVE, "3");
        PeriodicalIssue periodicalIssue =
                EntityProvider.getPeriodicalIssue(1L, periodical);

        when(periodicalIssueDao.existByNumberAndPeriodical(periodicalIssue.getIssueNumber(),
                periodical))
                .thenReturn(true);

        assertFalse(issueService.addIssueToPeriodical(periodical, periodicalIssue));
        verify(periodicalIssueDao, never()).insert(periodicalIssue);
    }

    @Test
    void findAllIssuesByPeriodical() {
        Periodical periodical = EntityProvider.getPeriodical(1L,
                PeriodicalStatus.ACTIVE, "3");
        List<PeriodicalIssue> expected = new ArrayList<>();
        expected.add(EntityProvider.getPeriodicalIssue(1L, periodical));
        expected.add(EntityProvider.getPeriodicalIssue(2L, periodical));

        when(periodicalIssueDao.findByPeriodical(periodical)).thenReturn(expected);

        List<PeriodicalIssue> actual = issueService.findAllIssuesByPeriodical(periodical);
        assertEquals(expected, actual);
    }
}