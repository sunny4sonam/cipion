package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Grade;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GradeController implements Controller {
    private GenericManager<Grade, Long> gradeManager;

    public void setGradeManager(GenericManager<Grade, Long> gradeManager) {
        this.gradeManager = gradeManager;
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
        	String hql="from Grade where lower(name) like '" + filter.toLowerCase() + "%' order by name";
            return mv.addObject(gradeManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(gradeManager.getAll("name"));
    	}
    }
}
