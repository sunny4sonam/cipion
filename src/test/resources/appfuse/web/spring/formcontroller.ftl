<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign pojoToLower = pojo.shortName.toLowerCase()>
<#assign getIdMethodName = pojo.getGetterSignature(pojo.identifierProperty)>
<#assign setIdMethodName = 'set' + pojo.getPropertyName(pojo.identifierProperty)>
<#assign identifierType = pojo.getJavaTypeName(pojo.identifierProperty, jdk5)>
package ${basepackage}.webapp.controller;

import org.apache.commons.lang.StringUtils;
<#if genericcore>
import ${appfusepackage}.service.GenericManager;
<#else>
import ${basepackage}.service.${pojo.shortName}Manager;
</#if>

<#if !genericcore>
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if c2h.isManyToOne(field) >
	<#assign fieldToUpper = field.name.substring(0,1).toUpperCase()+field.name.substring(1)>
	// FIXME Probably package is wrong, not tested
import ${basepackage}.service.${fieldToUpper}Manager;	
	</#if>
</#foreach>
</#if>

import ${basepackage}.model.${pojo.shortName};
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if c2h.isManyToOne(field) >
	<#assign fieldToUpper = field.name.substring(0,1).toUpperCase()+field.name.substring(1)>
import ${basepackage}.model.${fieldToUpper};	
	</#if>
</#foreach>
import ${basepackage}.model.GenericPropertyEditor;	
import ${appfusepackage}.webapp.controller.BaseFormController;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class ${pojo.shortName}FormController extends BaseFormController {
<#if genericcore>
    private GenericManager<${pojo.shortName}, ${identifierType}> ${pojoNameLower}Manager = null;
<#else>
    private ${pojo.shortName}Manager ${pojoNameLower}Manager = null;
</#if>

<#if genericcore>
    public void set${pojo.shortName}Manager(GenericManager<${pojo.shortName}, ${identifierType}> ${pojoNameLower}Manager) {
<#else>
    public void set${pojo.shortName}Manager(${pojo.shortName}Manager ${pojoNameLower}Manager) {
</#if>
        this.${pojoNameLower}Manager = ${pojoNameLower}Manager;
    }
    
    // Obtain Related managers 
<#foreach field in pojo.getAllPropertiesIterator()>
	<#if c2h.isManyToOne(field) >
	<#assign fieldToUpper = field.name.substring(0,1).toUpperCase()+field.name.substring(1)>
	<#assign fieldToLower = field.name.substring(0,1).toLowerCase()+field.name.substring(1)>
	// #START-${fieldToLower}#
	<#if genericcore>
	// FIXME Verify IDENTIFIER TYPE of ${fieldToUpper}
	private GenericManager<${fieldToUpper}, Long> ${fieldToLower}Manager = null;
	<#else>
	private ${fieldToUpper}Manager ${fieldToLower}Manager = null;
	</#if>
	<#if genericcore>
	public void set${fieldToUpper}Manager(GenericManager<${fieldToUpper}, Long> ${fieldToLower}Manager) {
	<#else>
	public void set${fieldToUpper}Manager(${fieldToUpper}Manager ${fieldToLower}Manager) {
	</#if>
		this.${fieldToLower}Manager = ${fieldToLower}Manager;
	}
	
	<#if genericcore>
	private GenericPropertyEditor<${fieldToUpper}, Long> ${fieldToLower}PropEdit = null;
	<#else>
	private ${fieldToUpper}PropEdit ${fieldToLower}PropEdit = null;
	</#if>
	<#if genericcore>
	public void set${fieldToUpper}PropEdit(GenericPropertyEditor<${fieldToUpper}, Long> ${fieldToLower}PropEdit) {
	<#else>
	public void set${fieldToUpper}PropEdit(${fieldToUpper}PropEdit ${fieldToLower}PropEdit) {
	</#if>
		this.${fieldToLower}PropEdit = ${fieldToLower}PropEdit;
	}
	// #END-${fieldToLower}#
	
	</#if>
</#foreach>
	// End related managers    
    

    public ${pojo.shortName}FormController() {
        setCommandClass(${pojo.shortName}.class);
        setCommandName("${pojoNameLower}");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
	<#foreach field in pojo.getAllPropertiesIterator()>
		<#if c2h.isManyToOne(field) >
		<#assign fieldToUpper = field.name.substring(0,1).toUpperCase()+field.name.substring(1)>
		<#assign fieldToLower = field.name.substring(0,1).toLowerCase()+field.name.substring(1)>
		binder.registerCustomEditor(${fieldToUpper}.class, ${fieldToLower}PropEdit);
		</#if>
	</#foreach>
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String ${pojo.identifierProperty.name} = request.getParameter("${pojo.identifierProperty.name}");

        if (!StringUtils.isBlank(${pojo.identifierProperty.name})) {
            return ${pojoNameLower}Manager.get(new ${identifierType}(${pojo.identifierProperty.name}));
        }

        return new ${pojo.shortName}();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        ${pojo.shortName} ${pojoNameLower} = (${pojo.shortName}) command;
        boolean isNew = (${pojoNameLower}.${getIdMethodName}() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            ${pojoNameLower}Manager.remove(${pojoNameLower}.${getIdMethodName}());
            saveMessage(request, getText("${pojoNameLower}.deleted", locale));
        } else {
            ${pojoNameLower}Manager.save(${pojoNameLower});
            String key = (isNew) ? "${pojoNameLower}.added" : "${pojoNameLower}.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:${pojoToLower}form.html?${pojo.identifierProperty.name}=" + ${pojoNameLower}.${getIdMethodName}();
            }
        }

        return new ModelAndView(success);
    }
    
    
    // Loads dynamically related data
    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
		Map allReferenceData = super.referenceData(request);
		
		if(allReferenceData == null) {
			allReferenceData = new HashMap();
		}
		
		// #START isManyToOne fields
	 	// Q: How to reference data? -> A: Using <field.name>Manager
	<#foreach field in pojo.getAllPropertiesIterator()>
	 <#if c2h.isManyToOne(field) >
	 	<#assign fieldToUpper = field.name.substring(0,1).toUpperCase()+field.name.substring(1)>
	 	allReferenceData.put("${field.name}List",  ${field.name}Manager.getAll());
	 </#if>
	</#foreach>
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
