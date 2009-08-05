package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Club;
import org.palaciego.cipion.model.Country;	
import org.palaciego.cipion.model.GenericPropertyEditor;	
import org.palaciego.cipion.model.ImageUtil;
import org.palaciego.cipion.model.Settings;
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

public class ClubFormController extends BaseFormController {
    private GenericManager<Club, Long> clubManager = null;

    public void setClubManager(GenericManager<Club, Long> clubManager) {
        this.clubManager = clubManager;
    }
    
    // Obtain Related managers 
	// #START-country#
	// FIXME Verify IDENTIFIER TYPE of Country
	private GenericManager<Country, Long> countryManager = null;
	public void setCountryManager(GenericManager<Country, Long> countryManager) {
		this.countryManager = countryManager;
	}
	
	private GenericPropertyEditor<Country, Long> countryPropEdit = null;
	public void setCountryPropEdit(GenericPropertyEditor<Country, Long> countryPropEdit) {
		this.countryPropEdit = countryPropEdit;
	}
	// #END-country#
	
	// End related managers    
    

    public ClubFormController() {
        setCommandClass(Club.class);
        setCommandName("club");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Country.class, countryPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");

        if (!StringUtils.isBlank(sid)) {
            return clubManager.get(new Long(sid));
        }

        //pongo el país por defecto.
        Club c=new Club();
        GenericManager<Settings,Long> gm=(GenericManager<Settings,Long>)getApplicationContext().getBean("settingsManager");
        List settings=gm.getAll();
        if(settings.size()>0)
        {
        	c.setCountry(((Settings)settings.get(0)).getCountry());
        }
        return c;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
    	
        Club club = (Club) command;

    	System.out.println("PROBANDO..." + club.getGuides().size());

        log.debug("entering 'onSubmit' method...");
        

        boolean isNew = (club.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            clubManager.remove(club.getSid());
            saveMessage(request, getText("club.deleted", locale));
        } else {
        	//me aseguro de que la foto tiene dimensiones correctas
        	club.setPicture(ImageUtil.resizeImage(club.getPicture(), 200, 200));

            clubManager.save(club);
            String key = (isNew) ? "club.added" : "club.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:clubform.html?sid=" + club.getSid() + "&edit=false";
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
	 	allReferenceData.put("countryList",  countryManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
