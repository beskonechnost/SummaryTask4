<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="My subscriptions" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_editions" action="controller">

                <table id="list_edition_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td><fmt:message key="subscribe.loginUser"></fmt:message></td>
                        <td><fmt:message key="tableEditions.name"></fmt:message></td>
                        <td><fmt:message key="tableEditions.topic"></fmt:message></td>
                        <td><fmt:message key="tableEditions.price"></fmt:message></td>
                        <td><fmt:message key="tableEdition.publisher"></fmt:message></td>
                    </tr>
                    </thead>

                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${subscriptions}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.loginUser}</td>
                            <td>${item.editionName}</td>
                            <td>${item.editionTopic}</td>
                            <td>${item.editionPrice}</td>
                            <td>${item.editionPublisher}</td>
                        </tr>
                    </c:forEach>
                </table>

            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>