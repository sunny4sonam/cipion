<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="categoryList.title"/></title>
    <meta name="heading" content="<fmt:message key='categoryList.heading'/>"/>
    <meta name="menu" content="CategoryMenu"/>
</head>
<form id="formulario" action="/categorys.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/categoryform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="categoryList" class="table" defaultsort="1" requestURI="" id="categoryList" export="true" pagesize="25" >
            <display:column href="categoryform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="category.name"/>
            <display:column property="description" sortable="true" titleKey="category.description"/>

        	<display:setProperty name="paging.banner.item_name"><fmt:message key="categoryList.category"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="categoryList.categories"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="categoryList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="categoryList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="categoryList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("categoryList");
</script> 
</form>
