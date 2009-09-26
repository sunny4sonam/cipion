<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="roundresultsList.title"/></title>
    <meta name="heading" content="<fmt:message key='roundresultsList.heading'/>"/>
    <meta name="menu" content="RoundresultsMenu"/>
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
    		<a class="eventsubmenuicon"  href="participantss.html?sid=${eventsid}">
    			<div id="eventsubmenuparticipants">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="participantsList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuiconselected">
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
		<form name="resultsfilterForm" method="post" action="roundresultss.html?sid=${param.sid}" id="resultsfilterForm">
    		<div class="addParticipant">
                <ul class="formFields">
                    <li>
                        <label><fmt:message key="roundList.title"/>
                            <span class="small"><fmt:message key="round.pickone"/></span>
                        </label>				
                        <select id="roundSid" class="select" name="roundSid" onchange="document.resultsfilterForm.submit();">
                            <option value="1" 
								<%
								java.lang.String roundSid=(java.lang.String)request.getAttribute("roundSid");
								if(roundSid.equals("1")){
									out.println("selected");
								} 
								%>
							>Manga 1</option>
                            <option value="2"
								<%
								if(roundSid.equals("2")){
									out.println("selected");
								} 
								%>
								>Manga 2</option>
                        </select>
                    </li>
					<li>
                        <label><fmt:message key="gradeList.heading"/>
                            <span class="small"><fmt:message key="grade.pickone"/></span>
                        </label>
                        <select id="gradeSid" class="select" name="gradeSid" onchange="document.resultsfilterForm.submit();">
                            <option value="" ><fmt:message key="grade.pickone"/></option>
        					<%	java.util.List gradeList=(java.util.List)request.getAttribute("listadegrados");
        						for(int i=0;i<gradeList.size();i++)
        						{
        							org.palaciego.cipion.model.Grade c= (org.palaciego.cipion.model.Grade)gradeList.get(i);
									Object sid=request.getAttribute("gradeSid");
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
                        <label><fmt:message key="categoryList.heading"/>
                            <span class="small"><fmt:message key="category.pickone"/></span>
                        </label>				
                        <select id="categorySid" class="select" name="categorySid"  onchange="document.resultsfilterForm.submit();">
                            <option value=""><fmt:message key="category.pickone"/></option>
        					<%	java.util.List categoryList=(java.util.List)request.getAttribute("listadecategorias");
    							if(categoryList!=null)
    							{
        						for(int i=0;i<categoryList.size();i++)
        						{
        							org.palaciego.cipion.model.Category g= (org.palaciego.cipion.model.Category)categoryList.get(i);
									Object sid=request.getAttribute("categorySid");
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
                </ul>
				<c:if test="${not empty roundresultsList}">
				<div id="waydata" >
    				<div class="roundresultstrm">
    					<div class="roundresultstrmtext">TRM(s)</div>
    					<input id="trm" name="trm" type="text" value="${trm}" onkeypress="showsavetrm(this)"/>
    				</div>
					<div class="roundresultssep"></div>
    				<div class="roundresultstrm">
    					<div class="roundresultstrmtext">TRS(s)</div>
    					<input id="trs" name="trs" type="text" value="${trs}" onkeypress="showsavetrm(this)"/>
    				</div>
					<div class="roundresultssep"></div>
    				<div class="roundresultstrm">
    					<div class="roundresultstrmtext">Longitud(m)</div>
    					<input id="distance" name="distance" type="text" value="${distance}" onkeypress="showsavetrm(this)"/>
    				</div>
					<div class="roundresultssep"></div>
    				<div class="roundresultstrm">
    					<div class="roundresultstrmtext">Velocidad(m/s)</div>
    					<input id="velocity" name="velocity" type="text" value="${velocity}" onkeypress="showsavetrm(this)"/>
    				</div>
					<div class="roundresultssep"></div>
        			<div id="savetrm" class="roundresultsnone" title="<fmt:message key="button.save"/>" onclick="savetrm();">
                    </div>
                </div>
				<%
				if(roundSid.equals("1")){
				%>
				<div id="startorderbutton" title="Establecer Orden de Salida Manga 1" onclick="startorderfunction();">
                </div>
				<%
				}
				else
				{
				%>
				<div id="startorderbutton" title="Establecer Orden de Salida Manga 2" onclick="startorderMangaTwofunction();">
                </div>
				<%
				}
				%>

                </c:if>
    		</div>
		</form>
	</div>

		<div id="listcontent">
		<display:table name="roundresultsList" class="table" defaultsort="1" requestURI="" id="roundresultsList" export="true" pagesize="25" >
			<display:column property="startorder" sortable="true" titleKey="roundresults.startorder" paramId="sid" paramProperty="sid"/>
			<display:column property="participants.dorsal" sortable="true" titleKey="participants.dorsal"/>
			<display:column property="participants.dog.name" sortable="true" titleKey="dogList.heading"/>
            <display:column sortProperty="participants.heat" sortable="true" titleKey="participants.heat">
                <input type="checkbox" disabled="disabled" <c:if test="${roundresultsList.participants.heat}">checked="checked"</c:if>/>
            </display:column>
			<display:column property="fouls" sortable="true" titleKey="roundresults.fouls"/>
			<display:column property="reuses" sortable="true" titleKey="roundresults.reuses"/>
			<display:column property="result" sortable="true" titleKey="roundresults.result.table"/>
			<display:column property="time" sortable="true" titleKey="roundresults.time"/>
            <display:column sortProperty="absent" sortable="true" titleKey="roundresults.absent.table">
                <input type="checkbox" disabled="disabled" <c:if test="${roundresultsList.absent}">checked="checked"</c:if>/>
            </display:column>
            <display:column sortProperty="eliminated" sortable="true" titleKey="roundresults.eliminated.table">
                <input type="checkbox" disabled="disabled" <c:if test="${roundresultsList.eliminated}">checked="checked"</c:if>/>
            </display:column>
			<display:column titleKey="Edit" media="html" href="#">
					<a onclick="javascript:detailprompt(${roundresultsList.sid})" ><div class="editresults" ></div></a>
            </display:column>
			
        	<display:setProperty name="paging.banner.item_name"><fmt:message key="roundresultsList.roundresults"/></display:setProperty>
            <display:setProperty name="paging.banner.items_name"><fmt:message key="roundresultsList.roundresultss"/></display:setProperty>
            <display:setProperty name="export.excel.filename"><fmt:message key="roundresultsList.title"/>.xls</display:setProperty>
            <display:setProperty name="export.csv.filename"><fmt:message key="roundresultsList.title"/>.csv</display:setProperty>
            <display:setProperty name="export.pdf.filename"><fmt:message key="roundresultsList.title"/>.pdf</display:setProperty> 
		</display:table>
		</div>

<script type="text/javascript" src="scripts/yui/build/yahoo/yahoo.js"></script>
<script type="text/javascript" src="scripts/yui/build/event/event.js"></script>
<script type="text/javascript" src="scripts/yui/build/dom/dom.js"></script> 
<script type="text/javascript" src="scripts/dhtmlwindow/dhtmlwindow.js">
/***********************************************
* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/
</script> 
<script type="text/javascript" src="scripts/dhtmlwindow/modal.js">
/***********************************************
* DHTML Window Widget- © Dynamic Drive (www.dynamicdrive.com)
* This notice must stay intact for legal use.
* Visit http://www.dynamicdrive.com/ for full source code
***********************************************/
</script> 

<script type="text/javascript">
	var googlewin;
	var saved=false;
	
    highlightTableRows("roundresultsList");

	function startorderfunction()
	{
		if(window.confirm("&iquest;Seguro que desea establecer el orden de salida de los participantes?"))
		{
    		document.resultsfilterForm.action=document.resultsfilterForm.action+"&startorder=true";
    		document.resultsfilterForm.submit();
		}
	}
	
	function startorderMangaTwofunction()
	{
		if(window.confirm("&iquest;Seguro que desea establecer el orden de salida para la Manga 2? Recuerde que el orden será inverso a los resultados obtenidos en la primera manga."))
		{
    		document.resultsfilterForm.action=document.resultsfilterForm.action+"&startorder=true";
    		document.resultsfilterForm.submit();
		}
	}
	
	function showsavetrm(objID)
	{
		document.getElementById("savetrm").className="roundresultssave";
		YAHOO.util.Dom.setStyle(objID, 'background','red');
	}

	function savetrm()
	{
		document.resultsfilterForm.action=document.resultsfilterForm.action+"&savetrm=true";
		document.resultsfilterForm.submit();
	}

    function detailprompt(sid){
		saved=false;
		googlewin=dhtmlwindow.open("googlebox", "iframe", "resultsdetail.html?sid="+sid, "Resultados en la Manga", "width=544px,height=480px,resize=0,scrolling=1,center=1,enablecontrols=false", "recal");
        googlewin.onclose=function(){ //Run custom code when window is about to be closed
	        if(!saved)
			{
				return window.confirm("&iquest;Seguro que desea salir sin Guardar?")
			}
			else
			{
				document.resultsfilterForm.submit();
				return true;
			}
        } 
		
	}
</script>
 
