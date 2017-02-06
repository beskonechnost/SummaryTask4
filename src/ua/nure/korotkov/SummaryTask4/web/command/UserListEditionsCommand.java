package ua.nure.korotkov.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;
import ua.nure.korotkov.SummaryTask4.db.entity.EditionSub;
import ua.nure.korotkov.SummaryTask4.db.entity.Subscription;
import ua.nure.korotkov.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Андрей on 18.01.2017.
 */
public class UserListEditionsCommand extends Command {
    private static final long serialVersionUID = 7164886269973863325L;

    private static final Logger LOG = Logger.getLogger(UserListEditionsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String topic = request.getParameter("topicEdition");
        String name = request.getParameter("searchName");
        List<Edition> editions;
        if((topic==null || topic.isEmpty())&&(name==null || name.isEmpty())) {
            editions = DBManager.getInstance().findEditions();
        }else{
            if(name==null || name.isEmpty()) {
                editions = DBManager.getInstance().findEditionsByTopic(topic);
            }else{
                editions = DBManager.getInstance().findEditionsByName(name);
                if (editions.isEmpty()) {
                    return Path.NO_EDITION;
                }
            }
        }

        HttpSession ses = request.getSession();
        int id = (Integer) ses.getAttribute("userId");
        List<Subscription> subscriptions = DBManager.getInstance().findSubscriptionsUser(id);
        LOG.trace("Found in DB: subscriptions --> " + subscriptions);

        List<EditionSub> editionsNew = new ArrayList<EditionSub>();
        if(subscriptions.isEmpty()) {
            for (Edition edit : editions) {
                editionsNew.add(new EditionSub(edit));
            }
        }else{
            for (Edition edit : editions) {
                boolean flag = false;
                for (Subscription sub : subscriptions) {
                    if (sub.getEditionId() == edit.getId()) {
                        flag = true;
                    }
                }
                editionsNew.add(new EditionSub(edit, flag));
            }
        }

        String sort = String.valueOf(request.getAttribute("sort"));
        LOG.trace("Found in DB: sort --> " + sort);

        if(sort==null||sort.isEmpty()) {
            sort = "id";
        }

        if (sort.equals("price")) {
            Collections.sort(editionsNew, new Comparator<EditionSub>() {
                public int compare(EditionSub o1, EditionSub o2) {
                    if ((o1.getPrice() - o2.getPrice()) > 0) {
                        return 1;
                    } else {
                            if ((o1.getPrice() - o2.getPrice()) < 0) {
                                return -1;
                            } else {
                                return 0;
                            }
                    }
                }
            });
        }

        if (sort.equals("topic")) {
            Collections.sort(editionsNew, new Comparator<EditionSub>() {
                public int compare(EditionSub o1, EditionSub o2) {
                    return o1.getTopic().compareTo(o2.getTopic());
                }
            });
        }

        if (sort.equals("id")) {
            Collections.sort(editionsNew, new Comparator<EditionSub>() {
                public int compare(EditionSub o1, EditionSub o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });
        }

        List<String> topics = new ArrayList<String>();
        for(EditionSub edit : editionsNew){
            String top = edit.getTopic();
            if(topics.isEmpty()){
                topics.add(top);
            }else {
                boolean flag = true;
                for (String s : topics) {
                    if (s.equals(top)) {
                        flag = false;
                    }
                }
                if(flag){
                    topics.add(top);
                }
            }
        }

        request.setAttribute("topics", topics);
        LOG.trace("Set the request attribute: topics --> " + topics);
        request.setAttribute("editionsNew", editionsNew);
        LOG.trace("Set the request attribute: editionsNew --> " + editionsNew);

        LOG.debug("Command finished");
        return Path.USER_LIST_EDITIONS_PAGE;
    }
}
