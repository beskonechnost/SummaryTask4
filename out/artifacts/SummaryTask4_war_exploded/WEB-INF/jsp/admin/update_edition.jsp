<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Update edition" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="updateEditionForm" />
                <input type="hidden" name="upId" value="${requestScope.get("oldId")}" />
                <input type="hidden" name="upName" value="${requestScope.get("oldName")}" />
                <input type="hidden" name="upTopic" value="${requestScope.get("oldTopic")}" />
                <input type="hidden" name="upPrice" value="${requestScope.get("oldPrice")}" />
                <input type="hidden" name="upPublisher" value="${requestScope.get("oldPublisher")}" />

                <div>
                    <p>Name:</p>
                    <input name="name" value="${requestScope.get("oldName")}">
                </div>

                <div>
                    <p>Topic:</p>
                    <input name="topic"  value="${requestScope.get("oldTopic")}">
                </div>

                <div>
                    <p>Price ($):</p>
                    <input name="price"  value="${requestScope.get("oldPrice")}">
                </div>

                <div>
                    <p>Publisher:</p>
                    <input name="publisher" value="${requestScope.get("oldPublisher")}">
                </div>

                <input type="submit" value="Update"><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>