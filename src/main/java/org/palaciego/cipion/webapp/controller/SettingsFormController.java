package org.palaciego.cipion.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.model.Country;
import org.palaciego.cipion.model.Eventtype;
import org.palaciego.cipion.model.GenericPropertyEditor;
import org.palaciego.cipion.model.ImageUtil;
import org.palaciego.cipion.model.Settings;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class SettingsFormController extends BaseFormController {
    private GenericManager<Settings, Long> settingsManager = null;

    public void setSettingsManager(GenericManager<Settings, Long> settingsManager) {
        this.settingsManager = settingsManager;
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
	
	// End related managers    
    

    public SettingsFormController() {
        setCommandClass(Settings.class);
        setCommandName("settings");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Country.class, countryPropEdit);
		binder.registerCustomEditor(Eventtype.class, eventtypePropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
    	
    	List<Settings> l=settingsManager.getAll();
    	if(l.size()<=0)
    	{
    		//creo un settings por defecto
    		Settings s=new Settings();
    		List<Country> lc=countryManager.getAll();
    		if(lc.size()>0)
    		{
        		s.setCountry(lc.get(0));
    		}
    		else
    		{
    			s.setCountry(null);
    		}
    		List<Eventtype> let=eventtypeManager.getAll();
    		if(let.size()>0)
    		{
        		s.setEventtype(let.get(0));
    		}
    		else
    		{
    			s.setEventtype(null);
    		}
    		s.setMaxreuses(new Long(3));
    		s.setPointspenaltyabsent(new Long(100));
    		s.setPointspenaltymaxreuses(new Long(100));
    		
    		s.setPointspenaltyfoul(new Long(5));
    		s.setPointspenaltyreuse(new Long(5));
    		
    		s.setPointspenaltysecond(new Long(100));
    		settingsManager.save(s);
    	}
    	
        return settingsManager.getAll().get(0);
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Settings settings = (Settings) command;
        boolean isNew = (settings.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            settingsManager.remove(settings.getSid());
            saveMessage(request, getText("settings.deleted", locale));
        } else {
        	//me aseguro de que la foto tiene dimensiones correctas
        	settings.setReportlogo(ImageUtil.resizeImage(settings.getReportlogo(), 200, 200));
            settingsManager.save(settings);
            String key = (isNew) ? "settings.added" : "settings.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:settingsform.html?sid=" + settings.getSid() + "&edit=false";
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
	 	allReferenceData.put("countryList",  countryManager.getAll("name"));
	 	allReferenceData.put("eventtypeList",  eventtypeManager.getAll("name"));
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
