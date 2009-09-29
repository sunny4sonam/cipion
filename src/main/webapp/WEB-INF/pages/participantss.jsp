<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="participantsList.title"/></title>
    <meta name="heading" content="<fmt:message key='participantsList.heading'/>"/>
    <meta name="menu" content="ParticipantsMenu"/>
</head>

<form id="formulario" action="participantss.html" method="GET">
    <div class="subnavmenu-wrapper">
		<div class="subnavmenu-left">
		</div>
		<div class="subnavmenu">
		</div>	
		<div class="subnavmenu-right">
	    </div>
    </div>
</form>
	
    	<div id="eventsubmenu" >
			<a class="eventsubmenuicon"  href="eventform.html?sid=${eventsid}&edit=false">
    			<div id="eventsubmenuevent">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="eventList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
    		<a class="eventsubmenuiconselected" >
    			<div id="eventsubmenuparticipants">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="participantsList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="roundresultss.html?sid=${eventsid}">
    			<div id="eventsubmenurounds">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="roundList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="eventresultss.html?sid=${eventsid}">
    			<div id="eventsubmenuresults">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="event.results"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="eventreports.html?sid=${eventsid}">
    			<div id="eventsubmenureports">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="reports.reports"/>
    			</div>
    		</a>
    	</div>
	
	
	<div id="stylized" class="myform" >
		<form name="participantfilterForm" method="post" action="participantss.html?sid=${param.sid}" id="participantfilterForm">
    		<div class="addParticipant">
                <h1>Añadir Participantes</h1>
                <p>Seleccione el participante que desea añadir</p>
                <ul class="formFields">
                    <li>
                        <label><fmt:message key="club.name"/>
                            <span class="small"><fmt:message key="club.name.detail"/></span>
                        </label>				
                        <select id="clubSid" class="select" name="clubSid" onchange="document.participantfilterForm.submit();">
                            <option value="" ><fmt:message key="club.pickone"/></option>
        					<%	java.util.List clubList=(java.util.List)request.getAttribute("listadeclubs");
        						for(int i=0;i<clubList.size();i++)
        						{
        							org.palaciego.cipion.model.Club c= (org.palaciego.cipion.model.Club)clubList.get(i);
									Object sid=request.getAttribute("clubSid");
									java.lang.String sdefault="";
									if(sid!=null && (""+sid).equals(""+c.getSid()))
									{
										sdefault="selected";
									}
        							%>
        						<option value="<%=c.getSid()%>" <%=sdefault%>><%=c.getName()%></option>
        					<%}%>
                        </select>			
                    </li>
                    <li>
                        <label><fmt:message key="guide.firstname"/>
                            <span class="small"><fmt:message key="guide.firstname.detail"/></span>
                        </label>				
                        <select id="guideSid" class="select" name="guideSid"  onchange="document.participantfilterForm.submit();">
                            <option value=""><fmt:message key="guide.pickone"/></option>
        					<%	java.util.List guideList=(java.util.List)request.getAttribute("listadeguias");
    							if(guideList!=null)
    							{
        						for(int i=0;i<guideList.size();i++)
        						{
        							org.palaciego.cipion.model.Guide g= (org.palaciego.cipion.model.Guide)guideList.get(i);
									Object sid=request.getAttribute("guideSid");
									java.lang.String sdefault="";
									if(sid!=null && (""+sid).equals(""+g.getSid()))
									{
										sdefault="selected";
									}
        							%>
        						<option value="<%=g.getSid()%>" <%=sdefault%>><%=g.getFormLabelField()%></option>
        					<%}}%>
                        </select>			
                    </li>
                    <li>
                        <label><fmt:message key="dog.name"/>
                            <span class="small"><fmt:message key="dog.name.detail"/></span>
                        </label>				
                        <select id="dogSid" class="select" name="dogSid"  onchange="document.participantfilterForm.submit();">
                            <option value=""><fmt:message key="dog.pickone"/></option>
        					<%	java.util.List dogList=(java.util.List)request.getAttribute("listadeperros");
    							if(dogList!=null)
    							{
        						for(int i=0;i<dogList.size();i++)
        						{
        							org.palaciego.cipion.model.Dog d= (org.palaciego.cipion.model.Dog)dogList.get(i);
									Object sid=request.getAttribute("dogSid");
									java.lang.String sdefault="";
									if(sid!=null && (""+sid).equals(""+d.getSid()))
									{
										sdefault="selected";
									}
        							%>
        						<option value="<%=d.getSid()%>" <%=sdefault%>><%=d.getName()+" | " + d.getCategory().getName() + " | " + d.getGrade().getName()%></option>
        					<%}}%>
                        </select>			
                    </li>
                    <li>
                         <label><fmt:message key="participants.heat"/>
                             <span class="small"><fmt:message key="participants.heat.detail"/></span>
                         </label>
						 <input id="heat" class="formcheck" type="checkbox" value="true" name="heat"/>
                    </li>
                </ul>

				<div id="addParticipantbutton" >
					<input type="button" onClick="javascript:addParticipant();" value="<fmt:message key="button.add"/> <fmt:message key="participantsList.participants"/>" class="addParticipantbutton"/>
				</div>
				
				<div id="dorsalbutton" title="Establecer Dorsales" onclick="dorsalfunction();">
                </div>
    		</div>
		</form>
	</div>

		<div id="listcontent">
		<display:table name="participantsList" class="table" defaultsort="1" requestURI="" id="participantsList" export="true" pagesize="25" >
			<display:column property="dorsal" sortable="true" titleKey="participants.dorsal"/>
			<display:column property="dog.name" sortable="true" titleKey="dogList.heading" paramId="sid" paramProperty="sid"/>
			<display:column property="dog.guide.formLabelField" sortable="true" titleKey="guideList.heading"/>
			<display:column property="dog.guide.club.name" sortable="true" titleKey="clubList.heading"/>
			<display:column property="dog.grade.name" sortable="true" titleKey="dog.grade"/>
			<display:column property="dog.category.name" sortable="true" titleKey="dog.category"/>
            <display:column sortProperty="heat" sortable="true" titleKey="participants.heat" media="html">
                <input type="checkbox" disabled="disabled" <c:if test="${participantsList.heat}">checked="checked"</c:if>/>
            </display:column>
			<display:column titleKey="button.delete" media="html" href="#">
					<a onclick="deleteParticipant(${participantsList.sid});" ><div class="deletecolumn" ></div></a>
            </display:column>
			
	
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="participantsList.participants"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="participantsList.participantss"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="participantsList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="participantsList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="participantsList.title"/>.pdf</display:setProperty> 
		</display:table>
		</div>

