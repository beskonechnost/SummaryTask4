<%@ include file="/WEB-INF/jspf/head.jspf"%>

<fmt:setLocale value="${param.locale}" scope="session"/>
<fmt:setBundle basename="resourses"/>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<jsp:forward page="/WEB-INF/jsp/all/settings.jsp"/>