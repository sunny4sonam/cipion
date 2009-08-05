<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventList.title"/></title>
    <meta name="heading" content="<fmt:message key='eventList.heading'/>"/>
    <meta name="menu" content="EventMenu"/>
</head>

<form id="formulario" action="/events.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/eventform.html?edit=add" title="<fmt:message key="button.add"/>">
			</a>
			<div class="buttonSeparator">
			</div>
			<a id="doneButton" href="/mainMenu.html" title="<fmt:message key="button.done"/>">
			</a>

			<a id="filterButton" onclick="this.blur();document.forms['formulario'].submit();" href="#" title="<fmt:message key="form.search"/>">
			</a>
			<input id="subnavfilter" name="subnavfilter" type="edit" value="${param.subnavfilter}" />
		</div>	
		<div class="subnavmenu-right">
	    </div>
    </div>
	<div id="listcontent">
		
		<display:table name="eventList" class="table" defaultsort="1" requestURI="" id="eventList" export="true" pagesize="25" >
			<display:column property="name" sortable="true" titleKey="event.name" href="eventform.html?edit=false" paramId="sid" paramProperty="sid"/>
			<display:column property="eventtype.formLabelField" sortable="true" titleKey="event.eventtype"/>
            <display:column sortProperty="startDate" sortable="true" titleKey="event.startDate">
                 <fmt:formatDate value="${eventList.startDate}" pattern="${datePattern}"/>
            </display:column>
			<display:column property="judge.formLabelField" sortable="true" titleKey="event.judge"/>

	
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="eventList.event"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="eventList.events"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="eventList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="eventList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="eventList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("eventList");
</script> 
</form>
 