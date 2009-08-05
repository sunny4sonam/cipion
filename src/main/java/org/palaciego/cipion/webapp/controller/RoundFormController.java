package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Round;
import org.palaciego.cipion.model.Event;	
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

public class RoundFormController extends BaseFormController {
    private GenericManager<Round, Long> roundManager = null;

    public void setRoundManager(GenericManager<Round, Long> roundManager) {
        this.roundManager = roundManager;
    }
    
    // Obtain Related managers 
	// #START-event#
	// FIXME Verify IDENTIFIER TYPE of Event
	private GenericManager<Event, Long> eventManager = null;
	public void setEventManager(GenericManager<Event, Long> eventManager) {
		this.eventManager = eventManager;
	}
	
	private GenericPropertyEditor<Event, Long> eventPropEdit = null;
	public void setEventPropEdit(GenericPropertyEditor<Event, Long> eventPropEdit) {
		this.eventPropEdit = eventPropEdit;
	}
	// #END-event#
	
	// End related managers    
    

    public RoundFormController() {
        setCommandClass(Round.class);
        setCommandName("round");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Event.class, eventPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return roundManager.get(new Long(sid));
        }

        return new Round();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Round round = (Round) command;
        boolean isNew = (round.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            roundManager.remove(round.getSid());
            saveMessage(request, getText("round.deleted", locale));
        } else {
            roundManager.save(round);
            String key = (isNew) ? "round.added" : "round.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:roundform.html?sid=" + round.getSid();
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
	 	allReferenceData.put("eventList",  eventManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
