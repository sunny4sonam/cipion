package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Category;
import org.palaciego.cipion.model.GenericPropertyEditor;	
import org.palaciego.cipion.webapp.controller.BaseFormController;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class CategoryFormController extends BaseFormController {
    private GenericManager<Category, Long> categoryManager = null;

    public void setCategoryManager(GenericManager<Category, Long> categoryManager) {
        this.categoryManager = categoryManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public CategoryFormController() {
        setCommandClass(Category.class);
        setCommandName("category");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return categoryManager.get(new Long(sid));
        }

        return new Category();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Category category = (Category) command;
        boolean isNew = (category.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            categoryManager.remove(category.getSid());
            saveMessage(request, getText("category.deleted", locale));
        } else {
            categoryManager.save(category);
            String key = (isNew) ? "category.added" : "category.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:categoryform.html?sid=" + category.getSid() + "&edit=false";
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
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
