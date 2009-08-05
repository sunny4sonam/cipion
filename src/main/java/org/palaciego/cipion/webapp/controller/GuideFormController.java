package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Guide;
import org.palaciego.cipion.model.Club;	
import org.palaciego.cipion.model.GenericPropertyEditor;	
import org.palaciego.cipion.model.ImageUtil;
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

public class GuideFormController extends BaseFormController {
    private GenericManager<Guide, Long> guideManager = null;

    public void setGuideManager(GenericManager<Guide, Long> guideManager) {
        this.guideManager = guideManager;
    }
    
    // Obtain Related managers 
	// #START-club#
	// FIXME Verify IDENTIFIER TYPE of Club
	private GenericManager<Club, Long> clubManager = null;
	public void setClubManager(GenericManager<Club, Long> clubManager) {
		this.clubManager = clubManager;
	}
	
	private GenericPropertyEditor<Club, Long> clubPropEdit = null;
	public void setClubPropEdit(GenericPropertyEditor<Club, Long> clubPropEdit) {
		this.clubPropEdit = clubPropEdit;
	}
	// #END-club#
	
	// End related managers    
    

    public GuideFormController() {
        setCommandClass(Guide.class);
        setCommandName("guide");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Club.class, clubPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");
        String clubsid = request.getParameter("clubsid");

        if (!StringUtils.isBlank(sid)) {
            return guideManager.get(new Long(sid));
        }
        Guide g=new Guide();
        if(clubsid!=null)
        {
        	Club c=clubManager.get(Long.valueOf(clubsid));
        	g.setClub(c);
        }
        return g;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Guide guide = (Guide) command;
        boolean isNew = (guide.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            guideManager.remove(guide.getSid());
            saveMessage(request, getText("guide.deleted", locale));
        } else {
        	
        	//me aseguro de que la foto tiene dimensiones correctas
        	guide.setPicture(ImageUtil.resizeImage(guide.getPicture(), 200, 200));
        	
            guideManager.save(guide);
            String key = (isNew) ? "guide.added" : "guide.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:guideform.html?sid=" + guide.getSid() + "&edit=false";
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
	 	allReferenceData.put("clubList",  clubManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
