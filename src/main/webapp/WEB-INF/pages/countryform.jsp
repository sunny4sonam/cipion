<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="countryDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='countryDetail.heading'/>"/>
</head>


<form:form commandName="country" method="post" action="countryform.html" id="countryForm" onsubmit="return validateCountry(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty country.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['countryForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty country.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['countryForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="country.delete"/>')){document.forms['countryForm'].action='countryform.html?delete=true';document.forms['countryForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="countrys.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="countryform.html?sid=${country.sid}&edit=true" title="<fmt:message key="button.edit"/>">
			<span id="editsmall"><fmt:message key="button.edit"/></span>
			</a>
            <h1><fmt:message key="form.title.visualization"/></h1>
            <p><fmt:message key="form.subtitle.visualization"/></p>
        </c:if>
		
		<form:errors path="*" cssClass="error" element="div"/>
        <ul class="formFields">
        <form:hidden path="sid"/>
            <li>
                <form:errors path="flag" cssClass="fieldError"/>
                <label><fmt:message key="country.flag"/>
                    <span class="small"><fmt:message key="country.flag.detail"/></span>
                </label>				
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<input type="file" id="flag" name="flag" />
                </c:if>
				<c:if test="${param.edit=='false'}">
					<img src="getimage.html?sid=${country.sid}&manager=countryManager&pojo=Country&field=flag" alt="Wikipedia"/>
                </c:if>
            </li>
            <li>
                <form:errors path="name" cssClass="fieldError"/>
                <label><fmt:message key="country.name"/>
                    <span class="small"><fmt:message key="country.name.detail"/></span>
                </label>				
				<form:input path="name" id="name" cssErrorClass="text medium error" maxlength="150" readonly="${param.edit=='false'}"/>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="country" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="/scripts/si.files.js"></script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>

<script type="text/javascript">
	SI.Files.stylizeAll();
	//o
	//SI.Files.stylizeById('input-id');
    Form.focusFirstElement($('countryForm'));
</script>

