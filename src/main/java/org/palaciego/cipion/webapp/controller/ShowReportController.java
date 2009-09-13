package org.palaciego.cipion.webapp.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.model.Category;
import org.palaciego.cipion.model.Dog;
import org.palaciego.cipion.model.Event;
import org.palaciego.cipion.model.Grade;
import org.palaciego.cipion.model.Guide;
import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Rangecalification;
import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.model.Settings;
import org.palaciego.cipion.service.GenericManager;
import org.palaciego.cipion.webapp.util.ResultsManager;
import org.palaciego.cipion.webapp.util.Winner;
import org.palaciego.cipion.webapp.view.BasicMap;
import org.palaciego.cipion.webapp.view.XslFopView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ShowReportController extends AbstractController{
    private GenericManager<Settings, Long> settingsManager;
    private GenericManager<Event, Long> eventManager;
    private GenericManager<Roundresults, Long> roundresultsManager;
    private GenericManager<Grade, Long> gradeManager;
	private GenericManager<Category, Long> categoryManager;
	private GenericManager<Rangecalification, Long> rangeCalificationManager;

    public GenericManager<Rangecalification, Long> getRangeCalificationManager() {
		return rangeCalificationManager;
	}

	public void setRangeCalificationManager(GenericManager<Rangecalification , Long> rangeCalificationManager) {
		this.rangeCalificationManager = rangeCalificationManager;
	}
	
	public GenericManager<Category, Long> getCategoryManager() {
		return categoryManager;
	}

	public void setCategoryManager(GenericManager<Category, Long> categoryManager) {
		this.categoryManager = categoryManager;
	}

	public GenericManager<Grade, Long> getGradeManager() {
		return gradeManager;
	}

	public void setGradeManager(GenericManager<Grade, Long> gradeManager) {
		this.gradeManager = gradeManager;
	}

	public GenericManager<Roundresults, Long> getRoundresultsManager() {
		return roundresultsManager;
	}


    public void setRoundresultsManager(GenericManager<Roundresults, Long> roundresultsManager) {
        this.roundresultsManager = roundresultsManager;
    }

    public void setSettingsManager(GenericManager<Settings, Long> settingsManager) {
        this.settingsManager = settingsManager;
    }

    public void setEventManager(GenericManager<Event, Long> eventManager) {
        this.eventManager = eventManager;
    }


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv=new ModelAndView();
		
		String report=request.getParameter("report");
		String sid=request.getParameter("sid");

		String servletPath=request.getServletPath();
		String requestUrl=request.getRequestURL().toString();
		int index=requestUrl.indexOf(servletPath);
		String baseUrl=requestUrl.substring(0,index);
		mv.setView(new XslFopView(baseUrl));
		
		mv.addObject(XslFopView.XSLT,"/xslt/"+report+"-fop.xslt");
		BasicMap bm=new BasicMap("Datos");
		
		Event e=eventManager.get(Long.valueOf(sid));
		Settings s=settingsManager.getAll().get(0);

		bm.put("Evento", e.getName());
		bm.put("Organizador", s.getClub().getName());
		bm.put("Fecha", e.getStartDate() + " - " + e.getEndDate());
		bm.put("Juez", e.getJudge().getFirstname() + " " + e.getJudge().getLastname());

		BasicMap bmParticipantes=new BasicMap("Participantes");
		ArrayList<BasicMap> listParticipantes=new ArrayList<BasicMap>();
		Iterator<Participants> itpart=e.getParticipantses().iterator();
		while(itpart.hasNext())
		{
			Participants p=itpart.next();
			BasicMap participante=new BasicMap("Participante");
			Dog d=p.getDog();
			Guide g=d.getGuide();
			participante.put("Guide",g.getFirstname() + " " + g.getLastname());
			participante.put("Dog",d.getName());
			participante.put("Grade",d.getGrade().getName());
			participante.put("Category",d.getCategory().getName());
			participante.put("Club",g.getClub().getName());
			listParticipantes.add(participante);
		}
		bmParticipantes.put("Participantes", listParticipantes);
		bm.put("Participantes", bmParticipantes);


		List<Grade> listGrade=gradeManager.getAll();
		List<Category> listCategory=categoryManager.getAll();
		ArrayList<BasicMap> listResults=new ArrayList<BasicMap>();
		for(int i=0;i<listGrade.size();i++)
		{
			Grade g=listGrade.get(i);
			for(int j=0;j<listCategory.size();j++)
			{
				Category c=listCategory.get(j);
				BasicMap bmResults=getResults(sid,"1",g,c);
				if(bmResults!=null)
				{
					listResults.add(bmResults);
				}
				bmResults=getResults(sid,"2",g,c);
				if(bmResults!=null)
				{
					listResults.add(bmResults);
				}
				bmResults=getResultsTotal(sid,g,c);
				if(bmResults!=null)
				{
					listResults.add(bmResults);
				}
			}
		}
		bm.put("Resultados", listResults);
		bm.put("sid", ""+s.getSid());
		bm.put("logoImage", baseUrl+"/getimage.html?sid="+ s.getSid() +"&manager=settingsManager&pojo=Settings&field=reportlogo");

		mv.addObject(XslFopView.BASICMAP,bm);
		return mv;
	}

	/**
	 * Devuelve el BasicMap con los resultados de la general.
	 * @param eventSid
	 * @param g
	 * @param c
	 * @return
	 */
	private BasicMap getResultsTotal(String eventSid, Grade g, Category c)
	{
		//ahora muestro los participantes ordenados por resultado obtenido en la prueba
		String hql="from Roundresults where round.event.sid="+eventSid
          +" and round.number=1"
          +" and participants.dog.grade.sid="+g.getSid();
          
    		//si el valor del sid de la categoría es positivo, entonces, de una sóla categoría
    		//si es menor que cero es que el usuario quiere ver TODAS las categoráis
    		//para dar menos premios! la pasta es la pasta
    		if(Double.valueOf(c.getSid()).doubleValue()>=0)
    		{
		          hql=hql +" and participants.dog.category.sid="+c.getSid();
    		}

		List<Roundresults> results1=roundresultsManager.findHQL(hql);
		hql="from Roundresults where round.event.sid="+eventSid
          +" and round.number=2"
          +" and participants.dog.grade.sid="+g.getSid();
		
		//si el valor del sid de la categoría es positivo, entonces, de una sóla categoría
		//si es menor que cero es que el usuario quiere ver TODAS las categoráis
		//para dar menos premios! la pasta es la pasta
		if(Double.valueOf(c.getSid()).doubleValue()>=0)
		{
	          hql=hql +" and participants.dog.category.sid="+c.getSid();
		}

		List<Roundresults> results2=roundresultsManager.findHQL(hql);
		ResultsManager rm=new ResultsManager(settingsManager,this.rangeCalificationManager);
		
		List<Winner> winners=rm.orderResults(results1,results2);		
		if(winners.size()<=0)
		{
			return null;
		}
		
		BasicMap bmResultados=new BasicMap("Resultados");
		bmResultados.put("Round", "General");
		bmResultados.put("Grade", g.getName());
		bmResultados.put("Category", c.getName());
		ArrayList<BasicMap> alresultados=new ArrayList<BasicMap>();
		for(int i=0;i<winners.size();i++)
		{
			Winner w=winners.get(i);
			BasicMap bmWinner=new BasicMap("Resultado");
			bmWinner.put("Position", ""+(i+1));
			bmWinner.put("Dog", w.participants.getDog().getName());
			bmWinner.put("Guide", w.participants.getDog().getGuide().getFirstname() + " " + w.participants.getDog().getGuide().getLastname());
			bmWinner.put("PathFaultPoints", w.pathFaultPoints);
			bmWinner.put("TimeFaultPoints", w.timeFaultPoints);
			bmWinner.put("TotalFaultPoints", w.getTotalFault());
			alresultados.add(bmWinner);
		}
		
		bmResultados.put("Resultados",alresultados);
		
		return bmResultados;
	}

	/**
	 * Devuelve el BasicMap con los resultados de una manga
	 * @param eventSid
	 * @param roundSid
	 * @param g
	 * @param c
	 * @return
	 */
	private BasicMap getResults(String eventSid, String roundSid, Grade g, Category c)
	{
		//ahora muestro los participantes ordenados por resultado obtenido en la prueba
		String hql="from Roundresults where round.event.sid="+eventSid
		          +" and round.number="+roundSid
		          +" and participants.dog.grade.sid="+g.getSid();
		
		//si el valor del sid de la categoría es positivo, entonces, de una sóla categoría
		//si es menor que cero es que el usuario quiere ver TODAS las categoráis
		//para dar menos premios! la pasta es la pasta
		if(Double.valueOf(c.getSid()).doubleValue()>=0)
		{
	          hql=hql +" and participants.dog.category.sid="+c.getSid();
		}
		
		List<Roundresults> results=roundresultsManager.findHQL(hql);
		ResultsManager rm=new ResultsManager(settingsManager,this.rangeCalificationManager);

		List<Winner> winners=rm.orderResults(results);
		if(winners.size()<=0)
		{
			return null;
		}
		
		BasicMap bmResultados=new BasicMap("Resultados");
		bmResultados.put("Round", roundSid);
		bmResultados.put("Grade", g.getName());
		bmResultados.put("Category", c.getName());
		ArrayList<BasicMap> alresultados=new ArrayList<BasicMap>();
		for(int i=0;i<winners.size();i++)
		{
			Winner w=winners.get(i);
			BasicMap bmWinner=new BasicMap("Resultado");
			bmWinner.put("Position", ""+(i+1));
			bmWinner.put("Dog", w.participants.getDog().getName());
			bmWinner.put("Guide", w.participants.getDog().getGuide().getFirstname() + " " + w.participants.getDog().getGuide().getLastname());
			bmWinner.put("PathFaultPoints", w.pathFaultPoints);
			bmWinner.put("TimeFaultPoints", w.timeFaultPoints);
			bmWinner.put("TotalFaultPoints", w.getTotalFault());
			alresultados.add(bmWinner);
		}
		
		bmResultados.put("Resultados",alresultados);
		
		return bmResultados;
		
	}

}
