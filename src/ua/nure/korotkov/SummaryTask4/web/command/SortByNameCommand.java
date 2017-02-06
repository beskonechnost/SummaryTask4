package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 25.01.2017.
 */
public class SortByNameCommand extends Command {
    private static final long serialVersionUID = 4287136568516718325L;

    private static final Logger LOG = Logger.getLogger(SortByNameCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        List<Edition> editions;
        String sortedS = request.getParameter("sort");
        int sort;
        LOG.trace("Found in DB: sortedS --> " + sortedS);
        if(sortedS==null || sortedS.isEmpty()) {
            sort = 2;
        }else{
            sort = Integer.parseInt(request.getParameter("sort"));
        }
        LOG.trace("Found in DB: sort --> " + sort);

        if(sort==0 || sort%2==0) {
            sort++;
            request.setAttribute("sort", sort);
            editions = DBManager.getInstance().findEditionsSortNameASC();
            LOG.trace("Found in DB: editionsList ASC --> " + editions);
        }else{
            sort++;
            request.setAttribute("sort", sort);
            editions = DBManager.getInstance().findEditionsSortNameDESC();
            LOG.trace("Found in DB: editionsList DESC --> " + editions);
        }

        request.setAttribute("editions", editions);
        LOG.trace("Set the request attribute: editions --> " + editions);

        LOG.debug("Command finished");

        return Path.PAGE_LIST_EDITIONS_FOR_ALL;
    }
}
