<%@ include file="taglibs.jsp"%>


<div id="branding">
	<div id="appcipionlogo">
	</div>
    <c:if test="${pageContext.request.locale.language != 'en'}">
        <div id="switchLocale"><a href="<c:url value='/?locale=en'/>"><fmt:message key="webapp.name"/> in English</a></div>
    </c:if>
    <c:if test="${pageContext.request.locale.language != 'es'}">
        <div id="switchLocale"><a href="<c:url value='/?locale=es'/>"><fmt:message key="webapp.name"/> en Español</a></div>
    </c:if>
</div>

<div id="headerimg">
</div>

<hr />

<%-- Put constants into request scope --%>
<appfuse:constants scope="request"/>