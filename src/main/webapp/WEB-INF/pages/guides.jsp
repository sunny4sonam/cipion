<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="guideList.title"/></title>
    <meta name="heading" content="<fmt:message key='guideList.heading'/>"/>
    <meta name="menu" content="GuideMenu"/>
</head>
<form id="formulario" action="/guides.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/guideform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="guideList" class="table" defaultsort="1" requestURI="" id="guideList" export="true" pagesize="25" >
            <display:column property="club.formLabelField" sortable="true" titleKey="guide.club" href="guideform.html?edit=false" paramId="sid" paramProperty="sid" />
            <display:column property="firstname" sortable="true" titleKey="guide.firstname"/>
            <display:column property="lastname" sortable="true" titleKey="guide.lastname"/>
            <display:column property="idnumber" sortable="true" titleKey="guide.idnumber"/>
	
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="guideList.guide"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="guideList.guides"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="guideList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="guideList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="guideList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("guideList");
</script> 
</form>
 