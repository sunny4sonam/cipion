<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<!--${pojo.shortName}Dao-START-->
<#if daoframework == "hibernate">
    <bean id="${pojo.shortName}Dao" class="${basepackage}.dao.hibernate.${pojo.shortName}DaoHibernate">
        <property name="sessionFactory" ref="sessionFactory"/>
    </bean>
<#elseif daoframework == "ibatis">
    <bean id="${pojo.shortName}Dao" class="${basepackage}.dao.ibatis.${pojo.shortName}DaoiBatis">
        <property name="dataSource" ref="dataSource"/>
        <property name="sqlMapClient" ref="sqlMapClient"/>
    </bean>
<#elseif daoframework == "jpa">
    <bean id="${pojo.shortName}Dao" class="${basepackage}.dao.jpa.${pojo.shortName}DaoJpa"/>
</#if>
    <!--${pojo.shortName}Dao-END-->

    <!-- Add new DAOs here -->