package com.gmail.maxsvynarchuk.presentation.util;

import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class PaginationManager {
    public static final int RECORDS_PER_PAGE = 5;
    private static final int FIRST_PAGE = 1;

    public static int manage(HttpServletRequest req, long rowsCount) {
        String pageStr = req.getParameter(RequestParameters.PAGINATION_PAGE);
        //TODO - check NumberFormatException
        int page = Objects.isNull(pageStr) ? FIRST_PAGE : Integer.parseInt(pageStr);
        int skip = (page - 1) * RECORDS_PER_PAGE;
        long numberOfPages = rowsCount / RECORDS_PER_PAGE;
        if (rowsCount % RECORDS_PER_PAGE > 0) {
            numberOfPages++;
        }
        req.setAttribute(Attributes.PAGINATION_PAGE, page);
        req.setAttribute(Attributes.PAGINATION_NUMBER_OF_PAGES, numberOfPages);
        return skip;
    }
}
