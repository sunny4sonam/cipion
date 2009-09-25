<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="clubDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='clubDetail.heading'/>"/>
</head>

<form:form commandName="club" method="post" action="clubform.html" id="clubForm" onsubmit="return validateClub(this)" enctype="multipart/form-data">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
			<!-- si no hay sid es uno nuevo, mostramos done and back -->
			<c:if test="${empty club.sid}">
    			<a id="doneButton" href="#" onclick="this.blur();document.forms['clubForm'].submit();" title="<fmt:message key="button.save"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			
			<!-- si hay sid es existente-->
			<c:if test="${not empty club.sid}">
				<!-- si queremos editar, mostramos guardar y eliminar -->
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
        			<a id="doneButton" href="#" onclick="this.blur();document.forms['clubForm'].submit();" title="<fmt:message key="button.save"/>">
        			</a>
        			<div class="buttonSeparator">
    				</div>
				</c:if>
    			<a id="deleteButton" name="delete" onclick="bCancel=true;if (confirmDelete('<fmt:message key="club.delete"/>')){document.forms['clubForm'].action='clubform.html?delete=true';document.forms['clubForm'].submit();} " href="#" title="<fmt:message key="button.delete"/>">
    			</a>
    			<div class="buttonSeparator">
				</div>
			</c:if>
			<a id="backButton" href="clubs.html" onclick="this.blur();" title="<fmt:message key="button.back"/>">
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
			<a id="editButton" href="clubform.html?sid=${club.sid}&edit=true" title="<fmt:message key="button.edit"/>">
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
                <label><fmt:message key="club.picture"/>
                    <span class="small"><fmt:message key="club.picture.detail"/></span>
                </label>				
				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
					<input type="file" id="picture" name="picture" />
                </c:if>
				<c:if test="${param.edit=='false'}">
					<img src="getimage.html?sid=${club.sid}&manager=clubManager&pojo=Club&field=picture"/>
                </c:if>
            </li>
            <li>
				<form:errors path="name" cssClass="fieldError"/>
                <label><fmt:message key="club.name"/>
                    <span class="small"><fmt:message key="club.name.detail"/></span>
                </label>				
				<form:input path="name" id="name" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="contactperson" cssClass="fieldError"/>
                <label><fmt:message key="club.contactperson"/>
                    <span class="small"><fmt:message key="club.contactperson.detail"/></span>
                </label>				
				<form:input path="contactperson" id="contactperson" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="email" cssClass="fieldError"/>
                <label><fmt:message key="club.email"/>
                    <span class="small"><fmt:message key="club.email.detail"/></span>
                </label>				
				<form:input path="email" id="email" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="telephone" cssClass="fieldError"/>
                <label><fmt:message key="club.telephone"/>
                    <span class="small"><fmt:message key="club.telephone.detail"/></span>
                </label>				
				<form:input path="telephone" id="telephone" cssClass="text medium" cssErrorClass="text medium error" maxlength="300" readonly="${param.edit=='false'}"/>
            </li>
            <li>
				<form:errors path="description" cssClass="fieldError"/>
                <label><fmt:message key="club.description"/>
                    <span class="small"><fmt:message key="club.description.detail"/></span>
                </label>				
				<form:input path="description" id="description" cssClass="text medium" cssErrorClass="text medium error" maxlength="400" readonly="${param.edit=='false'}"/>
            </li>
			<div id="collapsiblePanel">
				<div id="collapsiblePanelHead">
					<div id="iconplus" class="collapsablePanelIconPlus" onclick="ShowHide('collapsiblePanelContent',this)"></div>
					<div id="collapsablePanelHeadText"  onclick="ShowHide('collapsiblePanelContent',document.getElementById('iconplus'))">Datos de Dirección</div>
				</div>
				<div id="collapsiblePanelContent" >
                    <li>
        				<form:errors path="country" cssClass="fieldError"/>
                        <label><fmt:message key="club.country"/>
                            <span class="small"><fmt:message key="club.country.detail"/></span>
                        </label>				
        				<c:if test="${param.edit=='false'}">
        					<form:input path="country.name" id="calification" cssClass="text medium" cssErrorClass="text medium error" maxlength="255" readonly="true"/>
                        </c:if>
        				<c:if test="${(!(param.edit=='false')) || (empty param.edit)}">
                    	     <form:select path="country" id="country" cssClass="select">
                    	     	<c:if test="${!empty countryList}">
                         			<option value=""><fmt:message key="country.pickone"/></option>
                    	        	<form:options items="${countryList}" itemValue="formIdField" itemLabel="formLabelField" />
                    	     	</c:if>
                    	     	<c:if test="${empty countryList}">
                    	     		<option value=""><fmt:message key="country.noonetopick"/></option>
                    	     	</c:if>
                    	     </form:select>
                        </c:if>
                    </li>
                    <li>
        				<form:errors path="address" cssClass="fieldError"/>
                        <label><fmt:message key="club.address"/>
                            <span class="small"><fmt:message key="club.address.detail"/></span>
                        </label>				
        				<form:input path="address" id="address" cssClass="text medium" cssErrorClass="text medium error" maxlength="300" readonly="${param.edit=='false'}"/>
                    </li>
                    <li>
        				<form:errors path="city" cssClass="fieldError"/>
                        <label><fmt:message key="club.city"/>
                            <span class="small"><fmt:message key="club.city.detail"/></span>
                        </label>				
        				<form:input path="city" id="city" cssClass="text medium" cssErrorClass="text medium error" maxlength="300" readonly="${param.edit=='false'}"/>
                    </li>
                    <li>
        				<form:errors path="region" cssClass="fieldError"/>
                        <label><fmt:message key="club.region"/>
                            <span class="small"><fmt:message key="club.region.detail"/></span>
                        </label>				
        				<form:input path="region" id="region" cssClass="text medium" cssErrorClass="text medium error" maxlength="200" readonly="${param.edit=='false'}"/>
                    </li>
                    <li>
        				<form:errors path="state" cssClass="fieldError"/>
                        <label><fmt:message key="club.state"/>
                            <span class="small"><fmt:message key="club.state.detail"/></span>
                        </label>				
        				<form:input path="state" id="state" cssClass="text medium" cssErrorClass="text medium error" maxlength="300" readonly="${param.edit=='false'}"/>
                    </li>
                    <li>
        				<form:errors path="zipcode" cssClass="fieldError"/>
                        <label><fmt:message key="club.zipcode"/>
                            <span class="small"><fmt:message key="club.zipcode.detail"/></span>
                        </label>				
        				<form:input path="zipcode" id="zipcode" cssClass="text medium" cssErrorClass="text medium error" maxlength="10" readonly="${param.edit=='false'}"/>
                    </li>
                </div>
			</div>
        </ul>
	</div>
	<!-- si hay sid es existente-->
	<c:if test="${not empty club.sid}">
    	<div id="listcontent">
			<a id="addButton" href="guideform.html?edit=add&clubsid=${club.sid}" title="<fmt:message key="button.add"/>">
			</a>
    		<div id="listDetailTitle"><fmt:message key="club.listdetailtitle"/></div>
    
    		<display:table name="club.guides" class="table" defaultsort="1" requestURI="" id="guideList" export="true" pagesize="25" >
                <display:column href="guideform.html?edit=false" paramId="sid" paramProperty="sid" property="firstname" sortable="true" titleKey="guide.firstname"/>
                <display:column property="lastname" sortable="true" titleKey="guide.lastname"/>
                <display:column property="idnumber" sortable="true" titleKey="guide.idnumber"/>
    	
            	<display:setProperty name="paging.banner.item_name"><fmt:message key="guideList.guide"/></display:setProperty>
                <display:setProperty name="paging.banner.items_name"><fmt:message key="guideList.guides"/></display:setProperty>
                <display:setProperty name="export.excel.filename"><fmt:message key="guideList.title"/>.xls</display:setProperty>
                <display:setProperty name="export.csv.filename"><fmt:message key="guideList.title"/>.csv</display:setProperty>
                <display:setProperty name="export.pdf.filename"><fmt:message key="guideList.title"/>.pdf</display:setProperty> 
    		</display:table>
    	</div>
    </c:if>
	
	<div class="formseparation" >
	</div>
