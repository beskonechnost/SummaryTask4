package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by Андрей on 20.01.2017.
 */
public class UpdateEditionFormCommand extends Command {
    private static final long serialVersionUID = 1497236513545361926L;

    private static final Logger LOG = Logger.getLogger(UpdateEditionFormCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        int upId = Integer.parseInt(request.getParameter("upId"));
        LOG.trace("Found in DB: upId -----------> " + upId);
        Edition edition = new Edition();
        edition.setId(upId);
        edition.setName(request.getParameter("upName"));
        LOG.trace("Found in DB: upName -----------> " + edition.getName());
        edition.setTopic(request.getParameter("upTopic"));
        LOG.trace("Found in DB: upTopic -----------> " + edition.getTopic());
        edition.setPrice(Double.parseDouble(request.getParameter("upPrice")));
        LOG.trace("Found in DB: upPrice -----------> " + edition.getPrice());
        edition.setPublisher(request.getParameter("upPublisher"));
        LOG.trace("Found in DB: upPublisher -----------> " + edition.getPublisher());

        boolean flag = false;
        String name = request.getParameter("name");
        LOG.trace("Found in DB: name -----------> " + name);
        if(!name.equals(edition.getName())){
            if(!(name==null)&&!name.isEmpty()) {
                edition.setName(name);
                flag = true;
            }
        }

        String topic = request.getParameter("topic");
        LOG.trace("Found in DB: topic -----------> " + topic);
        if(!topic.equals(edition.getTopic())){
            if(!(topic==null)&&!topic.isEmpty()) {
                edition.setTopic(topic);
                flag = true;
            }
        }

        String publisher = request.getParameter("publisher");
        LOG.trace("Found in DB: publisher -----------> " + publisher);
        if(!publisher.equals(edition.getPublisher())){
            if(!(publisher==null)&&!publisher.isEmpty()) {
                edition.setPublisher(publisher);
                flag = true;
            }
        }

        Locale locale = new Locale("en");
        Locale.setDefault(locale);
        String pattern = "##0.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String price = request.getParameter("price");
        LOG.trace("Found in DB: price -----------> " + price);
        LOG.trace("Found in DB: (price.isEmpty()) -----------> " + (price.isEmpty()));

        if (price.isEmpty()) {
        }else{
            double p = Double.parseDouble(decimalFormat.format(Double.parseDouble(price)));
            LOG.trace("Found in DB: p -----------> " + p);
            if (p<0 || p>10000) {
                throw new AppException("Price can not be negative or greater than 10000");
            }
            if(p==edition.getPrice()){
            }else {
                edition.setPrice(p);
                flag = true;
            }
        }


        if (name.length()>45 || topic.length()>45 || publisher.length()>45) {
            throw new AppException("Name/topic/publisher cannot be longer than 45 characters");
        }


        if(flag){
            DBManager manager = DBManager.getInstance();
            manager.updateEdition(edition.getId(), edition.getName(), edition.getTopic(),edition.getPrice(),edition.getPublisher());
            LOG.debug("Command finished");
            return Path.ADMIN_LIST_EDITION;
        } else {
            LOG.debug("Command finished");
            return Path.ADMIN_LIST_EDITION;
        }
    }
}
