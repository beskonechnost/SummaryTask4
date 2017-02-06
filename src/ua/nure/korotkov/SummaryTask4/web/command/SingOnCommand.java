package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.User;
import ua.nure.korotkov.SummaryTask4.exception.AppException;
import ua.nure.korotkov.SummaryTask4.web.forMail.EmailSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Андрей on 19.01.2017.
 */
public class SingOnCommand extends Command {
    private static final long serialVersionUID = 1714536459311761642L;

    private static final Logger LOG = Logger.getLogger(AddEditionCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");

        DBManager manager = DBManager.getInstance();
        String login = request.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);
        String pass = request.getParameter("pass");
        String mail = request.getParameter("mail");
        int age = Integer.parseInt(request.getParameter("age"));
        int accountNumber = Integer.parseInt(request.getParameter("account_number"));

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        double balance = Double.parseDouble(decimalFormat.format(Double.parseDouble(request.getParameter("balance"))));

        if (login == null || pass == null || mail == null || login.isEmpty() || pass.isEmpty() || mail.isEmpty()) {
            throw new AppException("login/pass/mail cannot be empty");
        }

        if (login.length()>45 || pass.length()>45 || mail.length()>45) {
            throw new AppException("login/pass/mail cannot be longer than 45 characters");
        }

        if (balance<0 || balance>300) {
            throw new AppException("Price can not be negative or greater than 300");
        }

        if (pass.length()<3 || pass.length()>45) {
            throw new AppException("The password should be at least 3 and no more than 45 characters");
        }

        if (age<0 || age>100) {
            throw new AppException("Age can not be negative or you have specified age, which does not correspond to reality");
        }

        if (age==0) {
            age=1;
        }

        String domen1 = "[a-zA-Z][a-zA-Z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
        String domen2 = "([a-z]){2,4}";
        Pattern p = Pattern.compile(domen1 + "@" + domen1 + "\u002E" + domen2);
        Matcher m = p.matcher(mail);
        if(!m.matches()){
            throw new AppException("Mail does not meet the requirements");
        }

        if (accountNumber<100000 || accountNumber>999999999) {
            throw new AppException("Account number does not meet the requirements");
        }

        List<User> users = manager.findUsers();
        for(User user : users){
            if(user.getLogin().equals(login)){
                throw new AppException("A user with the same name already exists.");
            }
        }
        String text = "Welcome! You login => "+login+", you password => "+pass+", account number you entered => "+accountNumber+" and start balance => "+balance+"!";
        LOG.trace("Request parameter: text --> " + text);


        EmailSender em = new EmailSender("myperiodicals9@gmail.com","My741852");
        LOG.trace("Request parameter: EmailSender --> " + em);

        em.send("Welcome to the application periodicals!", text, Path.mail, mail);
        LOG.trace("Request parameter: SEND");

        manager.addNewUserAndHimAccount(login,pass,mail,age,accountNumber,balance);
        LOG.trace("Add in DB: user --> " + login +"|"+pass+"|"+mail+"|"+age+", and account "+accountNumber+" balance = "+balance);

        LOG.debug("Command finished");
        return Path.PAGE_LOGIN;
    }
}
