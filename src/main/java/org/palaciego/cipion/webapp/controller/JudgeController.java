package org.palaciego.cipion.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.model.Judge;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class JudgeController implements Controller {
    private GenericManager<Judge, Long> judgeManager;

    public void setJudgeManager(GenericManager<Judge, Long> judgeManager) {
        this.judgeManager = judgeManager;
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
        	String hql="from Judge where lower(firstname) like '" + filter.toLowerCase() + "%' order by firstname";
            return mv.addObject(judgeManager.findHQL(hql));
    	}
    	else
    	{
            return new ModelAndView().addObject(judgeManager.getAll("firstname"));
    	}
    }
}
