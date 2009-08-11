<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventtypeList.title"/></title>
    <meta name="heading" content="<fmt:message key='eventtypeList.heading'/>"/>
    <meta name="menu" content="EventtypeMenu"/>
</head>
<form id="formulario" action="eventtypes.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="eventtypeform.html?edit=add" title="<fmt:message key="button.add"/>">
			</a>
			<div class="buttonSeparator">
			</div>
			<a id="doneButton" href="mainMenu.html" title="<fmt:message key="button.done"/>">
			</a>

			<a id="filterButton" onclick="this.blur();document.forms['formulario'].submit();" href="#" title="<fmt:message key="form.search"/>">
			</a>
			<input id="subnavfilter" name="subnavfilter" type="edit" value="${param.subnavfilter}" />
		</div>	
		<div class="subnavmenu-right">
	    </div>
    </div>
	<div id="listcontent">
		
		<display:table name="eventtypeList" class="table" defaultsort="1" requestURI="" id="eventtypeList" export="true" pagesize="25" >
            <display:column href="eventtypeform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="eventtype.name"/>
            <display:column property="description" sortable="true" titleKey="eventtype.description"/>

        	<display:setProperty name="paging.banner.item_name"><fmt:message key="eventtypeList.eventtype"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="eventtypeList.eventtypes"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="eventtypeList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="eventtypeList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="eventtypeList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("eventtypeList");
</script> 
</form>
 