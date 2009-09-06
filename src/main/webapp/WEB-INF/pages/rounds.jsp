<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="roundList.title"/></title>
    <meta name="heading" content="<fmt:message key='roundList.heading'/>"/>
    <meta name="menu" content="RoundMenu"/>
</head>

<c:set var="buttons">
	<div class="operaciones">
		<ul>
			<li><a href="<c:url value="roundform.html"/>"><fmt:message key="button.add"/></a></li
			><li><a href="<c:url value="mainMenu.html"/>"/><fmt:message key="button.done"/></a></li>			
		</ul>
    </div>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<display:table name="roundList" class="table" requestURI="" id="roundList" export="false" pagesize="25">
    <display:column property="sid" sortable="true" href="roundform.html" media="html"
        paramId="sid" paramProperty="sid" titleKey="round.sid"/>
    <display:column property="sid" media="csv excel xml pdf" titleKey="round.sid"/>

    <display:column property="description" sortable="true" titleKey="round.description"/>

    <display:column property="event.formLabelField" sortable="true" titleKey="round.event"/>

    <display:column property="number" sortable="true" titleKey="round.number"/>



<%--
	<display:setProperty name="paging.banner.item_name"><fmt:message key="roundList.round"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="roundList.rounds"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="roundList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="roundList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="roundList.title"/>.pdf</display:setProperty> 
--%>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("roundList");
</script> 