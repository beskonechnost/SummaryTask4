package ua.nure.korotkov.SummaryTask4.web.forMail;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Андрей on 25.01.2017.
 */
public class Foo extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print( "Periodicals (SummaryTask4, EPAM-KNURE Java Training, Korotkov), 2017" );
        } catch(IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }
}
