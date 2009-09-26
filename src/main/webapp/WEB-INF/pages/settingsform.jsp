<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="settingsDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='settingsDetail.heading'/>"/>
</head>


<form:form commandName="settings" method="post" action="settingsform.html" id="settingsForm" onsubmit="return validateSettings(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si queremos editar, mostramos guardar y eliminar -->
			<c:if test="${param.edit || (empty param.edit)}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['settingsForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
    			<a id="backButton" href="settingsform.html?edit=false" onclick="this.blur();" title="<fmt:message key="button.back"/>">
    			</a>
			</c:if>
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
			<a id="editButton" href="settingsform.html?sid=${settings.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
        <form:hidden path="sid"/>
            <li>
                <form:errors path="reportlogo" cssClass="fieldError"/>
                <label><fmt:message key="settings.reportlogo"/>
                    <span class="small"><fmt:message key="settings.reportlogo.detail"/></span>
                </label>				
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<input type="file" id="reportlogo" name="reportlogo" />
                </c:if>
				<c:if test="${param.edit=='false'}">
					<img src="getimage.html?sid=${settings.sid}&manager=settingsManager&pojo=Settings&field=reportlogo"/>
                </c:if>
            </li>
             <li>
        		<form:errors path="country" cssClass="fieldError"/>
                <label><fmt:message key="settings.country"/>
                    <span class="small"><fmt:message key="settings.country.detail"/></span>
                </label>
				<c:if test="${param.edit=='false'}">
					<form:input path="country.name" id="country" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="country" id="country" cssClass="select">
            	     	<c:if test="${!empty countryList}">
                 			<option value=""><fmt:message key="country.pickone"/></option>
            	        	<form:options items="${countryList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty countryList}">
            	     		<option value=""><fmt:message key="country.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
             <li>
        		<form:errors path="club" cssClass="fieldError"/>
                <label><fmt:message key="settings.club"/>
                    <span class="small"><fmt:message key="settings.club.detail"/></span>
                </label>
				<c:if test="${param.edit=='false' && club!=null}">
					<form:input path="club.formLabelField" id="club" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${param.edit=='false' && club==null}">
					<input id="club" class="text medium" type="text" maxlength="255" value="" name="club"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
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
             </li>
             <li>
				<form:errors path="eventtype" cssClass="fieldError"/>
                <label><fmt:message key="settings.eventtype"/>
					<span class="small"><fmt:message key="settings.eventtype.detail"/></span>
                </label>
                <c:if test="${param.edit=='false'}">
					<form:input path="eventtype.name" id="eventtype" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
                <c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="eventtype" id="eventtype" cssClass="select">
            	     	<c:if test="${!empty eventtypeList}">
                 			<option value=""><fmt:message key="eventtype.pickone"/></option>
            	        	<form:options items="${eventtypeList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty eventtypeList}">
            	     		<option value=""><fmt:message key="eventtype.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
             <li>
                <form:errors path="maxreuses" cssClass="fieldError"/>
                <label><fmt:message key="settings.maxreuses"/>
					<span class="small"><fmt:message key="settings.maxreuses.detail"/></span>
                </label>
                <form:input path="maxreuses" id="maxreuses" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="pointspenaltyabsent" cssClass="fieldError"/>
                <label><fmt:message key="settings.pointspenaltyabsent"/>
					<span class="small"><fmt:message key="settings.pointspenaltyabsent.detail"/></span>
                </label>
                <form:input path="pointspenaltyabsent" id="pointspenaltyabsent" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="pointspenaltyfoul" cssClass="fieldError"/>
                <label><fmt:message key="settings.pointspenaltyfoul"/>
					<span class="small"><fmt:message key="settings.pointspenaltyfoul.detail"/></span>
                </label>
                <form:input path="pointspenaltyfoul" id="pointspenaltyfoul" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="pointspenaltymaxreuses" cssClass="fieldError"/>
                <label><fmt:message key="settings.pointspenaltymaxreuses"/>
					<span class="small"><fmt:message key="settings.pointspenaltymaxreuses.detail"/></span>
                </label>
                <form:input path="pointspenaltymaxreuses" id="pointspenaltymaxreuses" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="pointspenaltyreuse" cssClass="fieldError"/>
                <label><fmt:message key="settings.pointspenaltyreuse"/>
					<span class="small"><fmt:message key="settings.pointspenaltyreuse.detail"/></span>
                </label>
                <form:input path="pointspenaltyreuse" id="pointspenaltyreuse" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="pointspenaltyeliminated" cssClass="fieldError"/>
                <label><fmt:message key="settings.pointspenaltyeliminated"/>
					<span class="small"><fmt:message key="settings.pointspenaltyeliminated.detail"/></span>
                </label>
                <form:input path="pointspenaltyeliminated" id="pointspenaltyeliminated" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="settings" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
