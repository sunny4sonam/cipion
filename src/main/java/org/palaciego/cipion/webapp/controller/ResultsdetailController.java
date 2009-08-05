package org.palaciego.cipion.webapp.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ResultsdetailController implements Controller
{
    private GenericManager<Roundresults, Long> roundresultsManager;

	public GenericManager<Roundresults, Long> getRoundresultsManager() {
		return roundresultsManager;
	}

	public void setRoundresultsManager(
			GenericManager<Roundresults, Long> roundresultsManager) {
		this.roundresultsManager = roundresultsManager;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mv=new ModelAndView();
    	String rrSid=(String)request.getParameter("sid");
    	String save=(String)request.getParameter("save");

    	if(save!=null && Boolean.valueOf(save).booleanValue())
    	{
    		//vamos a guardar los datos
        	Roundresults rr=this.roundresultsManager.get(new Long(rrSid));
//        	Enumeration<String> en=request.getParameterNames();
//        	while(en.hasMoreElements())
//        	{
//        		String name=en.nextElement();
//        		System.out.println("Parámetro:" + name + ", value:"+request.getParameter(name));
//        	}
        	
        	String absent=(String)request.getParameter("absent");
        	if(absent==null)
        	{
        		absent="false";
        	}
        	String eliminated=(String)request.getParameter("eliminated");
        	if(eliminated==null)
        	{
        		eliminated="false";
        	}
        	String fouls=(String)request.getParameter("fouls");
        	String result=(String)request.getParameter("result");
        	String reuses=(String)request.getParameter("reuses");
        	String startorder=(String)request.getParameter("startorder");
        	String time=(String)request.getParameter("time");
        	
        	//System.out.println("vamos a guardar:"+absent+","+eliminated+","+fouls+","+result+","+result+","+reuses+","+startorder+","+time);
        	
        	rr.setAbsent(Boolean.valueOf(absent));
        	rr.setEliminated(Boolean.valueOf(eliminated));
        	rr.setFouls(Long.valueOf(fouls));
        	rr.setResult(Float.valueOf(result).floatValue());
        	rr.setReuses(Long.valueOf(reuses));
        	rr.setStartorder(Long.valueOf(startorder));
        	rr.setTime(Float.valueOf(time));
        	roundresultsManager.save(rr);
        	ModelAndView mvfin=new ModelAndView();
        	mvfin.addObject("fin","true");
        	return mvfin;
    	}
    	
    	
    	Roundresults rr=this.roundresultsManager.get(new Long(rrSid));
    	
    	mv.addObject(rr);
    	
		return mv;
	}

}
