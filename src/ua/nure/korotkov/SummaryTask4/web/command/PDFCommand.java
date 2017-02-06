package ua.nure.korotkov.SummaryTask4.web.command;

import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import ua.nure.korotkov.SummaryTask4.Path;
import ua.nure.korotkov.SummaryTask4.db.DBManager;
import ua.nure.korotkov.SummaryTask4.db.entity.Edition;
import ua.nure.korotkov.SummaryTask4.exception.AppException;
import ua.nure.korotkov.SummaryTask4.web.forMail.MyPDF;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 25.01.2017.
 */
public class PDFCommand extends Command {

    private static final long serialVersionUID = 6274286664273452532L;

    private static final Logger LOG = Logger.getLogger(PDFCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("Command starts");
        List<Edition> editions = DBManager.getInstance().findEditions();

        try {
            MyPDF.createPDFEdition("Editions", editions);
        } catch (DocumentException e) {
            throw new AppException("Cannot create PDF file");
        }

        String forward = Path.ADMIN_LIST_EDITION;
        LOG.debug("Command finished");
        return forward;
    }
}
