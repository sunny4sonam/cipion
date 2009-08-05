<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="dogDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='dogDetail.heading'/>"/>
</head>

<form:form commandName="dog" method="post" action="dogform.html" id="dogForm" onsubmit="return validateDog(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty dog.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['dogForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty dog.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['dogForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="dog.delete"/>')){document.forms['dogForm'].action='dogform.html?delete=true';document.forms['dogForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="dogs.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="dogform.html?sid=${dog.sid}&edit=true" title="<fmt:message key="button.edit"/>">
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
                <label><fmt:message key="dog.picture"/>
                    <span class="small"><fmt:message key="dog.picture.detail"/></span>
                </label>				
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<input type="file" id="picture" name="picture" />
                </c:if>
				<c:if test="${param.edit=='false'}">
					<img src="getimage.html?sid=${dog.sid}&manager=dogManager&pojo=Dog&field=picture"/>
                </c:if>
            </li>
            <li>
        		 <form:errors path="guide" cssClass="fieldError"/>
                 <label><fmt:message key="dog.guide"/>
                     <span class="small"><fmt:message key="dog.guide.detail"/></span>
                 </label>
				<c:if test="${param.edit=='false'}">
					<form:input path="guide.formLabelField" id="guidelabel" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<c:if test="${empty param.guidesid}">
                	     <form:select path="guide" id="guide" cssClass="select">
                	     	<c:if test="${!empty guideList}">
                     			<option value=""><fmt:message key="guide.pickone"/></option>
                	        	<form:options items="${guideList}" itemValue="formIdField" itemLabel="formLabelField" />
                	     	</c:if>
                	     	<c:if test="${empty guideList}">
                	     		<option value=""><fmt:message key="guide.noonetopick"/></option>
                	     	</c:if>
                	     </form:select>
                    </c:if>
					<c:if test="${! (empty param.guidesid)}">
						<input type="hidden" name="guide" id="guide" value="${param.guidesid}"/>
						<form:input path="guide.formLabelField" id="guidelabel" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                    </c:if>
                </c:if>
            </li>
            <li>
				<form:errors path="name" cssClass="fieldError"/>
                <label><fmt:message key="dog.name"/>
                    <span class="small"><fmt:message key="dog.name.detail"/></span>
                </label>				
				<form:input path="name" id="name" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="alias" cssClass="fieldError"/>
                <label><fmt:message key="dog.alias"/>
                    <span class="small"><fmt:message key="dog.alias.detail"/></span>
                </label>				
				<form:input path="alias" id="alias" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="birthday" cssClass="fieldError"/>
                <label><fmt:message key="dog.birthday"/>
                    <span class="small"><fmt:message key="dog.birthday.detail"/></span>
                </label>				
                <form:input path="birthday" id="birthday" cssClass="dateinput" size="11" />
				<c:if test="${param.edit!='false'}">
					<img src="<c:url value='/images/iconCalendar.gif'/>" alt="" id="birthdayDatePicker" class="calIcon"/>
                </c:if>
            </li>
             <li>
        		 <form:errors path="breed" cssClass="fieldError"/>
                 <label><fmt:message key="dog.breed"/>
                     <span class="small"><fmt:message key="dog.breed.detail"/></span>
                 </label>				
				<c:if test="${param.edit=='false'}">
					<form:input path="breed.formLabelField" id="breed" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="breed" id="breed" cssClass="select">
            	     	<c:if test="${!empty breedList}">
                 			<option value=""><fmt:message key="breed.pickone"/></option>
            	        	<form:options items="${breedList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty breedList}">
            	     		<option value=""><fmt:message key="breed.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
            <li>
                <form:errors path="color" cssClass="fieldError"/>
                 <label><fmt:message key="dog.color"/>
                     <span class="small"><fmt:message key="dog.color.detail"/></span>
                 </label>
                <form:input path="color" id="color" cssClass="text medium" cssErrorClass="text medium error" maxlength="100"  readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="male" cssClass="fieldError"/>
                 <label><fmt:message key="dog.male"/>
                     <span class="small"><fmt:message key="dog.male.detail"/></span>
                 </label>
				<form:checkbox path="male" id="male" cssClass="formcheck"/>
            </li>
            <li>
                <form:errors path="idnumber" cssClass="fieldError"/>
                 <label><fmt:message key="dog.idnumber"/>
                     <span class="small"><fmt:message key="dog.idnumber.detail"/></span>
                 </label>
                <form:input path="idnumber" id="idnumber" cssClass="text medium" cssErrorClass="text medium error" maxlength="100"/>
            </li>
            <li>
                <form:errors path="license" cssClass="fieldError"/>
                 <label><fmt:message key="dog.license"/>
                     <span class="small"><fmt:message key="dog.license.detail"/></span>
                 </label>
                <form:input path="license" id="license" cssClass="text medium" cssErrorClass="text medium error" maxlength="100"/>
            </li>

        			
             <li>
        		 <form:errors path="category" cssClass="fieldError"/>
                 <label><fmt:message key="dog.category"/>
                     <span class="small"><fmt:message key="dog.category.detail"/></span>
                 </label>
				<c:if test="${param.edit=='false'}">
					<form:input path="category.formLabelField" id="calification" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="category" id="category" cssClass="select">
            	     	<c:if test="${!empty categoryList}">
                 			<option value=""><fmt:message key="category.pickone"/></option>
            	        	<form:options items="${categoryList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty categoryList}">
            	     		<option value=""><fmt:message key="category.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
             <li>
        		 <form:errors path="subcategory" cssClass="fieldError"/>
                 <label><fmt:message key="dog.subcategory"/>
                     <span class="small"><fmt:message key="dog.subcategory.detail"/></span>
                 </label>
				<c:if test="${param.edit=='false'}">
        	     	<c:if test="${subcategory!=null}">
						<form:input path="subcategory.formLabelField" id="calification" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                    </c:if>
        	     	<c:if test="${subcategory==null}">
						<input type="text" class="text medium" readonly="true"/>
                    </c:if>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="subcategory" id="subcategory" cssClass="select">
            	     	<c:if test="${!empty subcategoryList}">
                 			<option value=""><fmt:message key="subcategory.pickone"/></option>
            	        	<form:options items="${subcategoryList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty subcategoryList}">
            	     		<option value=""><fmt:message key="subcategory.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
             <li>
        		 <form:errors path="grade" cssClass="fieldError"/>
                 <label><fmt:message key="dog.grade"/>
                     <span class="small"><fmt:message key="dog.grade.detail"/></span>
                 </label>
				<c:if test="${param.edit=='false'}">
					<form:input path="grade.formLabelField" id="calification" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="grade" id="grade" cssClass="select">
            	     	<c:if test="${!empty gradeList}">
                 			<option value=""><fmt:message key="grade.pickone"/></option>
            	        	<form:options items="${gradeList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty gradeList}">
            	     		<option value=""><fmt:message key="grade.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="dog" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-es.js'/>"></script>
<script type="text/javascript">
    Form.focusFirstElement($('dogForm'));
    Calendar.setup({inputField: "birthday", ifFormat: "%d/%m/%Y", button: "birthdayDatePicker", firstDay: 1});
</script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>




