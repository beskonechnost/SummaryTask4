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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Андрей on 18.01.2017.
 */
public class AdminListEditionsCommand extends Command{
    private static final long serialVersionUID = 1763286237178269925L;

    private static final Logger LOG = Logger.getLogger(AdminListEditionsCommand.class);

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
        LOG.trace("Found in DB: editionsList --> " + editions);

        String sort = String.valueOf(request.getAttribute("sort"));
        LOG.trace("Found in DB: sort --> " + sort);

        if(sort==null||sort.isEmpty()) {
            sort = "id";
        }

        if (sort.equals("price")) {
            Collections.sort(editions, new Comparator<Edition>() {
                public int compare(Edition o1, Edition o2) {
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
            Collections.sort(editions, new Comparator<Edition>() {
                public int compare(Edition o1, Edition o2) {
                    return o1.getTopic().compareTo(o2.getTopic());
                }
            });
        }

        if (sort.equals("id")) {
            Collections.sort(editions, new Comparator<Edition>() {
                public int compare(Edition o1, Edition o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });
        }

        List<String> topics = new ArrayList<String>();
        for(Edition edit : editions){
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

        request.setAttribute("editions", editions);
        LOG.trace("Set the request attribute: editions --> " + editions);

        LOG.debug("Command finished");
        return Path.ADMIN_LIST_EDITIONS_PAGE;
    }
}
