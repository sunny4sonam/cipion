package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Subcategory;
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

public class SubcategoryFormController extends BaseFormController {
    private GenericManager<Subcategory, Long> subcategoryManager = null;

    public void setSubcategoryManager(GenericManager<Subcategory, Long> subcategoryManager) {
        this.subcategoryManager = subcategoryManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public SubcategoryFormController() {
        setCommandClass(Subcategory.class);
        setCommandName("subcategory");
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
            return subcategoryManager.get(new Long(sid));
        }

        return new Subcategory();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Subcategory subcategory = (Subcategory) command;
        boolean isNew = (subcategory.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            subcategoryManager.remove(subcategory.getSid());
            saveMessage(request, getText("subcategory.deleted", locale));
        } else {
            subcategoryManager.save(subcategory);
            String key = (isNew) ? "subcategory.added" : "subcategory.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:subcategoryform.html?sid=" + subcategory.getSid() + "&edit=false";
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
