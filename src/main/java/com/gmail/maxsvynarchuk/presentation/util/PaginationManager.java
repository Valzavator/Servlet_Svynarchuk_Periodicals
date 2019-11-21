package com.gmail.maxsvynarchuk.presentation.util;

import com.gmail.maxsvynarchuk.presentation.util.constants.Attributes;
import com.gmail.maxsvynarchuk.presentation.util.constants.RequestParameters;

import javax.print.attribute.standard.Fidelity;
import javax.servlet.http.HttpServletRequest;

/**
 * Class that is needed to carry information for views that do objects pagination
 *
 * @author Maksym Svynarchuk
 */
public class PaginationManager {
    public static final int DEFAULT_RECORDS_PER_PAGE = 10;
    public static final int CATALOG_RECORDS_PER_PAGE = 5;

    private static final int FIRST_PAGE = 1;

    public static long manage(HttpServletRequest req, long rowsCount, int recordsPerPage) {
        String pageStr = req.getParameter(RequestParameters.PAGINATION_PAGE);

        long numberOfPages = rowsCount / recordsPerPage;
        if (rowsCount % recordsPerPage > 0) {
            numberOfPages++;
        }
        long page;
        try {
            page = Integer.parseInt(pageStr);
        } catch (NumberFormatException ex) {
            page = FIRST_PAGE;
        }
        if (page <= 0) {
            page = FIRST_PAGE;
        }
        if (numberOfPages > 0 && page > numberOfPages) {
            page = numberOfPages;
        }
        long skip = (page - 1) * recordsPerPage;
        req.setAttribute(Attributes.PAGINATION_PAGE, page);
        req.setAttribute(Attributes.PAGINATION_NUMBER_OF_PAGES, numberOfPages);
        return skip;
    }

    public static long manage(HttpServletRequest req, long rowsCount) {
        return manage(req, rowsCount, DEFAULT_RECORDS_PER_PAGE);
    }
}