<script type="text/javascript">
    highlightTableRows("participantsList");

	function dorsalfunction()
	{
		if(window.confirm("&iquest;Seguro que desea establecer los dorsales para los participantes?"))
		{
    		document.participantfilterForm.action=document.participantfilterForm.action+"&dorsal=true";
    		document.participantfilterForm.submit();
		}
	}

    
	function deleteParticipant(sid)
	{
        var msg = "&iquest;Está seguro que desea eliminarlo del torneo?";
        ans = confirm(msg);
        if (ans) {
			document.participantfilterForm.action=document.participantfilterForm.action+"&delete="+sid;
			document.participantfilterForm.submit();
		}
	}

	function addParticipant()
	{
		if(document.participantfilterForm.clubSid.value=="")
		{
			alert("Debe seleccionar un club!");
			return;
		}
		if(document.participantfilterForm.guideSid.value=="")
		{
			alert("Debe seleccionar un guía!");
			return;
		}
		if(document.participantfilterForm.dogSid.value=="")
		{
			alert("Debe seleccionar un perro!");
			return;
		}
		
        var msg = "&iquest;Está seguro que desea añadir el equipo perro-guía seleccionado al torneo?\nAsegurése de que ha marcado correctamente la opción de celo";
        ans = confirm(msg);
        if (ans) 
        {
			document.participantfilterForm.action=document.participantfilterForm.action+"&add=true";
			document.participantfilterForm.submit();
        }
		
	}
</script> 
 
