<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="calificationDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='calificationDetail.heading'/>"/>
</head>



<form:form commandName="calification" method="post" action="calificationform.html" id="calificationForm" onsubmit="return validateCalification(this)">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty calification.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['calificationForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty calification.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${param.edit || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['calificationForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="calification.delete"/>')){document.forms['calificationForm'].action='calificationform.html?delete=true';document.forms['calificationForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="califications.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="calificationform.html?sid=${calification.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
        <form:hidden path="sid"/>
            <li>
                <form:errors path="name" cssClass="fieldError"/>
                <label><fmt:message key="calification.name"/>
                    <span class="small"><fmt:message key="calification.name.detail"/></span>
                </label>				
				<form:input path="name" id="name" cssErrorClass="text medium error" maxlength="150" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="description" cssClass="fieldError"/>
                <label><fmt:message key="calification.description"/>
                    <span class="small"><fmt:message key="calification.description.detail"/></span>
                </label>				
				<form:input path="description" id="description" cssErrorClass="text medium error" maxlength="150" readonly="${param.edit=='false'}"/>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="calification" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="/scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
	