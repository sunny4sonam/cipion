<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/common/taglibs.jsp"%>
<% 
	Object fin=request.getAttribute("fin");
	if(fin==null || !fin.equals("true"))
	{
%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
		<!--[if lt IE 7]>
			<link rel="stylesheet" type="text/css" href="include/ie6.css" media="screen"/>
		<![endif]-->
        <%@ include file="/common/meta.jsp" %>
        <link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/${appConfig["csstheme"]}/theme.css'/>" />
        <link rel="stylesheet" type="text/css" media="print" href="<c:url value='/styles/${appConfig["csstheme"]}/print.css'/>" />

        <script type="text/javascript" src="<c:url value='/scripts/prototype.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/scriptaculous.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/scripts/global.js'/>"></script>
    </head>
    <body>
    <center>    	
        <form:form commandName="roundresults" method="post" action="resultsdetail.html?sid=${roundresults.sid}&save=true" id="roundresultsForm" onsubmit="return validateRoundresults(this)">
        	<div id="stylized" class="myform" >
        		<form:errors path="*" cssClass="error" element="div"/>
                <h1><fmt:message key="resultdetail.head"/></h1>
                <p><fmt:message key="resultdetail.head.detail"/></p>
                <ul class="formFields">
                    <li>
                         <label><fmt:message key="resultdetail.eliminated"/>
                             <span class="small"><fmt:message key="resultdetail.eliminated.detail"/></span>
                         </label>
		                <div id="checksurround">
        					<form:checkbox path="eliminated" id="eliminated" cssClass="formcheck"/>
        				</div>
                    </li>
                    <li>
                         <label><fmt:message key="resultdetail.absent"/>
                             <span class="small"><fmt:message key="resultdetail.absent.detail"/></span>
                         </label>
		                <div id="checksurround">
        					<form:checkbox path="absent" id="absent" cssClass="formcheck"/>
        				</div>
                    </li>
                    <li>
                        <label><fmt:message key="resultdetail.startorder"/>
                            <span class="small"><fmt:message key="resultdetail.startorder.detail"/></span>
                        </label>				
        				<form:input path="startorder" id="startorder" cssErrorClass="text medium error" maxlength="150"/>
                    </li>
                    <li>
                        <label><fmt:message key="resultdetail.fouls"/>
                            <span class="small"><fmt:message key="resultdetail.fouls.detail"/></span>
                        </label>				
        				<form:input path="fouls" id="fouls" cssErrorClass="text medium error" maxlength="150"/>
                    </li>
                    <li>
                        <label><fmt:message key="resultdetail.reuses"/>
                            <span class="small"><fmt:message key="resultdetail.reuses.detail"/></span>
                        </label>				
        				<form:input path="reuses" id="reuses" cssErrorClass="text medium error" maxlength="150"/>
                    </li>
                    <li>
                        <label><fmt:message key="resultdetail.time"/>
                            <span class="small"><fmt:message key="resultdetail.time.detail"/></span>
                        </label>				
        				<form:input path="time" id="time" cssErrorClass="text medium error" maxlength="150"/>
                    </li>
                    <li>
                        <label><fmt:message key="resultdetail.result"/>
                            <span class="small"><fmt:message key="resultdetail.result.detail"/></span>
                        </label>				
        				<form:input path="result" id="result" cssErrorClass="text medium error" maxlength="150"/>
                    </li>
                </ul>
    
    			<a id="doneButton" href="#" style="float:right;" onclick="this.blur();document.forms['roundresultsForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
    			</div>
        	</div>
        </form:form>
    </center>
    
    <v:javascript formName="roundresults" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
    <script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script type="text/javascript" src="scripts/si.files.js"></script>
    
    <style type="text/css">
    .show{display:inline;}
    .hide{display:none;}
    </style>
    </body>
</html>
<%
	}
	else
	{
%>
	<html><head></head>
    <body>hola
    <script type="text/javascript">
    	window.parent.saved=true;
		window.parent.googlewin.close();
    </script>
    </body>
	</html>
<%
	}
%>