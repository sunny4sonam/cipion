package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Rangecalification;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RangecalificationController implements Controller {
    private GenericManager<Rangecalification, Long> rangecalificationManager;

    public void setRangecalificationManager(GenericManager<Rangecalification, Long> rangecalificationManager) {
        this.rangecalificationManager = rangecalificationManager;
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
        	String hql="from Rangecalification as rc where lower(rc.calification.name) like '" + filter.toLowerCase() + "%' order by rc.round asc, rc.calification.name asc";
            return mv.addObject(rangecalificationManager.findHQL(hql));
    	}
    	else
    	{
    		ModelAndView mv=new ModelAndView();
        	String hql="from Rangecalification as rc order by rc.round asc, rc.calification.name asc";
            return mv.addObject(rangecalificationManager.findHQL(hql));
    	}
    }
}
