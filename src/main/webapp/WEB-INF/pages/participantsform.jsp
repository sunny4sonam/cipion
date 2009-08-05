<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="participantsDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='participantsDetail.heading'/>"/>
</head>

<form:form commandName="participants" method="post" action="participantsform.html" id="participantsForm" onsubmit="return validateParticipants(this)">
<form:errors path="*" cssClass="error" element="div"/>

<c:set var="buttons">
<ul class="formButtons">
	<li><input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/></li
	><li><c:if test="${not empty participants.sid}">
	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('participants')"
    	value="<fmt:message key="button.delete"/>" />
	</c:if></li
	><li><input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/></li>			
</ul>
</c:set>

<c:out value="${buttons}" escapeXml="false"/>

<ul class="formFields">
<form:hidden path="sid"/>
    <li>
        <appfuse:label styleClass="desc" key="participants.absent"/>
        <form:errors path="absent" cssClass="fieldError"/>
        <form:checkbox path="absent" id="absent" cssClass="checkbox"/>
    </li>
     <li>
		 <appfuse:label styleClass="desc" key="participants.dog"/>
		 <form:errors path="dog" cssClass="fieldError"/>
	     <form:select path="dog" id="dog" cssClass="select">
	     	<c:if test="${!empty dogList}">
     			<option value=""><fmt:message key="dog.pickone"/></option>
	        	<form:options items="${dogList}" itemValue="formIdField" itemLabel="formLabelField" />
	     	</c:if>
	     	<c:if test="${empty dogList}">
	     		<option value=""><fmt:message key="dog.noonetopick"/></option>
	     	</c:if>
	     </form:select>
     </li>
    <li>
        <appfuse:label styleClass="desc" key="participants.dorsal"/>
        <form:errors path="dorsal" cssClass="fieldError"/>
        <form:input path="dorsal" id="dorsal" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/>
    </li>
     <li>
		 <appfuse:label styleClass="desc" key="participants.event"/>
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
        <appfuse:label styleClass="desc" key="participants.heat"/>
        <form:errors path="heat" cssClass="fieldError"/>
        <form:checkbox path="heat" id="heat" cssClass="checkbox"/>
    </li>
</ul>

<c:out value="${buttons}" escapeXml="false"/>
</form:form>

<v:javascript formName="participants" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    Form.focusFirstElement($('participantsForm'));
</script>