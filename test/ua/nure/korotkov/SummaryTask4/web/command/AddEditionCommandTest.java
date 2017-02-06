package ua.nure.korotkov.SummaryTask4.web.command;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEditionCommandTest extends Mockito{

    @Test
    public void testExecute() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("nameEdition");
        when(request.getParameter("topic")).thenReturn("topicEdition");
        when(request.getParameter("price")).thenReturn("100");
        when(request.getParameter("publisher")).thenReturn("publisherEdition");

        new AddEditionCommand().execute(request, response);

        verify(request, atLeast(1)).getParameter("name");
        verify(request, atLeast(1)).getParameter("topic");
        verify(request, atLeast(1)).getParameter("price");
        verify(request, atLeast(1)).getParameter("publisher");


    }
}