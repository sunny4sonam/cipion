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

public class ReportOrdersEventController extends AbstractController{
    private GenericManager<Settings, Long> settingsManager;
    private GenericManager<Event, Long> eventManager;
    private GenericManager<Roundresults, Long> roundresultsManager;
    private GenericManager<Grade, Long> gradeManager;
	private GenericManager<Category, Long> categoryManager;
	private GenericManager<Rangecalification, Long> rangeCalificationManager;
	private GenericManager<Participants, Long> participantsManager;

    public GenericManager<Rangecalification, Long> getRangeCalificationManager() {
		return rangeCalificationManager;
	}

	public void setRangeCalificationManager(GenericManager<Rangecalification , Long> rangeCalificationManager) {
		this.rangeCalificationManager = rangeCalificationManager;
	}

    public GenericManager<Participants, Long> getParticipantsManager() {
		return participantsManager;
	}

	public void setParticipantsManager(GenericManager<Participants , Long> ParticipantsManager) {
		this.participantsManager = ParticipantsManager;
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

		Event e=eventManager.get(Long.valueOf(sid));
		Settings s=settingsManager.getAll().get(0);

		mv.addObject(XslFopView.XSLT,"/xslt/"+report+"-fop.xslt");
		BasicMap bm=new BasicMap("Datos");
		bm.put("logoImage", baseUrl+"/getimage.html?sid="+ s.getSid() +"&manager=settingsManager&pojo=Settings&field=reportlogo");
		bm.put("logoImageBasic", "url('getimage.html?sid="+ s.getSid() +"&manager=settingsManager&pojo=Settings&field=reportlogo')");

		List<Grade> listGrade=gradeManager.getAll();
		List<Category> listCategory=categoryManager.getAll();
		ArrayList<BasicMap> listDivisions=new ArrayList<BasicMap>();
		BasicMap lastDivision=null;

		for(int i=0;i<listGrade.size();i++)
		{
			Grade g=listGrade.get(i);
			for(int j=0;j<listCategory.size();j++)
			{
				for(int round=1;round<3;round++)
				{
					Category c=listCategory.get(j);
					String hql="from Roundresults where participants.dog.category.sid=" +c.getSid()
						  +" and round.number="+round
	    		          +" and participants.dog.grade.sid="+g.getSid()
	    		          +" and participants.event.sid="+sid
	    		          +" order by startorder";
		      		List<Roundresults> list=roundresultsManager.findHQL(hql);
		    		ArrayList<BasicMap> listParticipantes=new ArrayList<BasicMap>();
		      		for(int rr=0;rr<list.size();rr++)
		      		{
		      			Roundresults rresults=list.get(rr);
		      			BasicMap bmParticipante=new BasicMap("Participante");
		      			bmParticipante.put("order", ""+rresults.getStartorder());
		      			bmParticipante.put("dorsal", ""+rresults.getParticipants().getDorsal());
		      			bmParticipante.put("dog", ""+rresults.getParticipants().getDog().getName());
		      			bmParticipante.put("guide", ""+rresults.getParticipants().getDog().getGuide().getFirstname()+" " +rresults.getParticipants().getDog().getGuide().getLastname());
		      			bmParticipante.put("club", ""+rresults.getParticipants().getDog().getGuide().getClub().getName());
		      			bmParticipante.put("heat", ""+rresults.getParticipants().isHeat());
		      			listParticipantes.add(bmParticipante);
		      		}
					if(listParticipantes.size()>0)
					{
						BasicMap bmDivision=new BasicMap("division");
						bmDivision.put("grade", g.getName());
						bmDivision.put("category", c.getName());
						bmDivision.put("round", round);
						bmDivision.put("participants", listParticipantes);
						bmDivision.put("last", "false");
						lastDivision=bmDivision;
						listDivisions.add(bmDivision);
					}
				}
			}
		}
		if(lastDivision!=null)
		{
			lastDivision.put("last", "true");
		}
		bm.put("Divisions", listDivisions);

		mv.addObject(XslFopView.BASICMAP,bm);
		return mv;
	}



}
