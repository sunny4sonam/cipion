package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.model.Participants;	
import org.palaciego.cipion.model.Round;	
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

public class RoundresultsFormController extends BaseFormController {
    private GenericManager<Roundresults, Long> roundresultsManager = null;

    public void setRoundresultsManager(GenericManager<Roundresults, Long> roundresultsManager) {
        this.roundresultsManager = roundresultsManager;
    }
    
    // Obtain Related managers 
	// #START-participants#
	// FIXME Verify IDENTIFIER TYPE of Participants
	private GenericManager<Participants, Long> participantsManager = null;
	public void setParticipantsManager(GenericManager<Participants, Long> participantsManager) {
		this.participantsManager = participantsManager;
	}
	
	private GenericPropertyEditor<Participants, Long> participantsPropEdit = null;
	public void setParticipantsPropEdit(GenericPropertyEditor<Participants, Long> participantsPropEdit) {
		this.participantsPropEdit = participantsPropEdit;
	}
	// #END-participants#
	
	// #START-round#
	// FIXME Verify IDENTIFIER TYPE of Round
	private GenericManager<Round, Long> roundManager = null;
	public void setRoundManager(GenericManager<Round, Long> roundManager) {
		this.roundManager = roundManager;
	}
	
	private GenericPropertyEditor<Round, Long> roundPropEdit = null;
	public void setRoundPropEdit(GenericPropertyEditor<Round, Long> roundPropEdit) {
		this.roundPropEdit = roundPropEdit;
	}
	// #END-round#
	
	// End related managers    
    

    public RoundresultsFormController() {
        setCommandClass(Roundresults.class);
        setCommandName("roundresults");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Participants.class, participantsPropEdit);
		binder.registerCustomEditor(Round.class, roundPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return roundresultsManager.get(new Long(sid));
        }

        return new Roundresults();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Roundresults roundresults = (Roundresults) command;
        boolean isNew = (roundresults.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            roundresultsManager.remove(roundresults.getSid());
            saveMessage(request, getText("roundresults.deleted", locale));
        } else {
            roundresultsManager.save(roundresults);
            String key = (isNew) ? "roundresults.added" : "roundresults.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:roundresultsform.html?sid=" + roundresults.getSid();
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
	 	allReferenceData.put("participantsList",  participantsManager.getAll());
	 	allReferenceData.put("roundList",  roundManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
