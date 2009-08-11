<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="judgeList.title"/></title>
    <meta name="heading" content="<fmt:message key='judgeList.heading'/>"/>
    <meta name="menu" content="JudgeMenu"/>
</head>
<form id="formulario" action="judges.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="judgeform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="judgeList" class="table" defaultsort="1" requestURI="" id="judgeList" export="true" pagesize="25" >
            <display:column href="judgeform.html?edit=false" paramId="sid" paramProperty="sid" property="firstname" sortable="true" titleKey="judge.firstname"/>
            <display:column property="lastname" sortable="true" titleKey="judge.lastname"/>
            <display:column property="idnumber" sortable="true" titleKey="judge.idnumber"/>
            <display:column property="license" sortable="true" titleKey="judge.license"/>
			
			
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="judgeList.judge"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="judgeList.judges"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="judgeList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="judgeList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="judgeList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("judgeList");
</script> 
</form>
