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
			<a class="eventsubmenuicon"  href="eventform.html?sid=${param.sid}&edit=false">
    			<div id="eventsubmenuevent">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="eventList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
    		<a class="eventsubmenuicon"  href="participantss.html?sid=${param.sid}">
    			<div id="eventsubmenuparticipants">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="participantsList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="roundresultss.html?sid=${param.sid}">
    			<div id="eventsubmenurounds">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="roundList.heading"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuicon"  href="eventresultss.html?sid=${param.sid}">
    			<div id="eventsubmenuresults">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="event.results"/>
    			</div>
    		</a>
			<div class="eventsubmenuseparator">
            </div>
			<a class="eventsubmenuiconselected">
    			<div id="eventsubmenureports">
                </div>
    			<div class="eventsubmenuicontext">
    			<fmt:message key="reports.reports"/>
    			</div>
    		</a>
    	</div>
	
	
	<div id="stylized" class="myform" >
		<div class="resultreport">
    		<div id="resultsreport1">
    		</div>
    		<div class="resultsreporttext" onclick="alert('falta por hacer');">
				<fmt:message key="reports.report1"/>
    		</div>
		</div>
		
    	<div class="formseparation" >
    	</div>
	</div>
 
