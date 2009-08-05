package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Calification;
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

public class CalificationFormController extends BaseFormController {
    private GenericManager<Calification, Long> calificationManager = null;

    public void setCalificationManager(GenericManager<Calification, Long> calificationManager) {
        this.calificationManager = calificationManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public CalificationFormController() {
        setCommandClass(Calification.class);
        setCommandName("calification");
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
            return calificationManager.get(new Long(sid));
        }

        return new Calification();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Calification calification = (Calification) command;
        boolean isNew = (calification.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            calificationManager.remove(calification.getSid());
            saveMessage(request, getText("calification.deleted", locale));
        } else {
            calificationManager.save(calification);
            String key = (isNew) ? "calification.added" : "calification.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:calificationform.html?sid=" + calification.getSid()+ "&edit=false";;
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
