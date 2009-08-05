package org.palaciego.cipion.webapp.controller;

import org.apache.commons.lang.StringUtils;
import org.palaciego.cipion.service.GenericManager;


import org.palaciego.cipion.model.Club;
import org.palaciego.cipion.model.Dog;
import org.palaciego.cipion.model.Breed;	
import org.palaciego.cipion.model.Category;	
import org.palaciego.cipion.model.Grade;	
import org.palaciego.cipion.model.Guide;	
import org.palaciego.cipion.model.ImageUtil;
import org.palaciego.cipion.model.Subcategory;	
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

public class DogFormController extends BaseFormController {
    private GenericManager<Dog, Long> dogManager = null;

    public void setDogManager(GenericManager<Dog, Long> dogManager) {
        this.dogManager = dogManager;
    }
    
    // Obtain Related managers 
	// #START-breed#
	// FIXME Verify IDENTIFIER TYPE of Breed
	private GenericManager<Breed, Long> breedManager = null;
	public void setBreedManager(GenericManager<Breed, Long> breedManager) {
		this.breedManager = breedManager;
	}
	
	private GenericPropertyEditor<Breed, Long> breedPropEdit = null;
	public void setBreedPropEdit(GenericPropertyEditor<Breed, Long> breedPropEdit) {
		this.breedPropEdit = breedPropEdit;
	}
	// #END-breed#
	
	// #START-category#
	// FIXME Verify IDENTIFIER TYPE of Category
	private GenericManager<Category, Long> categoryManager = null;
	public void setCategoryManager(GenericManager<Category, Long> categoryManager) {
		this.categoryManager = categoryManager;
	}
	
	private GenericPropertyEditor<Category, Long> categoryPropEdit = null;
	public void setCategoryPropEdit(GenericPropertyEditor<Category, Long> categoryPropEdit) {
		this.categoryPropEdit = categoryPropEdit;
	}
	// #END-category#
	
	// #START-grade#
	// FIXME Verify IDENTIFIER TYPE of Grade
	private GenericManager<Grade, Long> gradeManager = null;
	public void setGradeManager(GenericManager<Grade, Long> gradeManager) {
		this.gradeManager = gradeManager;
	}
	
	private GenericPropertyEditor<Grade, Long> gradePropEdit = null;
	public void setGradePropEdit(GenericPropertyEditor<Grade, Long> gradePropEdit) {
		this.gradePropEdit = gradePropEdit;
	}
	// #END-grade#
	
	// #START-guide#
	// FIXME Verify IDENTIFIER TYPE of Guide
	private GenericManager<Guide, Long> guideManager = null;
	public void setGuideManager(GenericManager<Guide, Long> guideManager) {
		this.guideManager = guideManager;
	}
	
	private GenericPropertyEditor<Guide, Long> guidePropEdit = null;
	public void setGuidePropEdit(GenericPropertyEditor<Guide, Long> guidePropEdit) {
		this.guidePropEdit = guidePropEdit;
	}
	// #END-guide#
	
	// #START-subcategory#
	// FIXME Verify IDENTIFIER TYPE of Subcategory
	private GenericManager<Subcategory, Long> subcategoryManager = null;
	public void setSubcategoryManager(GenericManager<Subcategory, Long> subcategoryManager) {
		this.subcategoryManager = subcategoryManager;
	}
	
	private GenericPropertyEditor<Subcategory, Long> subcategoryPropEdit = null;
	public void setSubcategoryPropEdit(GenericPropertyEditor<Subcategory, Long> subcategoryPropEdit) {
		this.subcategoryPropEdit = subcategoryPropEdit;
	}
	// #END-subcategory#
	
	// End related managers    
    

    public DogFormController() {
        setCommandClass(Dog.class);
        setCommandName("dog");
    }
    
    // Include binders for relatedObjects
    @Override
    protected void initBinder(HttpServletRequest request,
    		ServletRequestDataBinder binder) {
    	super.initBinder(request, binder);
		binder.registerCustomEditor(Breed.class, breedPropEdit);
		binder.registerCustomEditor(Category.class, categoryPropEdit);
		binder.registerCustomEditor(Grade.class, gradePropEdit);
		binder.registerCustomEditor(Guide.class, guidePropEdit);
		binder.registerCustomEditor(Subcategory.class, subcategoryPropEdit);
    }

    protected Object formBackingObject(HttpServletRequest request)
    throws Exception {
        String sid = request.getParameter("sid");
        String guidesid = request.getParameter("guidesid");

        if (!StringUtils.isBlank(sid)) {
            return dogManager.get(new Long(sid));
        }
        Dog d=new Dog();
        if(guidesid!=null)
        {
        	Guide g=guideManager.get(Long.valueOf(guidesid));
        	d.setGuide(g);
        }
        

        return d;
    }

    public ModelAndView onSubmit(HttpServletRequest request,
                                 HttpServletResponse response, Object command,
                                 BindException errors)
    throws Exception {
        log.debug("entering 'onSubmit' method...");

        Dog dog = (Dog) command;
        boolean isNew = (dog.getSid() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            dogManager.remove(dog.getSid());
            saveMessage(request, getText("dog.deleted", locale));
        } else {
        	//me aseguro de que la foto tiene dimensiones correctas
        	dog.setPicture(ImageUtil.resizeImage(dog.getPicture(), 200, 200));
        	
            dogManager.save(dog);
            String key = (isNew) ? "dog.added" : "dog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:dogform.html?sid=" + dog.getSid() + "&edit=false";
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
	 	allReferenceData.put("breedList",  breedManager.getAll());
	 	allReferenceData.put("categoryList",  categoryManager.getAll());
	 	allReferenceData.put("gradeList",  gradeManager.getAll());
	 	allReferenceData.put("guideList",  guideManager.getAll());
	 	allReferenceData.put("subcategoryList",  subcategoryManager.getAll());
		// #END isManyToOne fields
		
		return allReferenceData;
    }
    
}
