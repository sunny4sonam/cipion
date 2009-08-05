package org.palaciego.cipion.webapp.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.model.Category;
import org.palaciego.cipion.model.Event;
import org.palaciego.cipion.model.Eventtype;
import org.palaciego.cipion.model.GenericPropertyEditor;
import org.palaciego.cipion.model.Grade;
import org.palaciego.cipion.model.Judge;
import org.palaciego.cipion.model.Round;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class EventFormController extends BaseFormController {
    private GenericManager<Event, Long> eventManager = null;
    private GenericManager<Round, Long> roundManager = null;
    private GenericManager<Grade, Long> gradeManager = null;
	private GenericManager<Category, Long> categoryManager = null;

    public GenericManager<Grade, Long> getGradeManager() {
		return gradeManager;
	}

	public void setGradeManager(GenericManager<Grade, Long> gradeManager) {
		this.gradeManager = gradeManager;
	}

	public GenericManager<Category, Long> getCategoryManager() {
		return categoryManager;
	}

	public void setCategoryManager(GenericManager<Category, Long> categoryManager) {
		this.categoryManager = categoryManager;
	}

	public GenericManager<Round, Long> getRoundManager() {
		return roundManager;
	}

	public void setRoundManager(GenericManager<Round, Long> roundManager) {
		this.roundManager = roundManager;
	}

	public void setEventManager(GenericManager<Event, Long> eventManager) {
        this.eventManager = eventManager;
    }
    
    // Obtain Related managers 
	// #START-eventtype#
	// FIXME Verify IDENTIFIER TYPE of Eventtype
	private GenericManager<Eventtype, Long> eventtypeManager = null;
	public void setEventtypeManager(GenericManager<Eventtype, Long> eventtypeManager) {
		this.eventtypeManager = eventtypeManager;
	}
	
	private GenericPropertyEditor<Eventtype, Long> eventtypePropEdit = null;
	public void setEventtypePropEdit(GenericPropertyEditor<Eventtype, Long> eventtypePropEdit) {
		this.eventtypePropEdit = eventtypePropEdit;
	}
	// #END-eventtype#
	
	// #START-judge#
	// FIXME Verify IDENTIFIER TYPE of Judge
	private GenericManager<Judge, Long> judgeManager = null;
	public void setJudgeManager(GenericManager<Judge, Long> judgeManager) {
		this.judgeManager = judgeManager;
	}
	
	private GenericPropertyEditor<Judge, Long> judgePropEdit = null;
	public void setJudgePropEdit(GenericPropertyEditor<Judge, Long> judgePropEdit) {
		this.judgePropEdit = judgePropEdit;
	}
	// #END-judge#
	
	// End related managers    
    

    public EventFormController() {
        setCommandClass(Event.class);
        setCommandName("event");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Eventtype.class, eventtypePropEdit);
		binder.registerCustomEditor(Judge.class, judgePropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return eventManager.get(new Long(sid));
        }

        return new Event();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Event event = (Event) command;
        boolean isNew = (event.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            eventManager.remove(event.getSid());
            saveMessage(request, getText("event.deleted", locale));
        } else {
            try
            {
                eventManager.save(event);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
            //--------------------------------
            
            String key = (isNew) ? "event.added" : "event.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:eventform.html?sid=" + event.getSid() + "&edit=false";;
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
	 	allReferenceData.put("eventtypeList",  eventtypeManager.getAll());
	 	allReferenceData.put("judgeList",  judgeManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
