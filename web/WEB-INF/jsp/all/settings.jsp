<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>
                <form action="controller" method="post">
                    <fmt:message key="settings_jsp.label.set_locale"></fmt:message>:
                    <select name="locale">
                            <c:forEach items="${applicationScope.locales}" var="locale">
                                <c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
                                <option value="${locale.key}" ${selected}>${locale.value}</option>
                            </c:forEach>
                    </select>
                    <input type="hidden" name="command" value="Language" />
                    <input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'></fmt:message>">
                </form>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="updateSettings" />
                <c:set var="user" value="${user}"/>

                <div>
                    <fmt:message key="settings_jsp.label.Password"></fmt:message>:</br>
                    <input type="password" name="pass" value="${user.pass}">
                </div>

                <div>
                    <fmt:message key="settings_jsp.Mail"></fmt:message>:</br>
                    <input name="mail" value="${user.mail}">
                </div>

                <div>
                    <fmt:message key="settings_jsp.Age"></fmt:message>:</br>
                    <input name="age" value="${user.age}">
                </div>

                <div>
                    <fmt:message key="settings_jsp.account"></fmt:message>:</br>
                    <input name="account_number">
                </div>

                <input type="submit" value="<fmt:message key="settings_jsp.submit_Settings"></fmt:message>"><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>