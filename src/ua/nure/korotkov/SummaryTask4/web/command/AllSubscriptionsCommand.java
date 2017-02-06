package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.SubscriptionAll;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 21.01.2017.
 */
public class AllSubscriptionsCommand extends Command {
    private static final long serialVersionUID = 2841286452286841135L;

    private static final Logger LOG = Logger.getLogger(AllSubscriptionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<SubscriptionAll> subscriptions = DBManager.getInstance().findSubscriptions();
        LOG.trace("Found in DB: subscriptions --> " + subscriptions);

        request.setAttribute("subscriptions", subscriptions);
        LOG.trace("Set the request attribute: users --> " + subscriptions);

        LOG.debug("Command finished");
        return Path.PAGE_ADMIN_SUBSCRIPTIONS_LIST;
    }

}
