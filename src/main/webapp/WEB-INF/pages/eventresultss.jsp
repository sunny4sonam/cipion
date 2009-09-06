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
			<a class="eventsubmenuicon"  href="roundresultss.html?sid=${eventsid}">
    			<div id="eventsubmenurounds">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="roundList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuiconselected">
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
		<form name="resultsfilterForm" method="post" action="eventresultss.html?sid=${param.sid}" id="resultsfilterForm">
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
                            <option value="3"
								<%
								if(roundSid.equals("3")){
									out.println("selected");
								} 
								%>
								>General</option>
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
                            <%
                            	java.lang.String sdefault="";
								Object sid=request.getAttribute("categorySid");
								if(sid!=null && (""+sid).equals("-100"))
								{
									sdefault="selected";
								}
							%>
       						<option value="-100" <%=sdefault%>>Todas Las Categorías</option>
        					<%	java.util.List categoryList=(java.util.List)request.getAttribute("listadecategorias");
    							if(categoryList!=null)
    							{
        						for(int i=0;i<categoryList.size();i++)
        						{
        							sdefault="";
        							org.palaciego.cipion.model.Category g= (org.palaciego.cipion.model.Category)categoryList.get(i);
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
    		</div>
		</form>
	</div>

		<div class="formseparation" >
		</div>
		<div id="listcontent">
			<table cellspacing="0" id="mytable" class="table">
				<caption>Listado de Resultados</caption>
				<thead>
					<tr>
						<th class="sortable" scope="col">Puesto</th>
						<th class="sortable" scope="col">Perro</th>
						<th class="sortable" scope="col">Guía</th>
						<th class="sortable" scope="col">Penalización</th>
						<th class="sortable" scope="col">Calificación</th>
			  		</tr>
			  	</thead>
				<tbody>
			<%
			java.util.List resultados=(java.util.List)request.getAttribute("resultados");
			if(resultados!=null)
			{
				for(int i=0;i<resultados.size();i++)
				{
					org.palaciego.cipion.webapp.util.Winner w= (org.palaciego.cipion.webapp.util.Winner)resultados.get(i);
			%>
			  		<tr class="<%=(i%2==0?"odd":"even")%>">
						<%="<td>"+(i+1)+ "</td>"%>
						<%="<td>"+w.participants.getDog().getName()+"</td>" %>
						<%="<td>"+w.participants.getDog().getGuide().getFirstname()+"</td>" %>
						<%="<td>"+(w.pathFaultPoints+w.timeFaultPoints)+"</td>" %>
						<%="<td>"+w.Calification.getName()+"</td>" %>
					</tr>
			<%
				}
			}
			%>
				</tbody>
			</table>		
		</div>
		<div class="formseparation" >
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
 
