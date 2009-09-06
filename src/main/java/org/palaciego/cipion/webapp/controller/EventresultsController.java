package org.palaciego.cipion.webapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.model.Category;
import org.palaciego.cipion.model.Event;
import org.palaciego.cipion.model.Grade;
import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Round;
import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EventresultsController implements Controller, ApplicationContextAware
{
    private GenericManager<Roundresults, Long> roundresultsManager;
    private GenericManager<Grade, Long> gradeManager;
	private GenericManager<Category, Long> categoryManager;
	private GenericManager<Participants, Long> participantsManager;
	private GenericManager<Round, Long> roundManager;
	private GenericManager<Event, Long> eventManager;
	public static final String SUCCESS_MESSAGES_KEY = "successMessages";
    public static final String ERROR_MESSAGES_KEY = "errors";
    private ApplicationContext ac;
	private MessageSourceAccessor msa;
	
	
    public GenericManager<Event, Long> getEventManager() {
		return eventManager;
	}

	public void setEventManager(GenericManager<Event, Long> eventManager) {
		this.eventManager = eventManager;
	}

	public GenericManager<Round, Long> getRoundManager() {
		return roundManager;
	}

	public void setRoundManager(GenericManager<Round, Long> roundManager) {
		this.roundManager = roundManager;
	}

	public GenericManager<Participants, Long> getParticipantsManager() {
		return participantsManager;
	}

	public void setParticipantsManager(
			GenericManager<Participants, Long> participantsManager) {
		this.participantsManager = participantsManager;
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

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
		ModelAndView mv=new ModelAndView();
    	String eventSid=(String)request.getParameter("sid");
    	String roundSid=(String)request.getParameter("roundSid");
    	String gradeSid=(String)request.getParameter("gradeSid");
    	String categorySid=(String)request.getParameter("categorySid");

  		//creo valores por defecto
		if(roundSid==null || roundSid.length()<=0)
		{
			roundSid="1";
		}
		if(gradeSid==null || gradeSid.length()<=0)
		{
			List<Grade> gl=gradeManager.getAll();
			if(gl.size()>0)
			{
    			gradeSid=""+gl.get(0).getSid();
			}
			else
			{
				gradeSid="0";
			}
		}
		if(categorySid==null || categorySid.length()<=0)
		{
			List<Category> cl=categoryManager.getAll();
			if(cl.size()>0)
			{
    			categorySid=""+cl.get(0).getSid();
			}
			else
			{
				categorySid="0";
			}
		}
		
    	
    		mv.addObject("eventsid",eventSid);
    		mv.addObject("roundSid",roundSid);
    		mv.addObject("gradeSid",gradeSid);
    		mv.addObject("categorySid",categorySid);
    		

    		//primero meto todos los grados
    		mv.addObject("listadegrados",gradeManager.getAll("name"));
    		
    		//ahora meto todas las categor�as
    		mv.addObject("listadecategorias",categoryManager.getAll("name"));
    		

    		//compruebo que est�n creadas todas las rondas y resultados, y las que no las creo
    		if(roundSid.trim().equals("1") || roundSid.trim().equals("2"))
    		{
        		testIfAllRight(Long.valueOf(eventSid).longValue(), Long.valueOf(roundSid).longValue(), Long.valueOf(gradeSid).longValue(), Long.valueOf(categorySid).longValue());
        		//ahora muestro los participantes ordenados por resultado obtenido en la prueba
        		String hql="from Roundresults where round.event.sid="+eventSid
        		          +" and round.number="+roundSid
        		          +" and participants.dog.grade.sid="+gradeSid
        		          +" and participants.dog.category.sid="+categorySid;
        		List<Roundresults> results=roundresultsManager.findHQL(hql);
        		results=orderWinners(results);
        		mv.addObject("resultados",results);
    		}
    		else
    		{
        		testIfAllRight(Long.valueOf(eventSid).longValue(), Long.valueOf(1).longValue(), Long.valueOf(gradeSid).longValue(), Long.valueOf(categorySid).longValue());
        		testIfAllRight(Long.valueOf(eventSid).longValue(), Long.valueOf(2).longValue(), Long.valueOf(gradeSid).longValue(), Long.valueOf(categorySid).longValue());
        		//ahora muestro los participantes ordenados por resultado obtenido en la prueba
        		String hql="from Roundresults where round.event.sid="+eventSid
        		          +" and participants.dog.grade.sid="+gradeSid
        		          +" and participants.dog.category.sid="+categorySid;
        		List<Roundresults> results=roundresultsManager.findHQL(hql);
        		List<Roundresults> resultsgrouped=groupRoundResults(results);
        		results=orderWinners(resultsgrouped);
        		mv.addObject("resultados",results);
    		}
    		return mv;
    }

    /**
     * Función que agrupa los resultados de ambas mangas en uno solo,
     * sumando los resultados de los participantes en las dos mangas,
     * con esta lista se pueden obtener los resultados de la general 
     * de las dos mangas.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
    private List<Roundresults> groupRoundResults(List<Roundresults> participants)
    {
    	List<Roundresults> grouped=new ArrayList<Roundresults>();
    	
    	for(int i=0;i<participants.size();i++)
    	{
    		Roundresults rr=participants.get(i);
    		//primero, sino está en grouped, lo trato
    		if(!isGrouped(grouped, rr))
    		{
    			Roundresults rrgrouped=new Roundresults();
    			Roundresults other=getOtherRoundResults(participants, rr);
    			if(other!=null)
    			{
    				if(rr.isAbsent() || other.isAbsent())
    				{
    					rrgrouped.setAbsent(true);
    				}
    				else
    				{
    					rrgrouped.setAbsent(false);
    				}
    				if(rr.isEliminated() || other.isEliminated())
    				{
    					rrgrouped.setEliminated(true);
    				}
    				else
    				{
    					rrgrouped.setEliminated(false);
    				}
    				//sumo todo
    				rrgrouped.setFouls(rr.getFouls()+other.getFouls());
    				rrgrouped.setParticipants(rr.getParticipants());
    				rrgrouped.setResult(rr.getResult()+other.getResult());
    				rrgrouped.setReuses(rr.getReuses()+other.getReuses());
    				rrgrouped.setRound(null);
    				rrgrouped.setTime(rr.getTime()+other.getTime());
    			}
    			else
    			{
    				rrgrouped=rr;
    			}
    			grouped.add(rrgrouped);
    		}
    	}

    	return grouped;
    }

    /**
     * Devuelve de una lista de participantes, donde se supone que los participantes están 2 veces
     * (una por manga), el complementario a  la ronda que se le pase:si se le pasa la ronda 1, los resultados
     * de la 2, y así.
     * @param participants {@link List}<Roundresults/>
     * @param original {@link Roundresults}
     * @return {@link Roundresults}
     */
    private Roundresults getOtherRoundResults(List<Roundresults> participants, Roundresults original)
    {
    	for(int i=0;i<participants.size();i++)
    	{
    		Roundresults rr=participants.get(i);
    		if(rr.getParticipants().getDog().getSid().equals(original.getParticipants().getDog().getSid()) && !rr.equals(original))
    		{
    			return rr;
    		}
    	}
    	
    	return null;
    }

    /**
     * Función que indica si un participante está ya agrupado en la lista que se le pasa.
     * @param grouped {@link List}<Roundresults/>
     * @param rr {@link Roundresults}
     * @return boolean
     */
    private boolean isGrouped(List<Roundresults> grouped,Roundresults rr)
    {
    	for(int i=0;i<grouped.size();i++)
    	{
    		Roundresults r=grouped.get(i);
    		if(r.getParticipants().getDog().equals(rr.getParticipants().getDog()))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
     * Función que recibe una lsita de participantes con sus resultados, y devuelve otra
     * con los mismos participantes pero ordenada de manera que los primeros son los que mejores resultados han
     * obtenido en la prueba.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
    private List<Roundresults> orderWinners(List<Roundresults> participants)
    {
    		//comparador por faltas
    		Comparator<Roundresults> cmp1=new Comparator<Roundresults>() {

				public int compare(Roundresults o1, Roundresults o2) {
					Long fo1=o1.getFouls();
					Long fo2=o2.getFouls();
					
					//si no se ha presentado
					if(o1.isAbsent() || o2.isAbsent())
					{
						//si los dos no se han presentado, empate
						if(o1.isAbsent() && o2.isAbsent())
						{
							return 0;
						}
						//si no se ha presentado el primero, entonces el primero es mayor
						else if(o1.isAbsent())
						{
							return 1;
						}
						//sino se ha presentado els egundo, entonces el primero es menor
						else
						{
							return -1;
						}
					}

					//si está eliminado
					if(o1.isEliminated() || o2.isEliminated())
					{
						//si los dos están eliminados
						if(o1.isEliminated() && o2.isEliminated())
						{
							return 0;
						}
						//si el primero está eliminado, entonces el primero es mayor
						else if(o1.isEliminated())
						{
							return 1;
						}
						//sino está eliminado el segundo, entonces el primero es menor
						else
						{
							return -1;
						}
					}


					//si tienen las mismas faltas ordeno por tiempo
					if(fo1.longValue()==fo2.longValue())
					{
						//ahora ordeno por tiempo
						float to1=o1.getTime();
						float to2=o2.getTime();
						if(to1==to2)
						{
							return 0;
						}
						else if(to1<to2)
						{
							return -1;
						}
						else
						{
							return 1;
						}
					}
					
					return fo1.compareTo(fo2);
				}
			};

			//ordeno
    		Collections.sort(participants, cmp1);
    			
    		return participants;
    }

    /**
     * Comprueba que las rondas del torneo y los resultados del participante para esas rondas
     * est�n creados correctamente, sino las crea.
     * @param eventSid long
     * @param mangaSid long
     * @param gradeSid long
     * @param categorySid long
     */
    private void testIfAllRight(long eventSid, long mangaSid, long gradeSid, long categorySid)
    {
    	//primero obtengo todos los participantes del torneo para ese grado y categor�a
    	String hql="from Participants where dog.category.sid=" + categorySid + " and dog.grade.sid="+gradeSid;
    	List<Participants> participants=this.participantsManager.findHQL(hql);
    	//compruebo que para cada participante hay unos resultados
    	for(int iParts=0;iParts<participants.size();iParts++)
    	{
    		Participants part=participants.get(iParts);
    		hql="from Roundresults where participants.sid="+part.getSid();
    		List<Roundresults> rrList=this.roundresultsManager.findHQL(hql);
    		//si no hay resultados para el participante
    		if(rrList.size()<=0)
    		{
    			//primero que nada miro a ver si est�n las rondas del torneo para este participante
    			hql="from Round where grade.sid=" + gradeSid + " and category.sid=" + categorySid + " and (number=1 or number=2)";
    			List<Round> roundList=roundManager.findHQL(hql);
    			//si no existen las rondas para el torneo, las creo
    			if(roundList.size()<=0)
    			{
    				//creo dos rondas para esta categor�a y grado
    				for(int roundNumber=1;roundNumber<3;roundNumber++)
    				{
        				Round r=new Round();
        				r.setCategory(categoryManager.get(new Long(categorySid)));
        				r.setDescription("");
        				r.setEvent(eventManager.get(new Long(eventSid)));
        				r.setGrade(gradeManager.get(new Long(gradeSid)));
        				r.setNumber(new Long(roundNumber));
        				r.setTrm(0);
        				r.setTrs(0);
        				roundManager.save(r);
    				}
        			roundList=roundManager.findHQL(hql);
    			}
    			
    			//creo los resultados para este participante
    			//tantos resultados como rondas para esta categor�a y grado haya (2 en teor�a)
    			for(int iRound=0;iRound<roundList.size();iRound++)
    			{
    				Round r=roundList.get(iRound);
        			Roundresults rr=new Roundresults();
        			rr.setFouls(new Long(0));
        			rr.setParticipants(part);
        			rr.setReuses(new Long(0));
        			rr.setRound(r);
        			rr.setStartorder(new Long(0));
        			rr.setTime(0);
        			roundresultsManager.save(rr);
    			}
    		}
    	}
    	
    }
    
    
    @SuppressWarnings("unchecked")
    public void saveMessage(ModelAndView mv, String msg, String key) {
        List messages = new ArrayList();
        messages.add(msg);
        mv.addObject(key, messages);
    }
    

    /**
     * Convenience method for getting a i18n key's value.  Calling
     * getMessageSourceAccessor() is used because the RequestContext variable
     * is not set in unit tests b/c there's no DispatchServlet Request.
     *
     * @param msgKey
     * @param locale the current locale
     * @return
     */
    public String getText(String msgKey, Locale locale) {
        return this.msa.getMessage(msgKey, locale);
    }

    /**
     * Nos inyectan el application context
     */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ac=applicationContext;
		this.msa=new MessageSourceAccessor(this.ac);
	}    
    
}


