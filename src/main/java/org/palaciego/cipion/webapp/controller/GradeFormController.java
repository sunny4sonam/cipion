package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Grade;
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

public class GradeFormController extends BaseFormController {
    private GenericManager<Grade, Long> gradeManager = null;

    public void setGradeManager(GenericManager<Grade, Long> gradeManager) {
        this.gradeManager = gradeManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public GradeFormController() {
        setCommandClass(Grade.class);
        setCommandName("grade");
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
            return gradeManager.get(new Long(sid));
        }

        return new Grade();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Grade grade = (Grade) command;
        boolean isNew = (grade.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            gradeManager.remove(grade.getSid());
            saveMessage(request, getText("grade.deleted", locale));
        } else {
            gradeManager.save(grade);
            String key = (isNew) ? "grade.added" : "grade.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:gradeform.html?sid=" + grade.getSid() + "&edit=false";
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
