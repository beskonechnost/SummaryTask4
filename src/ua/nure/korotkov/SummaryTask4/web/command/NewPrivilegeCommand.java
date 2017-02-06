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
public class NewPrivilegeCommand extends Command {
    private static final long serialVersionUID = 459184654713732864L;

    private static final Logger LOG = Logger.getLogger(NewPrivilegeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {

        LOG.debug("Command starts");
        DBManager manager = DBManager.getInstance();

        int id = Integer.parseInt(request.getParameter("itemId"));
        LOG.trace("Request parameter: id --> " + id);
        int privId = Integer.parseInt(request.getParameter("itemPrivilege"));
        LOG.trace("Request parameter: privilegeId --> " + privId);

        if (id == 0) {
            throw new AppException("No parameters");
        }

        if(privId == 2){
            manager.userNewPrivilege(id, 1);
        }else{
            manager.userNewPrivilege(id, 2);
        }



        LOG.debug("Command finished");
        return Path.ADMIN_LIST_USER;
    }
}
