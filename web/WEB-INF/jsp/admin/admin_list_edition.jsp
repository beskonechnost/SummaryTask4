<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Editions" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <%-- CONTENT --%>

            <form id="make_editions" action="controller">
                <div>
                    <form action="controller" method="post">
                        <b><fmt:message key="editions.chooseTopic"></fmt:message>:</b>
                        <select name="topicEdition">
                            <option/></option>
                            <c:set var="k1" value="0"/>
                            <c:forEach var="top" items="${topics}">
                                <c:set var="k1" value="${k1+1}"/>
                                <option value="<c:out value="${top}"/>"><c:out value="${top}"/></option>
                            </c:forEach>
                            <input type="hidden" name="topicEdition" value="${topicEdition}" />
                            <input type="hidden" name="command" value="AdminListEditions" />
                            <input type="submit" value="<fmt:message key="editions.buttonTopic"></fmt:message>">
                        </select>
                    </form>
                </div>

                <div>
                    <form action="controller" method="post">
                        <b><fmt:message key="editions.chooseName"></fmt:message>:</b>
                        <input name="searchName">
                        <input type="hidden" name="command" value="AdminListEditions" />
                        <input type="submit" value="<fmt:message key="editions.buttonName"></fmt:message>">
                    </form>
                </div>

                <table id="list_edition_table">
                    <thead>
                    <tr>
                        <td>№</td>
                        <td>
                            <form id="settings_form" action="controller" method="post">
                                <input type="hidden" name="sort" value="${requestScope.get("sort")}" />
                                <input type="hidden" name="command" value="SortByName" />
                                <input type="submit" value="<fmt:message key="tableEditions.name"></fmt:message>"><br/>
                            </form>
                        </td>
                        <td>
                            <a href="controller?command=SortByTopic"><fmt:message key="tableEditions.topic"></fmt:message></a>
                        </td>
                        <td>
                            <a href="controller?command=SortByPrice"><fmt:message key="tableEditions.price"></fmt:message></a>
                        </td>
                        <td><fmt:message key="tableEdition.publisher"></fmt:message></td>
                        <td><fmt:message key="adminEdition.label.update"></fmt:message></td>
                        <td><fmt:message key="adminEdition.label.delete"></fmt:message></td>
                    </tr>
                    </thead>

                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${editions}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td><c:out value="${k}"/></td>
                            <td>${item.name}</td>
                            <td>${item.topic}</td>
                            <td>${item.price}</td>
                            <td>${item.publisher}</td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="itemId" value="${item.id}" />
                                    <input type="hidden" name="itemName" value="${item.name}" />
                                    <input type="hidden" name="itemTopic" value="${item.topic}" />
                                    <input type="hidden" name="itemPrice" value="${item.price}" />
                                    <input type="hidden" name="itemPublisher" value="${item.publisher}" />
                                    <input type="hidden" name="command" value="UpdateEdition" />
                                    <input type="submit" value="<fmt:message key="adminEdition.label.update"></fmt:message>">
                                </form>
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input type="hidden" name="itemId" value="${item.id}" />
                                    <input type="hidden" name="command" value="DeleteEdition" />
                                    <input type="submit" value="<fmt:message key="adminEdition.label.delete"></fmt:message>">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>

                <div>
                    <form action="controller" method="post">
                        <b><fmt:message key="edition.adminPDF"></fmt:message>:</b>
                        <input type="hidden" name="command" value="PDF" />
                        <input type="submit" value="<fmt:message key="edition.admin.button.PDF"></fmt:message>">
                    </form>
                </div>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>