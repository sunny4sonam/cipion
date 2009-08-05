package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Judge;
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

public class JudgeFormController extends BaseFormController {
    private GenericManager<Judge, Long> judgeManager = null;

    public void setJudgeManager(GenericManager<Judge, Long> judgeManager) {
        this.judgeManager = judgeManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public JudgeFormController() {
        setCommandClass(Judge.class);
        setCommandName("judge");
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
            return judgeManager.get(new Long(sid));
        }

        return new Judge();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Judge judge = (Judge) command;
        boolean isNew = (judge.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            judgeManager.remove(judge.getSid());
            saveMessage(request, getText("judge.deleted", locale));
        } else {
            judgeManager.save(judge);
            String key = (isNew) ? "judge.added" : "judge.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:judgeform.html?sid=" + judge.getSid() + "&edit=false";
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
