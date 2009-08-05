package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Rangecalification;
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

public class RangecalificationFormController extends BaseFormController {
    private GenericManager<Rangecalification, Long> rangecalificationManager = null;

    public void setRangecalificationManager(GenericManager<Rangecalification, Long> rangecalificationManager) {
        this.rangecalificationManager = rangecalificationManager;
    }
    
    // Obtain Related managers 
	// #START-calification#
	// FIXME Verify IDENTIFIER TYPE of Calification
	private GenericManager<Calification, Long> calificationManager = null;
	public void setCalificationManager(GenericManager<Calification, Long> calificationManager) {
		this.calificationManager = calificationManager;
	}
	
	private GenericPropertyEditor<Calification, Long> calificationPropEdit = null;
	public void setCalificationPropEdit(GenericPropertyEditor<Calification, Long> calificationPropEdit) {
		this.calificationPropEdit = calificationPropEdit;
	}
	// #END-calification#
	
	// End related managers    
    

    public RangecalificationFormController() {
        setCommandClass(Rangecalification.class);
        setCommandName("rangecalification");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Calification.class, calificationPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return rangecalificationManager.get(new Long(sid));
        }

        return new Rangecalification();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Rangecalification rangecalification = (Rangecalification) command;
        boolean isNew = (rangecalification.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            rangecalificationManager.remove(rangecalification.getSid());
            saveMessage(request, getText("rangecalification.deleted", locale));
        } else {
            rangecalificationManager.save(rangecalification);
            String key = (isNew) ? "rangecalification.added" : "rangecalification.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:rangecalificationform.html?sid=" + rangecalification.getSid() + "&edit=false";
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
	 	allReferenceData.put("calificationList",  calificationManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
