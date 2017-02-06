package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.SubscriptionAll;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 21.01.2017.
 */
public class UserSubscriptionsCommand extends Command {
    private static final long serialVersionUID = 3581218252646837134L;

    private static final Logger LOG = Logger.getLogger(AllSubscriptionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession ses = request.getSession();
        int userId = (Integer)ses.getAttribute("userId");
        List<SubscriptionAll> subscriptions = DBManager.getInstance().findSubscriptionsThisUser(userId);
        LOG.trace("Found in DB: subscriptions --> " + subscriptions);

        request.setAttribute("subscriptions", subscriptions);
        LOG.trace("Set the request attribute: subscriptions --> " + subscriptions);

        LOG.debug("Command finished");
        return Path.PAGE_USER_SUBSCRIPTIONS_LIST;
    }

}
