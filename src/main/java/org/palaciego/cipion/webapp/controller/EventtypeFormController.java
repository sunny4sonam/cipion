package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Eventtype;
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

public class EventtypeFormController extends BaseFormController {
    private GenericManager<Eventtype, Long> eventtypeManager = null;

    public void setEventtypeManager(GenericManager<Eventtype, Long> eventtypeManager) {
        this.eventtypeManager = eventtypeManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public EventtypeFormController() {
        setCommandClass(Eventtype.class);
        setCommandName("eventtype");
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
            return eventtypeManager.get(new Long(sid));
        }

        return new Eventtype();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Eventtype eventtype = (Eventtype) command;
        boolean isNew = (eventtype.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            eventtypeManager.remove(eventtype.getSid());
            saveMessage(request, getText("eventtype.deleted", locale));
        } else {
            eventtypeManager.save(eventtype);
            String key = (isNew) ? "eventtype.added" : "eventtype.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:eventtypeform.html?sid=" + eventtype.getSid() + "&edit=false";
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
