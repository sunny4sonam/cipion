<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="countryList.title"/></title>
    <meta name="heading" content="<fmt:message key='countryList.heading'/>"/>
    <meta name="menu" content="CountryMenu"/>
</head>
<form id="formulario" action="/countrys.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/countryform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="countryList" class="table" defaultsort="1" requestURI="" id="countryList" export="true" pagesize="25" >
            <display:column href="countryform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="country.name"/>
            <display:column sortable="false" titleKey="country.flag" media="html">
				<img src="getimage.html?sid=${countryList.sid}&manager=countryManager&pojo=Country&field=flag" style="border-width: 1px;border-color: #AAAAAA;border-style: dotted;"/>
			</display:column>
			
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="countryList.country"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="countryList.countries"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="countryList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="countryList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="countryList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
    highlightTableRows("countryList");
</script> 
</form>
