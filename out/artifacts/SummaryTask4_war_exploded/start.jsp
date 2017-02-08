<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Start" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<table id="main-container">
    <tr >
        <td class="content center">
            <form id="start_form" action="controller" method="post">
                <input type="hidden" name="sorted" value="0" />
                <input type="hidden" name="command" value="start"/>
                    <fieldset >
                        <legend>WELCOME</legend>
                        <H1><b>I greet you in the appendix of periodicals.<br/>
                        You can follow any responses to see all publications that are in the app,<br/>
                        but subscribe to or can only registered users.</b></H1>>
                    </fieldset><br/>
                <input type="submit" value="Come in">
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>