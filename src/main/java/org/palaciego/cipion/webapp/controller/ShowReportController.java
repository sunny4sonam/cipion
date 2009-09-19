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
		String byCategory=request.getParameter("bycategory");

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

		List<Grade> listGrade=gradeManager.getAll();
		List<Category> listCategory=categoryManager.getAll();
		ArrayList<BasicMap> listResults=new ArrayList<BasicMap>();
		BasicMap lastResults=null;
		for(int i=0;i<listGrade.size();i++)
		{
			Grade g=listGrade.get(i);
			if(Boolean.valueOf(byCategory).booleanValue())
			{
				for(int j=0;j<listCategory.size();j++)
				{
					Category c=listCategory.get(j);
					BasicMap bmResults=getResults(sid,g,c);
					if(bmResults!=null)
					{
						listResults.add(bmResults);
						lastResults=bmResults;
						lastResults.put("last", "false");
					}
				}
			}
			else
			{
				BasicMap bmResults=getResults(sid,g,null);
				if(bmResults!=null)
				{
					listResults.add(bmResults);
					lastResults=bmResults;
					lastResults.put("last", "false");
				}
			}
		}
		if(lastResults!=null)
		{
			lastResults.put("last", "true");
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
	 * Función que devuelve una lista de ganadores de un evento, grado y categoría determinados.
	 * Si categoría es null, entonces de todas las categorías de ese mismo grado.
	 * @param eventSid
	 * @param g
	 * @param c
	 * @return
	 */
	private List<Winner> getWinners(String eventSid, Grade g, Category c,String round)
	{

		List<Roundresults> results=getResults(eventSid, g, c, round);
		ResultsManager rm=new ResultsManager(settingsManager,this.rangeCalificationManager);

		List<Winner> winners=rm.orderResults(results);
		if(winners.size()<=0)
		{
			return null;
		}
		return winners;
	}

	/**
	 * Devuelve la lista de resultados {@link Roundresults} de un evento en un grado, categoría y ronda determinada
	 * @param eventSid
	 * @param g
	 * @param c
	 * @param round
	 * @return
	 */
	private List<Roundresults> getResults(String eventSid, Grade g, Category c, String round)
	{
		//ahora muestro los participantes ordenados por resultado obtenido en la prueba
		String hql="from Roundresults where round.event.sid="+eventSid
          +" and round.number="+round
          +" and participants.dog.grade.sid="+g.getSid();
          
		//si el valor del sid de la categoría es positivo, entonces, de una sóla categoría
		//si es menor que cero es que el usuario quiere ver TODAS las categoráis
		//para dar menos premios! la pasta es la pasta
		if(c!=null)
		{
	          hql=hql +" and participants.dog.category.sid="+c.getSid();
		}

		List<Roundresults> results1=roundresultsManager.findHQL(hql);
		return results1;
	}
	
	/**
	 * Función que devuelve una lista de ganadores de un evento, grado y categoría determinados.
	 * Si categoría es null, entonces de todas las categorías de ese mismo grado.
	 * @param eventSid
	 * @param g
	 * @param c
	 * @return
	 */
	private List<Winner> getFinalWinners(String eventSid, Grade g, Category c)
	{
		List<Roundresults> results1=getResults(eventSid,g,c,"1");
		List<Roundresults> results2=getResults(eventSid,g,c,"2");
		
		ResultsManager rm=new ResultsManager(settingsManager,this.rangeCalificationManager);
		
		List<Winner> results=rm.orderResults(results1,results2);
		return results;
	}

	/**
	 * Devuelve el BasicMap con los resultados de una manga
	 * @param eventSid
	 * @param roundSid
	 * @param g
	 * @param c
	 * @return
	 */
	private BasicMap getResults(String eventSid, Grade g, Category c)
	{
		List<Winner> winnersRound1=getWinners(eventSid, g, c,"1");
		List<Winner> winnersRound2=getWinners(eventSid, g, c,"2");
		List<Winner> winnersFinal=getFinalWinners(eventSid, g, c);
		
		BasicMap bmResultados=new BasicMap("Resultados");
		bmResultados.put("Grade", g.getName());
		if(c!=null)
		{
			bmResultados.put("Category", c.getName());
		}
		else
		{
			bmResultados.put("Category", "");
		}
		
		ArrayList<BasicMap> alresultados=new ArrayList<BasicMap>();
		for(int i=0;i<winnersFinal.size();i++)
		{
			Winner w=winnersFinal.get(i);
			BasicMap bmWinner=new BasicMap("Resultado");
			bmWinner.put("Position", ""+(i+1));
			bmWinner.put("Dorsal", ""+w.participants.getDorsal() );
			bmWinner.put("Dog", w.participants.getDog().getName());
			bmWinner.put("Guide", w.participants.getDog().getGuide().getFirstname() + " " + w.participants.getDog().getGuide().getLastname());
			
			Winner w1=getWinnerFromList(winnersRound1, w.participants);
			bmWinner.put("FaultPoints1", ""+w1.getTotalFault());
			bmWinner.put("Time1", ""+w1.results.getTime());
			bmWinner.put("Calification1", w1.Calification.getName());
			
			Winner w2=getWinnerFromList(winnersRound2, w.participants);
			bmWinner.put("FaultPoints2", ""+w2.getTotalFault());
			bmWinner.put("Time2", ""+w2.results.getTime());
			bmWinner.put("Calification2", w2.Calification.getName());

			bmWinner.put("FaultPoints3", ""+w.getTotalFault());
			bmWinner.put("Time3", ""+(w1.results.getTime()+w2.results.getTime()));

			bmWinner.put("Club", w.participants.getDog().getGuide().getClub().getName());
			bmWinner.put("License", w.participants.getDog().getLicense());
			bmWinner.put("Category", w.participants.getDog().getCategory().getName());

			alresultados.add(bmWinner);
		}
		
		if(alresultados.size()>0)
		{
			bmResultados.put("Resultados",alresultados);
			
			return bmResultados;
		}
		else
		{
			return null;
		}
		
	}

	/**
	 * Devuelve los datos de winner de un determinado participante.
	 * @param list lista de ganadores
	 * @param p participante
	 * @return el ganador
	 */
	private Winner getWinnerFromList(List<Winner> list,Participants p)
	{
		for(int i=0;i<list.size();i++)
		{
			Winner w=list.get(i);
			if(w.participants.getSid().equals(p.getSid()))
			{
				return w;
			}
		}
		return null;
	}

}
