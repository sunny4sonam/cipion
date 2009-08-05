<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<div id="lbasicdata">
	<h3 class="basicdata">
		<span><fmt:message key='mainMenu.basicdata'/></span>
	</h3>

	<ul>
		<li><!-- Razas -->
		<a accesskey="1" title="AccesKey: 1" href="breeds.html"><fmt:message key='mainMenu.breeds'/></a>
		</li>	
		<li><!-- Calificaciones -->
		<a accesskey="2" title="AccessKey: 2" href="califications.html"><fmt:message key='mainMenu.califications'/></a>
		</li>	
		<li><!-- Categorías -->
		<a accesskey="3" title="AccessKey: 3" href="categorys.html"><fmt:message key='mainMenu.categories'/></a>
		</li>	
		<li><!-- Countries -->
		<a accesskey="4" title="AccessKey: 4" href="countrys.html"><fmt:message key='mainMenu.countries'/></a>
		</li>	
		<li><!-- EventTypes -->
		<a accesskey="5" title="AccessKey: 5" href="eventtypes.html"><fmt:message key='mainMenu.eventtypes'/></a>
		</li>	
		<li><!-- Grades -->
		<a accesskey="6" title="AccessKey: 6" href="grades.html"><fmt:message key='mainMenu.grades'/></a>
		</li>	
		<li><!-- Rangecalification -->
		<a accesskey="7" title="AccessKey: 7" href="rangecalifications.html"><fmt:message key='mainMenu.rangecalifications'/></a>
		</li>	
		<li><!-- SubCategorías -->
		<a accesskey="8" title="AccessKey: 8" href="subcategorys.html"><fmt:message key='mainMenu.subcategories'/></a>
		</li>	
		<li><!-- Settings -->
		<a accesskey="9" title="AccessKey: 9" href="settingsform.html?edit=false"><fmt:message key='mainMenu.settings'/></a>
		</li>	
	</ul>
</div>
<div id="levents">
	<h3 class="events">
		<span><fmt:message key='mainMenu.events'/></span>
	</h3>
	<ul>
		<li><!-- Clubs -->
			<a accesskey="c" title="AccesKey: c" href="clubs.html"><fmt:message key='mainMenu.clubs'/></a>
		</li>	
		<li><!-- guides -->
			<a accesskey="g" title="AccesKey: g" href="guides.html"><fmt:message key='mainMenu.guides'/></a>
		</li>	
		<li><!-- Dogs -->
			<a accesskey="d" title="AccesKey: d" href="dogs.html"><fmt:message key='mainMenu.dogs'/></a>
		</li>	
		<li><!-- Judges -->
			<a accesskey="j" title="AccesKey: j" href="judges.html"><fmt:message key='mainMenu.judges'/></a>
		</li>	
		<li><!-- Events -->
			<a accesskey="e" title="AccesKey: c" href="events.html"><fmt:message key='mainMenu.events'/></a>
		</li>	
	</ul>
	
</div>

<ul id="primary-nav" class="menuList">

</ul>
</menu:useMenuDisplayer>