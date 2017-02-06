package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Андрей on 19.01.2017.
 */
public class UpdateEditionCommand extends Command {
    private static final long serialVersionUID = 6914536513527661972L;

    private static final Logger LOG = Logger.getLogger(UpdateEditionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        int id = Integer.parseInt(request.getParameter("itemId"));
        LOG.trace("Found in DB: id --> " + id);

        String oldId = request.getParameter("itemId");
        String oldName = request.getParameter("itemName");
        String oldTopic = request.getParameter("itemTopic");
        String oldPrice = request.getParameter("itemPrice");
        String oldPublisher = request.getParameter("itemPublisher");
        request.setAttribute("oldId", oldId);
        request.setAttribute("oldName", oldName);
        request.setAttribute("oldTopic", oldTopic);
        request.setAttribute("oldPrice", oldPrice);
        request.setAttribute("oldPublisher", oldPublisher);

        LOG.debug("Command finished");
        return Path.UPDATE_EDITION;
    }
}
