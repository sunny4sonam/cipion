<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="breedList.title"/></title>
    <meta name="heading" content="<fmt:message key='breedList.heading'/>"/>
    <meta name="menu" content="BreedMenu"/>
</head>
<form id="formulario" action="/breeds.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<a id="addButton" href="/breedform.html?edit=add" title="<fmt:message key="button.add"/>">
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
		
		<display:table name="breedList" class="table" defaultsort="1" requestURI="" id="breedList" export="true" pagesize="25" >
            <display:column href="breedform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="breed.name"/>
            <display:column property="description" sortable="true" titleKey="breed.description"/>
			<display:column titleKey="Wiki - Google" media="html" href="#">
					<a onclick="javascript:openWindows('${breedList.name}');return false;" ><div class="wikipediacolumn" ></div></a>
            </display:column>

        	<display:setProperty name="paging.banner.item_name"><fmt:message key="breedList.breed"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="breedList.breeds"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="breedList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="breedList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="breedList.title"/>.pdf</display:setProperty> 
		</display:table>
	</div>

<script type="text/javascript">
	//lo comento para que en explorer salga la opción de las ventanas emergentes, y qu eel usuario pueda habilitarlas
	//sino, el usuario no puede ver todas las ventanas que se abren en openWindows
    //highlightTableRows("breedList");
	
	function openWindows(breedName)
    {
          //alert('hola');
          //window.open("http://www.wikipedia.com/")
          //window.open("http://www.java2s.com/")	
		  
          window.open("http://en.wikipedia.org/wiki/" + breedName)
          window.open("http://www.google.com/search?hl=es&q=" + breedName)
          window.open("http://images.google.com/images?q=" + breedName)
		  window.open("<fmt:message key="button.wikipedia.baseurl"/>/"+breedName)
    }
</script> 
</form>