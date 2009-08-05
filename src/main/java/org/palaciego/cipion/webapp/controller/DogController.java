package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Dog;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DogController implements Controller {
    private GenericManager<Dog, Long> dogManager;

    public void setDogManager(GenericManager<Dog, Long> dogManager) {
        this.dogManager = dogManager;
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
        	String hql="from Dog where lower(alias) like '" + filter.toLowerCase() + "%' order by guide.club.name";
            return mv.addObject(dogManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(dogManager.getAll("alias"));
    	}
    }
}
