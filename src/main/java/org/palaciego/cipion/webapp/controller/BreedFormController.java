package org.palaciego.cipion.webapp.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.model.Breed;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

public class BreedFormController extends BaseFormController {
    private GenericManager<Breed, Long> breedManager = null;

    public void setBreedManager(GenericManager<Breed, Long> breedManager) {
        this.breedManager = breedManager;
    }
    
    // Obtain Related managers 
	// End related managers    
    

    public BreedFormController() {
        setCommandClass(Breed.class);
        setCommandName("breed");
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
            return breedManager.get(new Long(sid));
        }

        return new Breed();
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Breed breed = (Breed) command;
        boolean isNew = (breed.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            breedManager.remove(breed.getSid());
            saveMessage(request, getText("breed.deleted", locale));
        } else {
            breedManager.save(breed);
            String key = (isNew) ? "breed.added" : "breed.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:breedform.html?sid=" + breed.getSid() + "&edit=false";
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
