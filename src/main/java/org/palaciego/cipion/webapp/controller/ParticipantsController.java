package org.palaciego.cipion.webapp.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.palaciego.cipion.model.Category;
import org.palaciego.cipion.model.Club;
import org.palaciego.cipion.model.Dog;
import org.palaciego.cipion.model.Event;
import org.palaciego.cipion.model.Grade;
import org.palaciego.cipion.model.Guide;
import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.service.GenericManager;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ParticipantsController implements Controller, ApplicationContextAware {
    private GenericManager<Participants, Long> participantsManager;
    private GenericManager<Club, Long> clubManager;
    private GenericManager<Guide, Long> guideManager;
    private GenericManager<Dog, Long> dogManager;
    private GenericManager<Event, Long> eventManager;
    private GenericManager<Roundresults, Long> roundresultsManager;
    private GenericManager<Category, Long> categoryManager;
	private GenericManager<Grade, Long> gradeManager;
	public static final String SUCCESS_MESSAGES_KEY = "successMessages";
    public static final String ERROR_MESSAGES_KEY = "errors";
    private ApplicationContext ac;
	private MessageSourceAccessor msa;

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

	public void setRoundresultsManager(
			GenericManager<Roundresults, Long> roundresultsManager) {
		this.roundresultsManager = roundresultsManager;
	}

    public void setParticipantsManager(GenericManager<Participants, Long> participantsManager) {
        this.participantsManager = participantsManager;
    }

    public void setClubManager(GenericManager<Club, Long> clubManager) {
        this.clubManager = clubManager;
    }

    public void setGuideManager(GenericManager<Guide, Long> guideManager) {
        this.guideManager = guideManager;
    }

    public void setDogManager(GenericManager<Dog, Long> dogManager) {
        this.dogManager = dogManager;
    }

    public void setEventManager(GenericManager<Event, Long> eventManager) {
        this.eventManager = eventManager;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
    throws Exception {
		ModelAndView mv=new ModelAndView();
    	String eventSid=(String)request.getParameter("sid");
    	String clubSid=(String)request.getParameter("clubSid");
    	String guideSid=(String)request.getParameter("guideSid");
    	String dogSid=(String)request.getParameter("dogSid");
    	String heat=(String)request.getParameter("heat");
    	String delete=(String)request.getParameter("delete");
    	boolean add=Boolean.valueOf((String)request.getParameter("add")).booleanValue();
    	String dorsal=(String)request.getParameter("dorsal");

    	if(delete!=null && delete.length()>0)
    	{
    		//eliminamos
    		participantsManager.remove(new Long(delete));
            Locale locale = request.getLocale();
            saveMessage(mv, getText("participants.deleted", locale),SUCCESS_MESSAGES_KEY);
    	}
    		

		//Si queremos establecer los dorsales
    	if(dorsal!=null && Boolean.valueOf(dorsal).booleanValue())
    	{
    		Event e=eventManager.get(Long.valueOf(eventSid));
    		Iterator<Participants> itPart=e.getParticipantses().iterator();
    		List<Long> dorsals=shuffleDorsals(e.getParticipantses().size());
    		int i=0;
    		while(itPart.hasNext())
    		{
    			Participants p=itPart.next();
    			p.setDorsal(dorsals.get(i));
    			this.participantsManager.save(p);
    			i++;
    		}
    	}
    	
    	if(add)
    	{
    		String hql="from Participants where dog.sid=" + dogSid;
    		List<Participants> l=participantsManager.findHQL(hql);
    		if(l==null || l.size()==0)
    		{
//        		System.out.println("vamos a a�adir un participante");
        		//a�ado el participante
        		Participants p=new Participants();
        		Dog d=dogManager.get(new Long(dogSid));
        		p.setDog(d);
        		p.setHeat(Boolean.valueOf(heat).booleanValue());
        		Event e=eventManager.get(new Long(eventSid));
        		p.setEvent(e);
        		p.setDorsal(new Long(0));
        		Participants psaved=participantsManager.save(p);
        		clubSid="";
        		guideSid="";
        		dogSid="";
                Locale locale = request.getLocale();
                
//                //a�ado los resultados de las rondas
//                Iterator<Round> itRounds=e.getRounds().iterator();
//                while(itRounds.hasNext())
//                {
//                	Round r=itRounds.next();
//                    Roundresults rr=new Roundresults();
//                    rr.setAbsent(false);
//                    rr.setEliminated(false);
//                    rr.setParticipants(psaved);
//                    rr.setRound(r);
//                    rr.setFouls(new Long(0));
//                    rr.setReuses(new Long(0));
//                    rr.setStartorder(new Long(0));
//                    rr.setCategory(psaved.getDog().getCategory());
//                    rr.setGrade(psaved.getDog().getGrade());
//                    roundresultsManager.save(rr);
//                }
//                
                saveMessage(mv, getText("participants.added", locale),SUCCESS_MESSAGES_KEY);
    		}
    		else
    		{
                Locale locale = request.getLocale();
                saveMessage(mv, getText("participants.exist", locale),ERROR_MESSAGES_KEY);
    		}
    	}
    	
    	if(eventSid!=null && eventSid.length()>0)
    	{
        	String hql="from Participants where event.sid=" + eventSid+ " order by dog.guide.club.name";
    		mv.addObject("eventsid",eventSid);
    		mv.addObject("clubSid",clubSid);
    		mv.addObject("guideSid",guideSid);
    		mv.addObject("dogSid",dogSid);
    		mv.addObject(participantsManager.findHQL(hql));
    		
    		//primero meto todos los clubs
    		//System.out.println("meto toda la lista de clubs");
    		mv.addObject("listadeclubs",clubManager.getAll("name"));

    		//luego meto los gu�as del club seleccionado
    		if(clubSid!=null && clubSid.length()>0)
    		{
    			//System.out.println("meto la lista de gu�as!");
            	hql="from Guide where club.sid=" + clubSid + " order by firstName";
            	mv.addObject("listadeguias",guideManager.findHQL(hql));
                mv.addObject("clubName",clubManager.get(new Long(clubSid)).getName());

            	//ahora meto los perros del gu�a seleccionado
            	if(guideSid!=null && guideSid.length()>0)
            	{
            		Guide g=guideManager.get(new Long(guideSid));
            		//System.out.println("El Sid del Club="+g.getClub().getSid());
            		//System.out.println("El Sid del Club2="+new Long(clubSid));
            		if(g.getClub().getSid()==new Long(clubSid).longValue())
            		{
            			//System.out.println("meto la lista de perros!");
                    	hql="from Dog where guide.sid=" + guideSid + " order by name";
                    	mv.addObject("listadeperros",dogManager.findHQL(hql));
                        mv.addObject("guideName",g.getFormLabelField());
                        
                        if(dogSid!=null && dogSid.length()>0)
                        {
                        	Dog d=dogManager.get(new Long(dogSid));
                    		if(d.getGuide().getSid()==new Long(guideSid).longValue())
                    		{
                                mv.addObject("dogName",d.getName());
                    		}
                        }
            		}
            		
            	}
    		}
    		
            return mv;
    	}
    	else
    	{
            return new ModelAndView().addObject(participantsManager.getAll("dog.guide.club.name"));
    	}
    }

    /**
     * Función que baraja los dorsales.
     * @param size
     * @return
     */
    private List<Long> shuffleDorsals(int size)
    {
    	ArrayList<Long> dorsals=new ArrayList<Long>();
    	for(int i=0;i<size;i++)
    	{
    		dorsals.add(new Long(i+1));
    	}

		Collections.shuffle(dorsals);
    	return dorsals;
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
