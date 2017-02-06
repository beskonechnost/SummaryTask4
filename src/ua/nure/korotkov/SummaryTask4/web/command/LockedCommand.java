package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Андрей on 19.01.2017.
 */
public class LockedCommand extends Command {
    private static final long serialVersionUID = -358884651613753862L;

    private static final Logger LOG = Logger.getLogger(LockedCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();

        int id = Integer.parseInt(request.getParameter("itemId"));
        LOG.trace("Request parameter: id --> " + id);
        int lock = Integer.parseInt(request.getParameter("itemLocked"));
        LOG.trace("Request parameter: lock --> " + lock);
        if (id == 0) {
            throw new AppException("111");
        }

        if(lock == 2){
            manager.userLocked(id, 1);
        }else{
            manager.userLocked(id, 2);
        }



        LOG.debug("Command finished");
        return Path.ADMIN_LIST_USER;
    }
}
