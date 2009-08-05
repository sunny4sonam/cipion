package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Calification;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CalificationController implements Controller {
    private GenericManager<Calification, Long> calificationManager;

    public void setCalificationManager(GenericManager<Calification, Long> calificationManager) {
        this.calificationManager = calificationManager;
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
        	String hql="from Calification where lower(name) like '" + filter.toLowerCase() + "%' order by name";
            return mv.addObject(calificationManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(calificationManager.getAll());
    	}
    }
}
