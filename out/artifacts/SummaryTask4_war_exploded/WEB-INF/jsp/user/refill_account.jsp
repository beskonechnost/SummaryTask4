<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Refill" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_editions" action="controller">
                <c:set var="account" value="${account}"/>
                <input type="hidden" name="accountId" value="${account.id}" />
                <input type="hidden" name="accountBalance" value="${account.balance}" />
                <input type="hidden" name="command" value="UserRefillForm" />

                <div>
                    <b><fmt:message key="refill.myAccount"></fmt:message>: </b><c:out value="${account.balance}"/>
                </div>

                <div>
                    <p><fmt:message key="refill.massage"></fmt:message></p>
                    <p><fmt:message key="refill.format"></fmt:message></p>
                    <input name="sum">
                </div>
                <input type="submit" value="<fmt:message key="refill.refill"></fmt:message>"><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>