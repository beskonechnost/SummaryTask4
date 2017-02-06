package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Account;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Андрей on 21.01.2017.
 */
public class UserRefillCommand extends Command{

    private static final long serialVersionUID = 3581218252646837134L;

    private static final Logger LOG = Logger.getLogger(UserRefillCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession ses = request.getSession();
        int userId = (Integer)ses.getAttribute("userId");
        Account account = DBManager.getInstance().findAccountThisUser(userId);
        LOG.trace("Found in DB: account --> " + account);

        request.setAttribute("account", account);
        LOG.trace("Set the request attribute: account --> " + account);

        LOG.debug("Command finished");
        return Path.PAGE_REFIll;
    }

}
