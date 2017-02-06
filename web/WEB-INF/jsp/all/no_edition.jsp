<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Start" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<table id="main-container">
    <tr >
        <td class="content center">
            <form id="start_form" action="controller" method="post">
                <fieldset >
                    <legend>NO EDITION WITH SAME NAME</legend>
                    <H1><b>No edition with the same name!</b></H1>>
                </fieldset><br/>
                <button type="button" name="back" onclick="history.back()">back</button>
            </form>
        </td>
    </tr>
    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>