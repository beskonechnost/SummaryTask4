<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Unloked" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<table id="main-container">
    <tr >
        <td class="content center">
            <form id="start_form" action="controller" method="post">
                <input type="hidden" name="command" value="login"/>
                <fieldset >
                    <legend>YOU USER IS LOCKED</legend>
                    <H1><b>Your user is locked.<br/>
                        Contact administrator<br/></b></H1>>
                </fieldset><br/>
                <input type="submit" value="Back">
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>