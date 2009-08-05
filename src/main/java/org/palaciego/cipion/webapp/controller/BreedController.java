package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Breed;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BreedController implements Controller {
    private GenericManager<Breed, Long> breedManager;

    public void setBreedManager(GenericManager<Breed, Long> breedManager) {
        this.breedManager = breedManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
    	
    	String filter=(String)request.getParameter("subnavfilter");
    	//System.out.println("el valor de subnavfilter es:"+filter);
    	if(filter!=null && filter.length()>0)
    	{
    		ModelAndView mv=new ModelAndView();
    		mv.addObject("subnavfilter",filter);
        	String hql="from Breed where lower(name) like '" + filter.toLowerCase() + "%' order by name";
            return mv.addObject(breedManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(breedManager.getAll("name"));
    	}
    }
}
