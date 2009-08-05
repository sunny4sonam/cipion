<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign pojoToLower = pojo.shortName.toLowerCase()>
<#assign dateExists = false>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="${pojoNameLower}Detail.title"/></title>
    <meta name="heading" content="<fmt:message key='${pojoNameLower}Detail.heading'/>"/>
</head>

<form:form commandName="${pojoNameLower}" method="post" action="${pojoToLower}form.html" id="${pojoNameLower}Form" onsubmit="return validate${pojo.shortName}(this)">
<form:errors path="*" cssClass="error" element="div"/>
<#rt/>

<#foreach field in pojo.getAllPropertiesIterator()>
<#if field.equals(pojo.identifierProperty)>
<#assign idFieldName = field.name>
<c:set var="buttons">
<ul class="formButtons">
	<li><input type="submit" class="button" name="save" value="<fmt:message key="button.save"/>"/></li
	><li><c:if test="${'$'}{not empty ${pojoNameLower}.${idFieldName}}">
	<input type="submit" class="button" name="delete" onclick="bCancel=true;return confirmDelete('${pojoNameLower}')"
    	value="<fmt:message key="button.delete"/>" />
	</c:if></li
	><li><input type="submit" class="button" name="cancel" value="<fmt:message key="button.cancel"/>" onclick="bCancel=true"/></li>			
</ul>
</c:set>

<c:out value="${'$'}{buttons}" escapeXml="false"/>

<ul class="formFields">
    <#if field.value.identifierGeneratorStrategy == "assigned">
    <li>
        <appfuse:label styleClass="desc" key="${pojoNameLower}.${field.name}"/>
        <form:errors path="${field.name}" cssClass="fieldError"/>
        <form:input path="${field.name}" id="${field.name}" cssClass="text medium"/>
    </li>
    <#else>
        <#lt/><form:hidden path="${field.name}"/>
    </#if>
<#elseif !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
    <#foreach column in field.getColumnIterator()>
    <li>
        <appfuse:label styleClass="desc" key="${pojoNameLower}.${field.name}"/>
        <form:errors path="${field.name}" cssClass="fieldError"/>
        <#if field.value.typeName == "date" || field.value.typeName == "java.util.Date" >
        <#assign dateExists = true/>
        <form:input path="${field.name}" id="${field.name}" cssClass="text" size="11"/>
        <img src="<c:url value='/images/iconCalendar.gif'/>" alt="" id="${field.name}DatePicker" class="calIcon"/>
        <#elseif field.value.typeName == "boolean" || field.value.typeName == "java.lang.Boolean">
        <form:checkbox path="${field.name}" id="${field.name}" cssClass="checkbox"/>
        <#else>
        <form:input path="${field.name}" id="${field.name}" cssClass="text medium" cssErrorClass="text medium error"<#if (column.length > 0)> maxlength="${column.length?c}"</#if>/>
        </#if>
    </li>
    </#foreach>
<#elseif c2h.isManyToOne(field)>
    <#foreach column in field.getColumnIterator()>
     <li>
		 <appfuse:label styleClass="desc" key="${pojoNameLower}.${field.name}"/>
		 <form:errors path="${field.name}" cssClass="fieldError"/>
	     <form:select path="${field.name}" id="${field.name}" cssClass="select">
	     	<c:if test="${'$'}{!empty ${field.name}List}">
     			<option value=""><fmt:message key="${field.name}.pickone"/></option>
	        	<form:options items="${'$'}{${field.name}List}" itemValue="formIdField" itemLabel="formLabelField" />
	     	</c:if>
	     	<c:if test="${'$'}{empty ${field.name}List}">
	     		<option value=""><fmt:message key="${field.name}.noonetopick"/></option>
	     	</c:if>
	     </form:select>
     </li>
    </#foreach>
</#if>
</#foreach>
</ul>

<c:out value="${'$'}{buttons}" escapeXml="false"/>
</form:form>

<v:javascript formName="${pojoNameLower}" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<#if dateExists><#rt/>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-es.js'/>"></script>
</#if><#rt/>
<script type="text/javascript">
    Form.focusFirstElement($('${pojoNameLower}Form'));
<#foreach field in pojo.getAllPropertiesIterator()>
    <#if !c2h.isCollection(field) && !c2h.isManyToOne(field) && (field.value.typeName == "java.util.Date" || field.value.typeName == "date")>
    Calendar.setup({inputField: "${field.name}", ifFormat: "%d/%m/%Y", button: "${field.name}DatePicker", firstDay: 1});
    </#if>
</#foreach>
</script>