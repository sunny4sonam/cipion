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
			<a target="_blank" href="showreport.html?report=results&sid=${param.sid}&bycategory=true">
    		<div id="resultsreport1">
    		</div>
    		<div class="resultsreporttext">
				<fmt:message key="reports.report1"/>
    		</div>
	    	</a>
		</div>
		<div class="resultreport">
			<a target="_blank" href="showreport.html?report=results&sid=${param.sid}&bycategory=false">
    		<div id="resultsreport3">
    		</div>
    		<div class="resultsreporttext">
				<fmt:message key="reports.report3"/>
    		</div>
	    	</a>
		</div>
		<div class="resultreport">
			<a target="_blank" href="reportordersevent.html?report=reportordersevent&sid=${param.sid}&round=1">
    		<div id="resultsreport2">
    		</div>
    		<div class="resultsreporttext">
				<fmt:message key="reports.report2"/>
    		</div>
	    	</a>
		</div>
		<div class="formseparation" >
		</div>
		<div class="resultreport">
			<a target="_blank" href="reportordersevent.html?report=reportordersevent&sid=${param.sid}&round=2">
    		<div id="resultsreport4">
    		</div>
    		<div class="resultsreporttext">
				<fmt:message key="reports.report4"/>
    		</div>
	    	</a>
		</div>
		
    	<div class="formseparation" >
    	</div>
	</div>
 
