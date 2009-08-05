package org.palaciego.cipion.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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

public class RoundresultsController implements Controller, ApplicationContextAware
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

    	
    	String startOrder=(String)request.getParameter("startorder");
    	String savetrm=(String)request.getParameter("savetrm");
    	String trm=(String)request.getParameter("trm");
    	String trs=(String)request.getParameter("trs");
    	String velocity=(String)request.getParameter("velocity");
    	String distance=(String)request.getParameter("distance");

    	if(eventSid!=null && eventSid.length()>0)
    	{
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

    		//Si queremos establecer el orden de saldia
        	if(startOrder!=null && Boolean.valueOf(startOrder).booleanValue())
        	{
        		String hql="from Roundresults where round.event.sid="+eventSid+" and round.number=1 and participants.dog.grade.sid="+gradeSid+" and participants.dog.category.sid="+categorySid;
        		List<Roundresults> list=roundresultsManager.findHQL(hql);
        		long[] order=getOrder(list.size());

        		for(int i=0;i<list.size();i++)
        		{
        			Roundresults rr=list.get(i);
        			rr.setStartorder(order[i]);
        			roundresultsManager.save(rr);
        			//la manga2 el orden inverso
            		hql="from Roundresults where round.event.sid="+eventSid+" and round.number=2 and participants.dog.sid="+rr.getParticipants().getDog().getSid();
            		List<Roundresults> list2=roundresultsManager.findHQL(hql);
            		Roundresults rr2=list2.get(0);
            		rr2.setStartorder(list.size()-order[i]+1);
            		roundresultsManager.save(rr2);
        		}
        		
                Locale locale = request.getLocale();
                saveMessage(mv, getText("roundresults.ordersaved", locale),ERROR_MESSAGES_KEY);
        	}

    		//Si queremos guardar los cambios de trm trs velocidad y distancia
        	if(savetrm!=null && Boolean.valueOf(savetrm).booleanValue())
        	{
        		String hql="from Round where event.sid="+eventSid + " and number="+roundSid+" and grade.sid="+gradeSid+" and category.sid="+categorySid;
        		List<Round> roundList=roundManager.findHQL(hql);
        		if(roundList.size()>0)
        		{
        			trm=trm.replaceAll(",", ".");
        			trs=trs.replaceAll(",", ".");
        			int index=trm.indexOf('.');
        			if(index>0)
        			{
        				trm=trm.substring(0,index);
        			}
        			index=trs.indexOf('.');
        			if(index>0)
        			{
        				trs=trs.substring(0,index);
        			}
        			distance=distance.replaceAll(",", ".");
        			velocity=velocity.replaceAll(",", ".");
        			Round r=roundList.get(0);
        			r.setTrm(Integer.valueOf(trm));
        			r.setTrs(Integer.valueOf(trs));
        			r.setVelocity(Float.valueOf(velocity));
        			r.setDistance(Float.valueOf(distance));
        			roundManager.save(r);
                    Locale locale = request.getLocale();
                    saveMessage(mv, getText("round.prmsaved", locale),ERROR_MESSAGES_KEY);
        		}
        	}
        	else
        	{
        		//sino queremos guardarlos, pongo los valores que tenga la ronda
        		String hql="from Round where event.sid="+eventSid + " and number="+roundSid+" and grade.sid="+gradeSid+" and category.sid="+categorySid;
        		//System.out.println("hql="+hql);
        		List<Round> roundList=roundManager.findHQL(hql);
        		if(roundList.size()>0)
        		{
        			//System.out.println("guardamos trm, etc..");
        			trm=""+roundList.get(0).getTrm();
        			trs=""+roundList.get(0).getTrs();
        			velocity=""+roundList.get(0).getVelocity();
        			distance=""+roundList.get(0).getDistance();
        		}
        	}

    		mv.addObject("eventsid",eventSid);
    		mv.addObject("roundSid",roundSid);
    		mv.addObject("gradeSid",gradeSid);
    		mv.addObject("categorySid",categorySid);
    		//System.out.println("vamos a enviar:"+trm+","+trs+","+velocity+","+distance);
    		mv.addObject("trm",trm);
    		mv.addObject("trs",trs);
    		mv.addObject("velocity",velocity);
    		mv.addObject("distance",distance);
    		

    		//primero meto todos los grados
    		mv.addObject("listadegrados",gradeManager.getAll("name"));
    		
    		//ahora meto todas las categorías
    		mv.addObject("listadecategorias",categoryManager.getAll("name"));
    		
    		
    		//compruebo que están creadas todas las rondas y resultados, y las que no las creo
    		testIfAllRight(Long.valueOf(eventSid).longValue(), Long.valueOf(roundSid).longValue(), Long.valueOf(gradeSid).longValue(), Long.valueOf(categorySid).longValue());
    		
    		//ahora muestro los participantes al torneo
    		//del grado y categoría seleccionado
    		String hql="from Roundresults where round.event.sid="+eventSid+" and round.number="+roundSid+" and participants.dog.grade.sid="+gradeSid+" and participants.dog.category.sid="+categorySid;
    		mv.addObject(roundresultsManager.findHQL(hql));
    		return mv;
    	}
    	else
    	{
            return new ModelAndView().addObject(roundresultsManager.getAll());
    	}
    }

    /**
     * Comprueba que las rondas del torneo y los resultados del participante para esas rondas
     * estén creados correctamente, sino las crea.
     * @param eventSid long
     * @param mangaSid long
     * @param gradeSid long
     * @param categorySid long
     */
    private void testIfAllRight(long eventSid, long mangaSid, long gradeSid, long categorySid)
    {
    	//primero obtengo todos los participantes del torneo para ese grado y categoría
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
    			//primero que nada miro a ver si están las rondas del torneo para este participante
    			hql="from Round where grade.sid=" + gradeSid + " and category.sid=" + categorySid + " and (number=1 or number=2)";
    			List<Round> roundList=roundManager.findHQL(hql);
    			//si no existen las rondas para el torneo, las creo
    			if(roundList.size()<=0)
    			{
    				//creo dos rondas para esta categoría y grado
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
    			//tantos resultados como rondas para esta categoría y grado haya (2 en teoría)
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
    

	/**
	 * Devuelve el orden de un array de dimensión size
	 * @param size
	 * @return int[] dentro están las posiciones.
	 */
	public long[] getOrder(int size)
	{
		long[] numbers=new long[size];
		long aux;
		for(int i=0;i<size;i++)
		{
			numbers[i]=i+1;
		}
		
		//ahora lo desordenamos
		Random r=new Random(System.currentTimeMillis());
		for(int j=0;j<5;j++)
		{
			for(int i=0;i<size;i++)
			{
				int newPos=r.nextInt(size);
				aux=numbers[newPos];
				numbers[newPos]=numbers[i];
				numbers[i]=aux;
			}
		}
		
		return numbers;
	}
}

