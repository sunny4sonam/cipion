<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="roundresultsDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='roundresultsDetail.heading'/>"/>
</head>

<form:form commandName="roundresults" method="post" action="roundresultsform.html" id="roundresultsForm" onsubmit="return validateRoundresults(this)">
<form:errors path="*" cssClass="error" element="div"/>

<c:set var="buttons">
<ul class="formButtons">
	<li><input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/></li
	><li><c:if test="${not empty roundresults.sid}">
	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('roundresults')"
    	value="<fmt:message key="button.delete"/>" />
	</c:if></li
	><li><input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/></li>			
</ul>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ul class="formFields">
<form:hidden path="sid"/>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.absent"/>
        <form:errors path="absent" cssClass="fieldError"/>
        <div id="checksurround">
	        <form:checkbox path="absent" id="absent" cssClass="checkbox"/>
	    </div>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.category"/>
        <form:errors path="category" cssClass="fieldError"/>
        <form:input path="category" id="category" cssClass="text medium" cssErrorClass="text medium error" maxlength="100"/>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.eliminated"/>
        <form:errors path="eliminated" cssClass="fieldError"/>
        <div id="checksurround">
	        <form:checkbox path="eliminated" id="eliminated" cssClass="checkbox"/>
		</div>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.fouls"/>
        <form:errors path="fouls" cssClass="fieldError"/>
        <form:input path="fouls" id="fouls" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
     <li>
		 <appfuse:label styleClass="desc" key="roundresults.participants"/>
		 <form:errors path="participants" cssClass="fieldError"/>
	     <form:select path="participants" id="participants" cssClass="select">
	     	<c:if test="${!empty participantsList}">
     			<option value=""><fmt:message key="participants.pickone"/></option>
	        	<form:options items="${participantsList}" itemValue="formIdField" itemLabel="formLabelField" />
	     	</c:if>
	     	<c:if test="${empty participantsList}">
	     		<option value=""><fmt:message key="participants.noonetopick"/></option>
	     	</c:if>
	     </form:select>
     </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.result"/>
        <form:errors path="result" cssClass="fieldError"/>
        <form:input path="result" id="result" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.reuses"/>
        <form:errors path="reuses" cssClass="fieldError"/>
        <form:input path="reuses" id="reuses" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
     <li>
		 <appfuse:label styleClass="desc" key="roundresults.round"/>
		 <form:errors path="round" cssClass="fieldError"/>
	     <form:select path="round" id="round" cssClass="select">
	     	<c:if test="${!empty roundList}">
     			<option value=""><fmt:message key="round.pickone"/></option>
	        	<form:options items="${roundList}" itemValue="formIdField" itemLabel="formLabelField" />
	     	</c:if>
	     	<c:if test="${empty roundList}">
	     		<option value=""><fmt:message key="round.noonetopick"/></option>
	     	</c:if>
	     </form:select>
     </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.startorder"/>
        <form:errors path="startorder" cssClass="fieldError"/>
        <form:input path="startorder" id="startorder" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
    <li>
        <appfuse:label styleClass="desc" key="roundresults.time"/>
        <form:errors path="time" cssClass="fieldError"/>
        <form:input path="time" id="time" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
</ul>

<c:out value="${buttons}" escapeXml="false"/>
</form:form>

<v:javascript formName="roundresults" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    Form.focusFirstElement($('roundresultsForm'));
</script>