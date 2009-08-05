package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Dog;	
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

public class ParticipantsFormController extends BaseFormController {
    private GenericManager<Participants, Long> participantsManager = null;

    public void setParticipantsManager(GenericManager<Participants, Long> participantsManager) {
        this.participantsManager = participantsManager;
    }
    
    // Obtain Related managers 
	// #START-dog#
	// FIXME Verify IDENTIFIER TYPE of Dog
	private GenericManager<Dog, Long> dogManager = null;
	public void setDogManager(GenericManager<Dog, Long> dogManager) {
		this.dogManager = dogManager;
	}
	
	private GenericPropertyEditor<Dog, Long> dogPropEdit = null;
	public void setDogPropEdit(GenericPropertyEditor<Dog, Long> dogPropEdit) {
		this.dogPropEdit = dogPropEdit;
	}
	// #END-dog#
	
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
    

    public ParticipantsFormController() {
        setCommandClass(Participants.class);
        setCommandName("participants");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Dog.class, dogPropEdit);
		binder.registerCustomEditor(Event.class, eventPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return participantsManager.get(new Long(sid));
        }

        return new Participants();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Participants participants = (Participants) command;
        boolean isNew = (participants.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            participantsManager.remove(participants.getSid());
            saveMessage(request, getText("participants.deleted", locale));
        } else {
            participantsManager.save(participants);
            String key = (isNew) ? "participants.added" : "participants.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:participantsform.html?sid=" + participants.getSid();
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
	 	allReferenceData.put("dogList",  dogManager.getAll());
	 	allReferenceData.put("eventList",  eventManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
