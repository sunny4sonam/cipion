<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="judgeDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='judgeDetail.heading'/>"/>
</head>

<form:form commandName="judge" method="post" action="judgeform.html" id="judgeForm" onsubmit="return validateJudge(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty judge.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['judgeForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty judge.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['judgeForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="judge.delete"/>')){document.forms['judgeForm'].action='judgeform.html?delete=true';document.forms['judgeForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="judges.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="judgeform.html?sid=${judge.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
			<form:hidden path="sid"/>

            <li>
				<form:errors path="firstname" cssClass="fieldError"/>
                <label><fmt:message key="judge.firstname"/>
                    <span class="small"><fmt:message key="judge.firstname.detail"/></span>
                </label>				
				<form:input path="firstname" id="firstname" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="lastname" cssClass="fieldError"/>
                <label><fmt:message key="judge.lastname"/>
                    <span class="small"><fmt:message key="judge.lastname.detail"/></span>
                </label>				
				<form:input path="lastname" id="lastname" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="idnumber" cssClass="fieldError"/>
                <label><fmt:message key="judge.idnumber"/>
                    <span class="small"><fmt:message key="judge.idnumber.detail"/></span>
                </label>				
				<form:input path="idnumber" id="idnumber" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="license" cssClass="fieldError"/>
                <label><fmt:message key="judge.license"/>
                    <span class="small"><fmt:message key="judge.license.detail"/></span>
                </label>				
				<form:input path="license" id="license" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="judge" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="/scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
