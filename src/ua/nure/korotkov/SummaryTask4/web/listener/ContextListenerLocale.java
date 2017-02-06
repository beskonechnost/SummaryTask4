package ua.nure.korotkov.SummaryTask4.web.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Андрей on 22.01.2017.
 */
public class ContextListenerLocale implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListenerLocale.class);
    public void contextDestroyed(ServletContextEvent event) {

    }

    public void contextInitialized(ServletContextEvent event) {
        LOG.debug("ContextListenerLocale  contextInitialized");
        ServletContext context = event.getServletContext();
        String localesFileName = context.getInitParameter("locales");

        String localesFileRealPath = context.getRealPath(localesFileName);

        Properties locales = new Properties();
        try {
            locales.load(new FileInputStream(localesFileRealPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.setAttribute("locales", locales);
        locales.list(System.out);
    }
}
