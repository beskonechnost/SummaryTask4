package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Андрей on 21.01.2017.
 */
public class DeleteEditionCommand extends Command {
    private static final long serialVersionUID = -68558465161363287L;

    private static final Logger LOG = Logger.getLogger(DeleteEditionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();
        int id = Integer.parseInt(request.getParameter("itemId"));
        LOG.trace("Found in DB: id -----------> " + id);
        manager.deleteEditionById(id);


        LOG.debug("Command finished");
        return Path.ADMIN_LIST_EDITION;
    }

}
