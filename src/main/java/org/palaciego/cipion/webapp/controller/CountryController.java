package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Country;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class CountryController implements Controller {
    private GenericManager<Country, Long> countryManager;

    public void setCountryManager(GenericManager<Country, Long> countryManager) {
        this.countryManager = countryManager;
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
        	String hql="from Country where lower(name) like '" + filter.toLowerCase() + "%' order by name";
            return mv.addObject(countryManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(countryManager.getAll("name"));
    	}
    }
}
