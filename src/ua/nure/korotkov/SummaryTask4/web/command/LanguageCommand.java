package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Андрей on 22.01.2017.
 */
public class LanguageCommand extends Command {
    private static final long serialVersionUID = -346824651611253847L;

    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");
        LOG.debug("Command finished");
        return Path.LANGUAGE;
    }
}
