package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Guide;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class GuideController implements Controller {
    private GenericManager<Guide, Long> guideManager;

    public void setGuideManager(GenericManager<Guide, Long> guideManager) {
        this.guideManager = guideManager;
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
        	String hql="from Guide where lower(firstname) like '" + filter.toLowerCase() + "%' order by club.name";
            return mv.addObject(guideManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(guideManager.getAll("firstname"));
    	}
    }
}
