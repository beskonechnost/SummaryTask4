package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.Privilege;
import ua.nure.korotkov.SummaryTask4.db.entity.User;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Андрей on 17.01.2017.
 */
public class StartCommand extends Command {
    private static final long serialVersionUID = 6855136512511623371L;

    private static final Logger LOG = Logger.getLogger(StartCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String forward = Path.ALL_LIST_EDITION;

        LOG.debug("Command finished");
        return forward;
    }
}
