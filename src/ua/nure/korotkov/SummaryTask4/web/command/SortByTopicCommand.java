package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Андрей on 22.01.2017.
 */
public class SortByTopicCommand extends Command {
    private static final long serialVersionUID = 1585136512516723371L;

    private static final Logger LOG = Logger.getLogger(SortByTopicCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        request.setAttribute("sort", "topic");



        HttpSession session = request.getSession();
        String priv = String.valueOf(session.getAttribute("userPrivilege"));

        String forward;
        if(priv.isEmpty()||priv==null){
            forward = Path.ALL_LIST_EDITION;
        }else {
            if(priv.equals("USER")) {
                forward = Path.USER_LIST_EDITION;
            }else{
                if(priv.equals("ADMIN")) {
                    forward = Path.ADMIN_LIST_EDITION;
                }else{
                    forward = Path.ALL_LIST_EDITION;
                }
            }
        }

        LOG.debug("Command finished");
        return forward;
    }
}
