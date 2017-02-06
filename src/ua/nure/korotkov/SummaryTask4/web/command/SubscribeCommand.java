package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Account;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;
import ua.nure.korotkov.SummaryTask4.db.entity.EditionSub;
import ua.nure.korotkov.SummaryTask4.db.entity.Subscription;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Андрей on 21.01.2017.
 */
public class SubscribeCommand extends Command {
    private static final long serialVersionUID = 3584186265173863165L;

    private static final Logger LOG = Logger.getLogger(SubscribeCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession ses = request.getSession();
        int idUser = (Integer)ses.getAttribute("userId");
        int idEdition = Integer.parseInt(request.getParameter("itemId"));
        double priceEdition = Double.parseDouble(request.getParameter("itemPrice"));

        DBManager manager =DBManager.getInstance();
        Account userAccount = manager.findAccountThisUser(idUser);
        if(userAccount.getBalance()-priceEdition<0.0){
            throw new AppException("I can not sign up, because the account has insufficient funds. Refill your account and try again.");
        }

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double newBalance = Double.parseDouble(decimalFormat.format(userAccount.getBalance()-priceEdition));

        manager.newSubscriptionAndWritingDown(newBalance, idUser, idEdition);

        LOG.debug("Command finished");
        return Path.USER_LIST_EDITION;
    }
}
