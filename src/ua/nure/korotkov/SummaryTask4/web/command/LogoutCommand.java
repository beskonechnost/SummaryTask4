package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Андрей on 19.01.2017.
 */
public class LogoutCommand extends Command {
    private static final long serialVersionUID = -275884669617653867L;

    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }
}
