<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<!--${pojo.shortName}-START-->
		<!-- ${pojoNameLower}Controller -->
		    <bean id="${pojoNameLower}Controller" class="${basepackage}.webapp.controller.${pojo.shortName}Controller">
		        <property name="${pojoNameLower}Manager" ref="${pojoNameLower}Manager"/>
		    </bean>

		<!-- ${pojoNameLower} Custom PropertyEditor -->
			<bean id="${pojoNameLower}BaseObjectExample" class="${basepackage}.model.${pojo.shortName}"></bean>
			<bean id="${pojoNameLower}PropEdit" class="${basepackage}.model.GenericPropertyEditor">
		        <property name="manager" ref="${pojoNameLower}Manager"/>
		        <property name="baseObjectExample" ref="${pojoNameLower}BaseObjectExample" />
		    </bean>

		<!-- ${pojoNameLower}FormController -->
			<bean id="${pojoNameLower}FormController" class="${basepackage}.webapp.controller.${pojo.shortName}FormController">
		        <property name="validator" ref="beanValidator"/>
		        <property name="successView" value="redirect:${util.getPluralForWord(pojoNameLower).toLowerCase()}.html"/>
		        <property name="${pojoNameLower}Manager" ref="${pojoNameLower}Manager"/>
		<#foreach field in pojo.getAllPropertiesIterator()>
			<#if c2h.isManyToOne(field) >
				<property name="${field.name}Manager" ref="${field.name}Manager"/>
				<property name="${field.name}PropEdit" ref="${field.name}PropEdit"/>
			</#if>
		</#foreach>
		    </bean>
	<!--${pojo.shortName}-END-->

    <!-- Add additional controller beans here -->