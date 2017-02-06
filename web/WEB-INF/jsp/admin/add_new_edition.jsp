<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Add edition" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="addEdition" />

                <div>
                    <p><fmt:message key="addEdition.name"></fmt:message>:</p>
                    <input name="name">
                </div>

                <div>
                    <p><fmt:message key="addEdition.topic"></fmt:message>:</p>
                    <input name="topic">
                </div>

                <div>
                    <p><fmt:message key="addEdition.price"></fmt:message>:</p>
                    <input name="price">
                </div>

                <div>
                    <p><fmt:message key="addEdition.publisher"></fmt:message>:</p>
                    <input name="publisher">
                </div>

                <input type="submit" value="<fmt:message key="addEdition.add"></fmt:message>"><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>