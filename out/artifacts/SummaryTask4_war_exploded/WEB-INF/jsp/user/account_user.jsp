<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="My Account" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_editions" action="controller">
                <c:set var="account" value="${account}"/>
                <div>
                    <b><fmt:message key="account.number"></fmt:message>: </b><c:out value="${account.accountNumber}"/>
                </div>

                <div>
                    <b><fmt:message key="account.balance"></fmt:message>: </b><c:out value="${account.balance}"/>
                </div>

            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>