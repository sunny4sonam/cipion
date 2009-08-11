<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="rangecalificationList.title"/></title>
    <meta name="heading" content="<fmt:message key='rangecalificationList.heading'/>"/>
    <meta name="menu" content="RangecalificationMenu"/>
</head>
<form id="formulario" action="rangecalifications.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="rangecalificationform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="rangecalificationList" class="table" defaultsort="1" requestURI="" id="rangecalificationList" export="true" pagesize="25" >
            <display:column href="rangecalificationform.html?edit=false" paramId="sid" paramProperty="sid" property="calification.formLabelField" sortable="true" titleKey="rangecalification.calification"/>
            <display:column property="frompoint" sortable="true" titleKey="rangecalification.frompoint"/>
            <display:column property="topoint" sortable="true" titleKey="rangecalification.topoint"/>
            <display:column sortProperty="round" sortable="true" titleKey="rangecalification.round">
                <input type="checkbox" disabled="disabled" <c:if test="${rangecalificationList.round}">checked="checked"</c:if>/>
            </display:column>

			
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="rangecalificationList.rangecalification"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="rangecalificationList.rangecalifications"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="rangecalificationList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="rangecalificationList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="rangecalificationList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("rangecalificationList");
</script> 
</form>
 