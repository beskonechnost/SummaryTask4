<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Sign on" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="settings_form" action="controller" method="post">
                <input type="hidden" name="command" value="singOn" />

                <div>
                    <p>Login*</p>
                    <input name="login">
                </div>

                <div>
                    <p>Password*</p>
                    <input type="password" name="pass">
                </div>

                <div>
                    <p>Mail*</p>
                    <input name="mail">
                </div>

                <div>
                    <p>Age</p>
                    <input name="age">
                </div>

                <div>
                    <p>Account number*</p>
                    <p>(Minimum length of 6 digits)</p>
                    <input name="account_number">
                </div>

                <div>
                    <p>Start balance (no more than $ 300)</p>
                    <input name="balance">
                </div>

                <div>
                    <p><b>Fields marked * are required</b></p>
                </div>

                <input type="submit" value="Sing On"><br/>
            </form>

            <%-- CONTENT --%>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>