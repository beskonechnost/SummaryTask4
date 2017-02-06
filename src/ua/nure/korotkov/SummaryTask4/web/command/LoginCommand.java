package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.Lock;
import ua.nure.korotkov.SummaryTask4.db.Privilege;
import ua.nure.korotkov.SummaryTask4.db.entity.User;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Андрей on 14.01.2017.
 */
public class LoginCommand extends Command {
    private static final long serialVersionUID = 4482536512627677471L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();

        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = manager.findUserByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPass())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Privilege userPrivilege = Privilege.getPrivilege(user);
        Lock userLock = Lock.getLocked(user);
        LOG.trace("userPrivilege --> " + userPrivilege);
        LOG.trace("userLocked --> " + userLock);

        String forward = Path.PAGE_ERROR_PAGE;

        if(userLock == Lock.UNLOCKED){
            forward = Path.PAGE_UNLOCKED_PAGE;
        }

        if(userLock == Lock.LOCKED) {
            if (userPrivilege == Privilege.ADMIN) {
                forward = Path.ADMIN_LIST_EDITION;
                session.setAttribute("userId", user.getId());
                LOG.trace("Set the session attribute: userId --> " + user.getId());

                session.setAttribute("user", user);
                LOG.trace("Set the session attribute: user --> " + user);

                session.setAttribute("userPrivilege", userPrivilege);
                LOG.trace("Set the session attribute: userPrivilege --> " + userPrivilege);

                LOG.info("User " + user + " logged as " + userPrivilege.toString().toLowerCase());

            }

            if (userPrivilege == Privilege.USER) {
                session.setAttribute("userId", user.getId());
                LOG.trace("Set the session attribute: userId --> " + user.getId());

                session.setAttribute("user", user);
                LOG.trace("Set the session attribute: user --> " + user);

                session.setAttribute("userPrivilege", userPrivilege);
                LOG.trace("Set the session attribute: userPrivilege --> " + userPrivilege);

                LOG.info("User " + user + " logged as " + userPrivilege.toString().toLowerCase());

                forward = Path.USER_LIST_EDITION;
            }
        }

        LOG.debug("Command finished");
        return forward;
    }
}
