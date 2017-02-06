<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Users" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_users" action="controller">

                <table id="list_user_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td><fmt:message key="users.Login"></fmt:message></td>
                        <td><fmt:message key="users.Mail"></fmt:message></td>
                        <td><fmt:message key="users.Age"></fmt:message></td>
                        <td><fmt:message key="users.Lock"></fmt:message></td>
                        <td><fmt:message key="users.Privilege"></fmt:message></td>
                    </tr>
                    </thead>

                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${users}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.login}</td>
                            <td>${item.mail}</td>
                            <td>${item.age}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.id == user.id}"><p><fmt:message key="users.thisIsYou"></fmt:message></p></c:when>
                                    <c:when test="${item.lock == 1}">
                                    <form id="make_users" action="controller" method="post">
                                        <input type="hidden" name="itemId" value="${item.id}" />
                                        <input type="hidden" name="itemLocked" value="${item.lock}" />
                                        <input type="hidden" name="command" value="Locked" />
                                        <input type="submit" value="<fmt:message key="users.locked"></fmt:message>">
                                    </form>
                                    </c:when>
                                    <c:when test="${item.lock == 2}">
                                        <form id="make_users" action="controller" method="post">
                                            <input type="hidden" name="itemId" value="${item.id}" />
                                            <input type="hidden" name="itemLocked" value="${item.lock}" />
                                            <input type="hidden" name="command" value="Locked" />
                                            <input type="submit" value="<fmt:message key="users.unlocked"></fmt:message>">
                                        </form>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.id == user.id}"><p><fmt:message key="users.thisIsYou"></fmt:message></p></c:when>
                                    <c:when test="${item.privilegeId == 1}">
                                        <form id="make_users" action="controller" method="post">
                                            <input type="hidden" name="itemId" value="${item.id}" />
                                            <input type="hidden" name="itemPrivilege" value="${item.privilegeId}" />
                                            <input type="hidden" name="command" value="NewPrivilege" />
                                            <input type="submit" value="<fmt:message key="users.makeUser"></fmt:message>">
                                        </form>
                                    </c:when>
                                    <c:when test="${item.privilegeId == 2}">
                                        <form id="make_users" action="controller" method="post">
                                            <input type="hidden" name="itemId" value="${item.id}" />
                                            <input type="hidden" name="itemPrivilege" value="${item.privilegeId}" />
                                            <input type="hidden" name="command" value="NewPrivilege" />
                                            <input type="submit" value="<fmt:message key="users.makeAdmin"></fmt:message>">
                                        </form>
                                    </c:when>
                                </c:choose>
                            </td>
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