<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="dogList.title"/></title>
    <meta name="heading" content="<fmt:message key='dogList.heading'/>"/>
    <meta name="menu" content="DogMenu"/>
</head>

<form id="formulario" action="dogs.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="dogform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="dogList" class="table" defaultsort="1" requestURI="" id="dogList" export="true" pagesize="25" >
            <display:column property="guide.club.name" sortable="true" titleKey="clubList.club" href="dogform.html?edit=false" paramId="sid" paramProperty="sid" />
            <display:column property="guide.firstname" sortable="true" titleKey="guideList.guide"/>
			<display:column property="alias" sortable="true" titleKey="dog.alias"/>
			<display:column property="breed.formLabelField" sortable="true" titleKey="dog.breed"/>
			<display:column property="color" sortable="true" titleKey="dog.color"/>
	
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="dogList.dog"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="dogList.dogs"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="dogList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="dogList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="dogList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("dogList");
</script> 
</form>
 