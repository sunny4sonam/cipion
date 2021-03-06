<#assign pojoNameLower = pojo.shortName.substring(0,1).toLowerCase()+pojo.shortName.substring(1)>
<#assign pojoToLower = pojo.shortName.toLowerCase()>
package ${basepackage}.webapp.controller;

import ${appfusepackage}.webapp.controller.BaseControllerTestCase;
import ${pojo.packageName}.${pojo.shortName};
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;

public class ${pojo.shortName}FormControllerTest extends BaseControllerTestCase {
    private ${pojo.shortName}FormController form;

    public void set${pojo.shortName}FormController(${pojo.shortName}FormController form) {
        this.form = form;
    }

    public void testEdit() throws Exception {
        log.debug("testing edit...");
        MockHttpServletRequest request = newGet("/${pojoToLower}form.html");
        request.addParameter("${pojo.identifierProperty.name}", "-1");

        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());

        ${pojo.shortName} ${pojoNameLower} = (${pojo.shortName}) mv.getModel().get(form.getCommandName());
        assertNotNull(${pojoNameLower});
    }

    public void testSave() throws Exception {
        MockHttpServletRequest request = newGet("/${pojoToLower}form.html");
        request.addParameter("${pojo.identifierProperty.name}", "-1");

        ModelAndView mv = form.handleRequest(request, new MockHttpServletResponse());

        ${pojo.shortName} ${pojoNameLower} = (${pojo.shortName}) mv.getModel().get(form.getCommandName());
        assertNotNull(${pojoNameLower});

        request = newPost("/${pojoToLower}form.html");

        // update required fields
<#foreach field in pojo.getAllPropertiesIterator()>
    <#foreach column in field.getColumnIterator()>
        <#if !field.equals(pojo.identifierProperty) && !column.nullable && !c2h.isCollection(field) && !c2h.isManyToOne(field) && !c2j.isComponent(field)>
        	<#if pojo.getJavaTypeName(field, jdk5) != "java.util.Long" && pojo.getJavaTypeName(field, jdk5) != "Long"  >
		${pojoNameLower}.set${pojo.getPropertyName(field)}(${data.getValueForJavaTest(column)});
		    <#else>
		${pojoNameLower}.set${pojo.getPropertyName(field)}((long)(Math.random()*1000));
		    </#if>
		    
        </#if>
    </#foreach>
</#foreach>

        super.objectToRequestParameters(${pojoNameLower}, request);

        mv = form.handleRequest(request, new MockHttpServletResponse());

        Errors errors = (Errors) mv.getModel().get(BindException.MODEL_KEY_PREFIX + "${pojoNameLower}");
        assertNull(errors);
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    public void testRemove() throws Exception {
        MockHttpServletRequest request = newPost("/${pojoToLower}form.html");
        request.addParameter("delete", "");
        request.addParameter("${pojo.identifierProperty.name}", "-2");

        form.handleRequest(request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}