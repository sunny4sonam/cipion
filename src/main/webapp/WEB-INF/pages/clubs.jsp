<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="clubList.title"/></title>
    <meta name="heading" content="<fmt:message key='clubList.heading'/>"/>
    <meta name="menu" content="ClubMenu"/>
</head>
<form id="formulario" action="/clubs.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/clubform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="clubList" class="table" defaultsort="1" requestURI="" id="clubList" export="true" pagesize="25" >
            <display:column href="clubform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="club.name"/>
			<display:column property="city" sortable="true" titleKey="club.city"/>
            <display:column property="contactperson" sortable="true" titleKey="club.contactperson"/>
            <display:column property="region" sortable="true" titleKey="club.region"/>
	
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="clubList.club"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="clubList.clubs"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="clubList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="clubList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="clubList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("clubList");
</script> 
</form>
