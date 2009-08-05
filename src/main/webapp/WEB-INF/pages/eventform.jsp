<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="eventDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='eventDetail.heading'/>"/>
</head>

<form:form commandName="event" method="post" action="eventform.html" id="eventForm" onsubmit="return validateEvent(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty event.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['eventForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty event.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['eventForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="event.delete"/>')){document.forms['eventForm'].action='eventform.html?delete=true';document.forms['eventForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="events.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
			</a>

		</div>	
		<div class="subnavmenu-right">
	    </div>
    </div>

	<c:if test="${(not empty event.sid) && (param.edit=='false')}">
    	<div id="eventsubmenu" >
			<a class="eventsubmenuiconselected" >
    			<div id="eventsubmenuevent">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="eventList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
    		<a class="eventsubmenuicon"  href="participantss.html?sid=${event.sid}">
    			<div id="eventsubmenuparticipants">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="participantsList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="roundresultss.html?sid=${event.sid}">
    			<div id="eventsubmenurounds">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="roundList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="eventreports.html?sid=${event.sid}">
    			<div id="eventsubmenureports">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="reports.reports"/>
    			</div>
    		</a>
    	</div>
    </c:if>
	<c:if test="${(empty event.sid) || (param.edit=='true')}">
    	<div class="formseparation" >
    	</div>
    </c:if>

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
			<a id="editButton" href="eventform.html?sid=${event.sid}&edit=true" title="<fmt:message key="button.edit"/>">
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
                <label><fmt:message key="event.name"/>
                    <span class="small"><fmt:message key="event.name.detail"/></span>
                </label>				
				<form:input path="name" id="name" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>

            <li>
				<form:errors path="description" cssClass="fieldError"/>
                <label><fmt:message key="event.description"/>
                    <span class="small"><fmt:message key="event.description.detail"/></span>
                </label>				
				<form:input path="description" id="description" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
             <li>
        		 <form:errors path="eventtype" cssClass="fieldError"/>
                 <label><fmt:message key="event.eventtype"/>
                     <span class="small"><fmt:message key="event.eventtype.detail"/></span>
                 </label>				
				<c:if test="${param.edit=='false'}">
					<form:input path="eventtype.formLabelField" id="eventtype" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
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
				<form:errors path="startDate" cssClass="fieldError"/>
                <label><fmt:message key="event.startDate"/>
                    <span class="small"><fmt:message key="event.startDate.detail"/></span>
                </label>				
                <form:input path="startDate" id="startDate" cssClass="dateinput" size="11" />
				<c:if test="${param.edit!='false'}">
					<img src="<c:url value='/images/iconCalendar.gif'/>" alt="" id="startDateDatePicker" class="calIcon"/>
                </c:if>
            </li>
            <li>
				<form:errors path="endDate" cssClass="fieldError"/>
                <label><fmt:message key="event.endDate"/>
                    <span class="small"><fmt:message key="event.endDate.detail"/></span>
                </label>				
                <form:input path="endDate" id="endDate" cssClass="dateinput" size="11" />
				<c:if test="${param.edit!='false'}">
					<img src="<c:url value='/images/iconCalendar.gif'/>" alt="" id="endDateDatePicker" class="calIcon"/>
                </c:if>
            </li>
            <li>
				<form:errors path="endenrollment" cssClass="fieldError"/>
                <label><fmt:message key="event.endenrollment"/>
                    <span class="small"><fmt:message key="event.endenrollment.detail"/></span>
                </label>				
                <form:input path="endenrollment" id="endenrollment" cssClass="dateinput" size="11" />
				<c:if test="${param.edit!='false'}">
					<img src="<c:url value='/images/iconCalendar.gif'/>" alt="" id="endenrollmentDatePicker" class="calIcon"/>
                </c:if>
            </li>
             <li>
        		 <form:errors path="judge" cssClass="fieldError"/>
                 <label><fmt:message key="event.judge"/>
                     <span class="small"><fmt:message key="event.judge.detail"/></span>
                 </label>				
				<c:if test="${param.edit=='false'}">
					<form:input path="judge.formLabelField" id="judge" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                </c:if>
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
            	     <form:select path="judge" id="judge" cssClass="select">
            	     	<c:if test="${!empty judgeList}">
                 			<option value=""><fmt:message key="judge.pickone"/></option>
            	        	<form:options items="${judgeList}" itemValue="formIdField" itemLabel="formLabelField" />
            	     	</c:if>
            	     	<c:if test="${empty judgeList}">
            	     		<option value=""><fmt:message key="judge.noonetopick"/></option>
            	     	</c:if>
            	     </form:select>
                </c:if>
             </li>
			 
            <li>
				<form:errors path="judgeassistant" cssClass="fieldError"/>
                <label><fmt:message key="event.judgeassistant"/>
                    <span class="small"><fmt:message key="event.judgeassistant.detail"/></span>
                </label>				
				<form:input path="judgeassistant" id="judgeassistant" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="judgefloor1" cssClass="fieldError"/>
                <label><fmt:message key="event.judgefloor1"/>
                    <span class="small"><fmt:message key="event.judgefloor1.detail"/></span>
                </label>				
				<form:input path="judgefloor1" id="judgefloor1" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="judgefloor2" cssClass="fieldError"/>
                <label><fmt:message key="event.judgefloor2"/>
                    <span class="small"><fmt:message key="event.judgefloor2.detail"/></span>
                </label>				
				<form:input path="judgefloor2" id="judgefloor2" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="judgefloor3" cssClass="fieldError"/>
                <label><fmt:message key="event.judgefloor3"/>
                    <span class="small"><fmt:message key="event.judgefloor3.detail"/></span>
                </label>				
				<form:input path="judgefloor3" id="judgefloor3" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="judgefloor4" cssClass="fieldError"/>
                <label><fmt:message key="event.judgefloor4"/>
                    <span class="small"><fmt:message key="event.judgefloor4.detail"/></span>
                </label>				
				<form:input path="judgefloor4" id="judgefloor4" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="judgeparticipant" cssClass="fieldError"/>
                <label><fmt:message key="event.judgeparticipant"/>
                    <span class="small"><fmt:message key="event.judgeparticipant.detail"/></span>
                </label>				
				<form:input path="judgeparticipant" id="judgeparticipant" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="secretary1" cssClass="fieldError"/>
                <label><fmt:message key="event.secretary1"/>
                    <span class="small"><fmt:message key="event.secretary1.detail"/></span>
                </label>				
				<form:input path="secretary1" id="secretary1" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="secretary2" cssClass="fieldError"/>
                <label><fmt:message key="event.secretary2"/>
                    <span class="small"><fmt:message key="event.secretary2.detail"/></span>
                </label>				
				<form:input path="secretary2" id="secretary2" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="timekeeper1" cssClass="fieldError"/>
                <label><fmt:message key="event.timekeeper1"/>
                    <span class="small"><fmt:message key="event.timekeeper1.detail"/></span>
                </label>				
				<form:input path="timekeeper1" id="timekeeper1" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
                <form:errors path="timekeeper2" cssClass="fieldError"/>
                <label><fmt:message key="event.timekeeper2"/>
                    <span class="small"><fmt:message key="event.timekeeper2.detail"/></span>
                </label>				
				<form:input path="timekeeper2" id="timekeeper2" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
        </ul>
	</div>
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="judge" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/calendar-setup.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/calendar/lang/calendar-es.js'/>"></script>
<script type="text/javascript">
    Form.focusFirstElement($('eventForm'));
    Calendar.setup({inputField: "endDate", ifFormat: "%d/%m/%Y", button: "endDateDatePicker", firstDay: 1});
    Calendar.setup({inputField: "endenrollment", ifFormat: "%d/%m/%Y", button: "endenrollmentDatePicker", firstDay: 1});
    Calendar.setup({inputField: "startDate", ifFormat: "%d/%m/%Y", button: "startDateDatePicker", firstDay: 1});
</script>

<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>



