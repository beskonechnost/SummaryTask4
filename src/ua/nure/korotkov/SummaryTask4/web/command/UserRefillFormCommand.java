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
 * Created by Андрей on 21.01.2017.
 */
public class UserRefillFormCommand extends Command {
    private static final long serialVersionUID = 6588218252127837534L;

    private static final Logger LOG = Logger.getLogger(UserRefillFormCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        int idAccount = Integer.parseInt(request.getParameter("accountId"));
        double balanceAccount = Double.parseDouble(request.getParameter("accountBalance"));

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String sum = request.getParameter("sum");
        if(sum==null || sum.isEmpty()){
            LOG.debug("Command finished");
            return Path.USER_ACCOUNT;
        }else{
            double sum1 = Double.parseDouble(decimalFormat.format(Double.parseDouble(sum)));
            if(sum1<0 || sum1>10000){
                throw new AppException("amount can not be negative or greater than 10000");
            }
            balanceAccount+=sum1;
            LOG.debug("idAccount ---> " + idAccount);
            LOG.debug("balanceAccount ---> " + balanceAccount);
            DBManager manager = DBManager.getInstance();
            manager.refillAccountById(idAccount, balanceAccount);


            LOG.debug("Command finished");
            return Path.USER_ACCOUNT;
        }
    }
}
