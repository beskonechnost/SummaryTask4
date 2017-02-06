package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Account;
import ua.nure.korotkov.SummaryTask4.db.entity.User;
import ua.nure.korotkov.SummaryTask4.exception.AppException;
import ua.nure.korotkov.SummaryTask4.web.forMail.EmailSender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Андрей on 19.01.2017.
 */
public class UpdateSettingsCommand extends Command {
    private static final long serialVersionUID = 6927536568329396275L;

    private static final Logger LOG = Logger.getLogger(ViewSettingsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        LOG.debug("Command starts");

        HttpSession session = request.getSession();
        Integer idUser = (Integer)session.getAttribute("userId");
        LOG.trace("Request parameter: idUser --> " + idUser);

        DBManager manager = DBManager.getInstance();

        String pass = request.getParameter("pass");


        String mail = request.getParameter("mail");
        LOG.trace("Request parameter: mail --> " + mail);

        String ageString = request.getParameter("age");
        LOG.trace("Request parameter: age --> " + ageString);

        String accountNumberString = request.getParameter("account_number");
        LOG.trace("Request parameter: account_number --> " + accountNumberString);

        User user = manager.findUserById(idUser);
        LOG.trace("Request parameter: user --> " + user);
        Account account = manager.findAccountThisUser(idUser);
        LOG.trace("Request parameter: account --> " + account);

        int age;
        int accountNumber;

        if(mail.isEmpty() || mail==null){
            mail = user.getMail();
        }

        if(pass.isEmpty() || pass==null){
            pass = user.getPass();
        }

        if(ageString.isEmpty() || ageString==null){
            age = user.getAge();
        }else{
            age = Integer.parseInt(ageString);
        }

        if(accountNumberString.isEmpty() || accountNumberString==null){
            accountNumber = account.getAccountNumber();
        }else{
            accountNumber = Integer.parseInt(accountNumberString);
        }

        if (pass.length()<3 || pass.length()>45) {
            throw new AppException("The password should be at least 3 and no more than 45 characters");
        }

        if (age<6 || age>100) {
            throw new AppException("Age can not be negative or you have specified age, which does not correspond to reality");
        }

        String domen1 = "[a-zA-Z][a-zA-Z[0-9]\u005F\u002E\u002D]*[a-z||0-9]";
        String domen2 = "([a-z]){2,4}";
        Pattern p = Pattern.compile(domen1 + "@" + domen1 + "\u002E" + domen2);
        Matcher m = p.matcher(mail);
        if(!m.matches()){
            throw new AppException("New mail does not meet the requirements");
        }

        if (accountNumber<100000 || accountNumber>999999999) {
            throw new AppException("Account number does not meet the requirements");
        }

        LOG.trace("Request parameter: user.getId() --> " + user.getId());
        LOG.trace("Request parameter: pass --> " + pass);
        LOG.trace("Request parameter: mail --> " + mail);
        LOG.trace("Request parameter: age --> " + age);
        LOG.trace("Request parameter: accountNumber --> " + accountNumber);
        manager.UpdateUser(user.getId(), pass, mail, age, accountNumber);

        String text = "Hello, "+user.getLogin()+"! You update settings! You new password "+pass+", account number you entered => "+accountNumber+"!";
        LOG.trace("Request parameter: text --> " + text);


        EmailSender em = new EmailSender("myperiodicals9@gmail.com","My741852");
        LOG.trace("Request parameter: EmailSender --> " + em);

        em.send("You update settings!", text, Path.mail, mail);


        LOG.debug("Command finished");
        return Path.PAGE_SETTINGS;
    }
}
