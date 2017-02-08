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
                        <b>Choose edition by topic:</b>
                        <select name="topicEdition">
                            <option/></option>
                            <c:set var="k1" value="0"/>
                            <c:forEach var="top" items="${topics}">
                                <c:set var="k1" value="${k1+1}"/>
                                <option value="<c:out value="${top}"/>"><c:out value="${top}"/></option>
                            </c:forEach>
                            <input type="hidden" name="topicEdition" value="${topicEdition}" />
                            <input type="hidden" name="command" value="TotalListEditions" />
                            <input type="submit" value="Search by topic">
                        </select>
                    </form>
                </div>

                <div>
                    <form action="controller" method="post">
                        <b>Choose edition by name:</b>
                        <input name="searchName">
                        <input type="hidden" name="command" value="TotalListEditions" />
                        <input type="submit" value="Search by name">
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
                                <input type="submit" value="Name"><br/>
                            </form>
                        </td>
                        <td>
                            <a href="controller?command=SortByTopic">Topic</a>
                        </td>
                        <td>
                            <a href="controller?command=SortByPrice">Price</a>
                        </td>
                        <td>Publisher</td>
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