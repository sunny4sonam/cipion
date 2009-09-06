<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="rangecalificationDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='rangecalificationDetail.heading'/>"/>
</head>

<form:form commandName="rangecalification" method="post" action="rangecalificationform.html" id="rangecalificationForm" onsubmit="return validateRangecalification(this)">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty rangecalification.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['rangecalificationForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty rangecalification.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${param.edit || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['rangecalificationForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="rangecalification.delete"/>')){document.forms['rangecalificationForm'].action='rangecalificationform.html?delete=true';document.forms['rangecalificationForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="rangecalifications.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="rangecalificationform.html?sid=${rangecalification.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
        <form:hidden path="sid"/>
             <li>
                <label><fmt:message key="rangecalification.calification"/>
                    <span class="small"><fmt:message key="rangecalification.calification.detail"/></span>
                </label>				
    		    <form:errors path="calification" cssClass="fieldError"/>
				<c:if test="${param.edit=='false'}">
					<form:input path="calification.name" id="calification" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="calification" id="calification" cssClass="select">
            	     	<c:if test="${!empty calificationList}">
                 			<option value=""><fmt:message key="calification.pickone"/></option>
            	        	<form:options items="${calificationList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty calificationList}">
            	     		<option value=""><fmt:message key="calification.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
            <li>
                <form:errors path="frompoint" cssClass="fieldError"/>
                <label><fmt:message key="rangecalification.frompoint"/>
                    <span class="small"><fmt:message key="rangecalification.frompoint.detail"/></span>
                </label>				
                <form:input path="frompoint" id="frompoint" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="topoint" cssClass="fieldError"/>
                <label><fmt:message key="rangecalification.topoint"/>
                    <span class="small"><fmt:message key="rangecalification.topoint.detail"/></span>
                </label>				
                <form:input path="topoint" id="topoint" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="round" cssClass="fieldError"/>
                <label><fmt:message key="rangecalification.round"/>
                    <span class="small"><fmt:message key="rangecalification.round.detail"/></span>
                </label>				
                <div id="checksurround">
                	<form:checkbox path="round" id="round" cssClass="formcheck"/>
                </div>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="rangecalification" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
