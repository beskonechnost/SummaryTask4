package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Андрей on 18.01.2017.
 */
public class ViewSettingsCommand extends Command {

    private static final long serialVersionUID = 1884536544229396875L;

    private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.debug("Command starts");

        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
