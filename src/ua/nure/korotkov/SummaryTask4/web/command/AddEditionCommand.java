package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.exception.AppException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Андрей on 19.01.2017.
 */
public class AddEditionCommand extends Command {
    private static final long serialVersionUID = 6914536513527661972L;

    private static final Logger LOG = Logger.getLogger(AddEditionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        String name = request.getParameter("name");
        LOG.trace("Request parameter: name --> " + name);
        String topic = request.getParameter("topic");
        String publisher = request.getParameter("publisher");

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double price = Double.parseDouble(decimalFormat.format(Double.parseDouble(request.getParameter("price"))));

        if (name == null || topic == null || publisher == null || name.isEmpty() || topic.isEmpty() || publisher.isEmpty()) {
            throw new AppException("Name/topic/publisher cannot be empty");
        }

        if (name.length()>45 || topic.length()>45 || publisher.length()>45) {
            throw new AppException("Name/topic/publisher cannot be longer than 45 characters");
        }

        if (price<0 || price>10000) {
            throw new AppException("Price can not be negative or greater than 10000");
        }
        DBManager.getInstance().addNewEdition(name, topic, price, publisher);
        LOG.trace("Add in DB: edition --> " + name +"|"+topic+"|"+price+"|"+publisher);

        LOG.debug("Command finished");
        return Path.ADMIN_LIST_EDITION;
    }
}