</form:form>

<v:javascript formName="club" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="scripts/si.files.js"></script>
<script type="text/javascript" src="scripts/yui/build/yahoo/yahoo.js"></script>
<script type="text/javascript" src="scripts/yui/build/event/event.js"></script>
<script type="text/javascript" src="scripts/yui/build/dom/dom.js"></script> 

<script type="text/javascript">
    var iHeight = 270 ; //poner aquí el height máximo alcanzado
    var collapseStep = 15 ;
    var aniSpeed = 2;
	var animating = false;
    
    function minimisepanel(objDiv)
    {
    	var t =  parseInt( YAHOO.util.Dom.getStyle(objDiv,'height')); 
    	//YAHOO.util.Dom.setStyle(objDiv, 'opacity',t/iHeight);
    	if(t>0)
    	{	
			t=t-collapseStep ;
    		if(t<=0){
				YAHOO.util.Dom.setStyle(objDiv, 'display','none');
				animating=false;
			}
			else
			{
        		YAHOO.util.Dom.setStyle(objDiv, 'height',""+t+"px");
				animating=true;
        		setTimeout( "minimisepanel('"+objDiv+"');",aniSpeed);
			}
    	}
		else
		{
			animating=false;
		}
    }


	function maximisepanel(objDiv)
    {
    	YAHOO.util.Dom.setStyle(objDiv, 'display','block')
    	var t = parseInt( YAHOO.util.Dom.getStyle(objDiv, 'height'));
    	//YAHOO.util.Dom.setStyle(objDiv, 'opacity',t/iHeight );
    	if(t<=(iHeight-collapseStep))
    	{	t=t+collapseStep ;
    		YAHOO.util.Dom.setStyle(objDiv, 'height',""+t+"px");
			animating=true;
    		setTimeout( "maximisepanel('"+objDiv+"');",aniSpeed);
    	}
		else
		{
			animating=false;
		}
    }
    
    
    function ShowHide(objID,imgref)
    {
		if(animating)
		{
			return
		}
		if (YAHOO.util.Dom.getStyle(objID, 'display')=='block')
    	{
        	minimisepanel(objID);
			imgref.className="collapsablePanelIconPlus";
        	//imgref.src="images/downarr.gif";
        	return
    	}
    	
    	maximisepanel(objID);
		imgref.className="collapsablePanelIconMinus";
    }

</script> 
<style type="text/css">
.show{display:inline;}
.hide{display:none;}
</style>
