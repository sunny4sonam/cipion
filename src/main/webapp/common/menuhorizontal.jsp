<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="cssHorizontalMenu.vm" permissions="rolesAdapter">
<div class="wrapper1">
	<div class="wrapper">
		<div class="navhorizontal-wrapper">
			<div class="navhorizontal-left"></div>
			<div class="navhorizontal">
				<ul id="navigation">
			   		<li class="active">
						<a href="mainMenu.html">
							<span class="menu-left"></span>
							<span class="menu-mid"><fmt:message key='mainMenu.home'/></span>
							<span class="menu-right"></span>
						</a>
					</li>
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid"><fmt:message key='mainMenu.basicdata'/></span>
							<span class="menu-right"></span>
						</a>
	            	   	<div class="sub">
			   				<ul>
			   					<li>
									<a href="breeds.html"><fmt:message key='mainMenu.breeds'/></a>
								</li>
			   					<li>
									<a href="califications.html"><fmt:message key='mainMenu.califications'/></a>
								</li>
			   					<li>
									<a href="categorys.html"><fmt:message key='mainMenu.categories'/></a>
								</li>
			   					<li>
									<a href="countrys.html"><fmt:message key='mainMenu.countries'/></a>
								</li>
			   					<li>
									<a href="eventtypes.html"><fmt:message key='mainMenu.eventtypes'/></a>
								</li>
			   					<li>
									<a href="grades.html"><fmt:message key='mainMenu.grades'/></a>
								</li>
			   					<li>
									<a href="rangecalifications.html"><fmt:message key='mainMenu.rangecalifications'/></a>
								</li>
			   					<li>
									<a href="subcategorys.html"><fmt:message key='mainMenu.subcategories'/></a>
								</li>
			   					<li>
									<a href="settingsform.html?edit=false"><fmt:message key='mainMenu.settings'/></a>
								</li>
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
					</li>
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid"><fmt:message key='mainMenu.events'/></span>
							<span class="menu-right"></span>
						</a>
			   			<div class="sub">
			   				<ul>
			   					<li>
									<a href="clubs.html"><fmt:message key='mainMenu.clubs'/></a>
								</li>
			   					<li>
									<a href="guides.html"><fmt:message key='mainMenu.guides'/></a>
								</li>
			   					<li>
									<a href="dogs.html"><fmt:message key='mainMenu.dogs'/></a>
								</li>
			   					<li>
									<a href="judges.html"><fmt:message key='mainMenu.judges'/></a>
								</li>
			   					<li>
									<a href="events.html"><fmt:message key='mainMenu.events'/></a>
								</li>
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   		</li>
			   		<li class="">
						<a href="#">
							<span class="menu-left"></span>
							<span class="menu-mid"><fmt:message key='mainMenu.help'/></span>
							<span class="menu-right"></span>
						</a>
			   			<div class="sub">
			   				<ul>
			   					<li>
									<a onclick="alert('TODO (help us) - Por Hacer (ayúdenos)')"><fmt:message key='mainMenu.help'/></a>
									</li>
			   					<li>
									<a href="eventcipion.html"><fmt:message key='mainMenu.about'/></a>
								</li>
			   				</ul>
			   				<div class="btm-bg"></div>
			   			</div>
			   		</li>
			   	</ul>
			</div>
			<div class="navhorizontal-right"></div>
		</div>
	</div>
</div>
</menu:useMenuDisplayer>