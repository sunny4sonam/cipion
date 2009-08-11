<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="guideDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='guideDetail.heading'/>"/>
</head>


<form:form commandName="guide" method="post" action="guideform.html" id="guideForm" onsubmit="return validateGuide(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty guide.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['guideForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty guide.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['guideForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="guide.delete"/>')){document.forms['guideForm'].action='guideform.html?delete=true';document.forms['guideForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="guides.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
			</a>

		</div>	
		<div class="subnavmenu-right">
	    </div>
    </div>

	<div class="formseparation" >
	</div>
	
	<div id="stylized" class="myform" >
		<c:if test="${param.edit=='true'}">
            <h1><font color="red"/><fmt:message key="form.title.edition"/></font></h1>
            <p><fmt:message key="form.subtitle.edition"/></p>
        </c:if>
		<c:if test="${param.edit=='add'}">
            <h1><font color="red"/><fmt:message key="form.title.add"/></font></h1>
            <p><fmt:message key="form.subtitle.add"/></p>
        </c:if>
		<c:if test="${param.edit=='false'}">
			<a id="editButton" href="guideform.html?sid=${guide.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
			<form:hidden path="sid"/>
            <li>
                <form:errors path="picture" cssClass="fieldError"/>
                <label><fmt:message key="guide.picture"/>
                    <span class="small"><fmt:message key="guide.picture.detail"/></span>
                </label>				
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<input type="file" id="picture" name="picture" />
                </c:if>
				<c:if test="${param.edit=='false'}">
					<img src="getimage.html?sid=${guide.sid}&manager=guideManager&pojo=Guide&field=picture"/>
                </c:if>
            </li>
            <li>
				<form:errors path="firstname" cssClass="fieldError"/>
                <label><fmt:message key="guide.firstname"/>
                    <span class="small"><fmt:message key="guide.firstname.detail"/></span>
                </label>				
				<form:input path="firstname" id="firstname" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="lastname" cssClass="fieldError"/>
                <label><fmt:message key="guide.lastname"/>
                    <span class="small"><fmt:message key="guide.lastname.detail"/></span>
                </label>				
				<form:input path="lastname" id="lastname" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="idnumber" cssClass="fieldError"/>
                <label><fmt:message key="guide.idnumber"/>
                    <span class="small"><fmt:message key="guide.idnumber.detail"/></span>
                </label>				
				<form:input path="idnumber" id="idnumber" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="male" cssClass="fieldError"/>
                <label><fmt:message key="guide.male"/>
                    <span class="small"><fmt:message key="guide.male.detail"/></span>
                </label>				
				<form:checkbox path="male" id="male" cssClass="formcheck"/>
            </li>
             <li>
        		 <form:errors path="club" cssClass="fieldError"/>
                 <label><fmt:message key="guide.club"/>
					 <span class="small"><fmt:message key="guide.club.detail"/></span>
                 </label>				
				<c:if test="${param.edit=='false'}">
					<form:input path="club.formLabelField" id="clublabel" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<c:if test="${empty param.clubsid}">
                	     <form:select path="club" id="club" cssClass="select">
                	     	<c:if test="${!empty clubList}">
                     			<option value=""><fmt:message key="club.pickone"/></option>
                	        	<form:options items="${clubList}" itemValue="formIdField" itemLabel="formLabelField" />
                	     	</c:if>
                	     	<c:if test="${empty clubList}">
                	     		<option value=""><fmt:message key="club.noonetopick"/></option>
                	     	</c:if>
                	     </form:select>
                    </c:if>
					<c:if test="${! (empty param.clubsid)}">
						<input type="hidden" name="club" id="club" value="${param.clubsid}"/>
						<form:input path="club.formLabelField" id="clublabel" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                    </c:if>
                </c:if>
             </li>
        </ul>
	</div>
	<c:if test="${not empty guide.sid}">
    	<div id="listcontent">
			<a id="addButton" href="dogform.html?edit=add&guidesid=${guide.sid}" title="<fmt:message key="button.add"/>">
			</a>
    		<div id="listDetailTitle"><fmt:message key="guide.listdetailtitle"/></div>
    
    		<display:table name="guide.dogs" class="table" defaultsort="1" requestURI="" id="dogList" export="true" pagesize="25" >
                <display:column href="dogform.html?edit=false" paramId="sid" paramProperty="sid" property="name" sortable="true" titleKey="dog.name"/>
                <display:column property="breed.formLabelField" sortable="true" titleKey="dog.breed"/>
    			<display:column property="color" sortable="true" titleKey="dog.color"/>
                <display:column sortProperty="male" sortable="true" titleKey="dog.male">
                    <input type="checkbox" disabled="disabled" <c:if test="${dogList.male}">checked="checked"</c:if>/>
                </display:column>
    			<display:column property="category.formLabelField" sortable="true" titleKey="dog.category"/>
    	
            	<display:setProperty name="paging.banner.item_name"><fmt:message key="dogList.dog"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="dogList.dogs"/></display:setProperty>
                <display:setProperty name="export.excel.filename"><fmt:message key="dogList.title"/>.xls</display:setProperty>
                <display:setProperty name="export.csv.filename"><fmt:message key="dogList.title"/>.csv</display:setProperty>
                <display:setProperty name="export.pdf.filename"><fmt:message key="dogList.title"/>.pdf</display:setProperty> 
    		</display:table>
    	</div>
    </c:if>
	
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="guide" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="/scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
<script type="text/javascript">
    highlightTableRows("dogList");
</script> 

