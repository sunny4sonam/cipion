<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="roundDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='roundDetail.heading'/>"/>
</head>

<form:form commandName="round" method="post" action="roundform.html" id="roundForm" onsubmit="return validateRound(this)">
<form:errors path="*" cssClass="error" element="div"/>

<c:set var="buttons">
<ul class="formButtons">
	<li><input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/></li
	><li><c:if test="${not empty round.sid}">
	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('round')"
    	value="<fmt:message key="button.delete"/>" />
	</c:if></li
	><li><input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/></li>			
</ul>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ul class="formFields">
<form:hidden path="sid"/>
    <li>
        <appfuse:label styleClass="desc" key="round.description"/>
        <form:errors path="description" cssClass="fieldError"/>
        <form:input path="description" id="description" cssClass="text medium" cssErrorClass="text medium error" maxlength="400"/>
    </li>
     <li>
		 <appfuse:label styleClass="desc" key="round.event"/>
		 <form:errors path="event" cssClass="fieldError"/>
	     <form:select path="event" id="event" cssClass="select">
	     	<c:if test="${!empty eventList}">
     			<option value=""><fmt:message key="event.pickone"/></option>
	        	<form:options items="${eventList}" itemValue="formIdField" itemLabel="formLabelField" />
	     	</c:if>
	     	<c:if test="${empty eventList}">
	     		<option value=""><fmt:message key="event.noonetopick"/></option>
	     	</c:if>
	     </form:select>
     </li>
    <li>
        <appfuse:label styleClass="desc" key="round.number"/>
        <form:errors path="number" cssClass="fieldError"/>
        <form:input path="number" id="number" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
</ul>

<c:out value="${buttons}" escapeXml="false"/>
</form:form>

<v:javascript formName="round" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    Form.focusFirstElement($('roundForm'));
</script>