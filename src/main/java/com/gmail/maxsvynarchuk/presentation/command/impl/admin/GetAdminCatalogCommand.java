package com.gmail.maxsvynarchuk.presentation.command.impl.admin;

import com.gmail.maxsvynarchuk.persistence.entity.Periodical;
import com.gmail.maxsvynarchuk.presentation.command.Command;
import com.gmail.maxsvynarchuk.presentation.command.CommandResult;
import com.gmail.maxsvynarchuk.presentation.util.PaginationManager;
import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.Views;
import com.gmail.maxsvynarchuk.service.PeriodicalService;
import com.gmail.maxsvynarchuk.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAdminCatalogCommand implements Command {
    private final PeriodicalService periodicalService = ServiceFactory.getPeriodicalService();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO
        long rowsCount = periodicalService.getPeriodicalsCount();
        long skip = PaginationManager.manage(request, rowsCount);
        List<Periodical> periodicals = periodicalService.getPeriodicals(skip, PaginationManager.RECORDS_PER_PAGE);
        request.setAttribute(Attributes.CATALOG, periodicals);
        return CommandResult.forward(Views.ADMIN_CATALOG_VIEW);
    }
}
