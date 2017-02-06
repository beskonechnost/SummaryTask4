package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.User;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Андрей on 18.01.2017.
 */
public class AllUsersCommand extends Command {

    private static final long serialVersionUID = 1584286692273842538L;

    private static final Logger LOG = Logger.getLogger(AllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<User> users = DBManager.getInstance().findUsers();
        LOG.trace("Found in DB: usersList --> " + users);

        Collections.sort(users, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });

        request.setAttribute("users", users);
        LOG.trace("Set the request attribute: users --> " + users);

        LOG.debug("Command finished");
        return Path.PAGE_USERS_LIST;
    }
}
