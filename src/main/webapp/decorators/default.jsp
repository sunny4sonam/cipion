<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
		<!--[if lt IE 7]>
			<link rel="stylesheet" type="text/css" href="/include/ie6.css" media="screen"/>
		<![endif]-->
        <%@ include file="/common/meta.jsp" %>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>

        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />

        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
        <decorator:head/>
    </head>
<body<decorator:getProperty property="body.id" writeEntireProperty="true"/><decorator:getProperty property="body.class" writeEntireProperty="true"/>>

    <div id="page">
        <div id="header" class="clearfix">
            <jsp:include page="/common/header.jsp"/>
        </div>


        <div id="content" class="clearfix">
	        <div id="navvertical">
                    <jsp:include page="/common/menu.jsp"/>
	        </div>
            <div id="main">
                <%@ include file="/common/messages.jsp" %>
                <jsp:include page="/common/menuhorizontal.jsp"/>
                <decorator:body/>
		        <div id="footer" class="clearfix">
		            <jsp:include page="/common/footer.jsp"/>
		        </div>
            </div>

            <c:set var="currentMenu" scope="request"><decorator:getProperty property="meta.menu"/></c:set>
            <c:if test="${currentMenu == 'AdminMenu'}">
	            <div id="sub">
	                <menu:useMenuDisplayer name="Velocity" config="cssVerticalMenu.vm" permissions="rolesAdapter">
	                    <menu:displayMenu name="AdminMenu"/>
	                </menu:useMenuDisplayer>
	            </div>
            </c:if>

        </div>

    </div>
</body>
</html>
