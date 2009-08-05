package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Eventtype;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EventtypeController implements Controller {
    private GenericManager<Eventtype, Long> eventtypeManager;

    public void setEventtypeManager(GenericManager<Eventtype, Long> eventtypeManager) {
        this.eventtypeManager = eventtypeManager;
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
        	String hql="from Eventtype where lower(name) like '" + filter.toLowerCase() + "%' order by name";
            return mv.addObject(eventtypeManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(eventtypeManager.getAll("name"));
    	}    	
    }
}
