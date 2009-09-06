package org.palaciego.cipion.webapp.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.palaciego.cipion.model.Calification;
import org.palaciego.cipion.model.Rangecalification;
import org.palaciego.cipion.model.Role;
import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.model.Settings;
import org.palaciego.cipion.service.GenericManager;

/**
 * Clase encargada de calcular los resultados
 * @author spheras
 *
 */
public class ResultsManager 
{
	GenericManager<Settings, Long> settingsManager;
	GenericManager<Rangecalification, Long> rangeCalificationManager;
	
	Settings settings;
	
	
	/**
	 * Constructor.
	 * @param settingsManager
	 */
	public ResultsManager(GenericManager<Settings, Long> settingsManager,GenericManager<Rangecalification, Long> rangeCalificationManager)
	{
		this.settingsManager=settingsManager;
		this.rangeCalificationManager=rangeCalificationManager;
	}

	/**
	 * Función que se encarga de actualizar los settings
	 */
	private void refreshSettings()
	{
		List<Settings> list=settingsManager.getAll();
		if(list.size()>0)
		{
			settings=list.get(0);
		}
	}

    /**
     * Devuelve una lista ordenada de los participantes. El orden es establecido por
     * los vencedores de la prueba.  Esta función sólo evalúa una manga.
     * obtenido en la prueba.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
	public List<Winner> orderResults(List<Roundresults> participants)
	{
		List<Winner> orderWinners=orderWinners(participants);

		return orderWinners;
	}

    /**
     * Devuelve una lista ordenada de los participantes. El orden es establecido por
     * los vencedores de la prueba.  Esta función evalúa dos mangas.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
    public List<Winner> orderResults(List<Roundresults> participantsRound1,List<Roundresults> participantsRound2)
    {
		List<Winner> orderWinners=orderWinners(participantsRound1,participantsRound2);

		return orderWinners;
    }

    /**
     * Devuelve una lista ordenada de los participantes. El orden es establecido por
     * los vencedores de la prueba.  Esta función sólo evalúa una manga.
     * obtenido en la prueba.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
    private List<Winner> orderWinners(List<Roundresults> participants)
    {
    	
    	refreshSettings();
    	
    	//lo primero le calculo los puntos a cada uno, y voy creando una lista de ganadores.
    	ArrayList<Winner> winnersList=new ArrayList<Winner>();
    	for(int i=0;i<participants.size();i++)
    	{
    		Roundresults rr=participants.get(i);
    		Winner w=new Winner();
    		w.results=rr;
    		w.participants=rr.getParticipants();
    		w.Calification=getCalification(w, true);
    		if(rr.isAbsent() || rr.isEliminated())
    		{
    			w.pathFaultPoints=100;
    		}
    		else
    		{
        		w.pathFaultPoints=(rr.getFouls()*settings.getPointspenaltyfoul()) + (rr.getReuses()*settings.getPointspenaltyreuse());
        		if(rr.getTime()>rr.getRound().getTrs())
        		{
        			w.timeFaultPoints=rr.getTime()-rr.getRound().getTrs();
        		}
        		else
        		{
        			w.timeFaultPoints=0;
        		}
    		}
    		w.Calification=getCalification(w, true);
    		winnersList.add(w);
    	}
    	
    	//ahora ordeno la lista de ganadores
		Collections.sort(winnersList,new Winner());
		
    	return winnersList;
    }

    /**
     * Devuelve la calificación para un determinado ganador.
     * @param w {@link Winner}
     * @param round boolean indica si es para una manga o la general
     * @return {@link Calification}
     */
    private Calification getCalification(Winner w, boolean round)
    {
    	double points=w.getTotalFault();
    	
    	String hql="from Rangecalification "
    	          +"where frompoint<="+points
    	          + " and topoint>="+points
    	          + " and round="+(round?"1":"0");
    	
    	List<Rangecalification> list=rangeCalificationManager.findHQL(hql);
    	if(list.size()>0)
    	{
    		return list.get(0).getCalification();
    	}
    	return null;
    }
    
    /**
     * Devuelve una lista ordenada de los participantes. El orden es establecido por
     * los vencedores de la prueba.  Esta función evalúa dos mangas.
     * @param participants {@link List}<Roundresults/>
     * @return {@link List}<Roundresults/>
     */
    private List<Winner> orderWinners(List<Roundresults> participantsRound1,List<Roundresults> participantsRound2)
    {
    	List<Winner> list1=orderWinners(participantsRound1);
    	List<Winner> list2=orderWinners(participantsRound2);
    	List<Winner> list3=new ArrayList<Winner>();
    	for(int i=0;i<list1.size();i++)
    	{
    		Winner w1=list1.get(i);
    		Winner w2=getWinnerFromList(list2,w1.results);
    		Winner w3=new Winner();
    		w3.pathFaultPoints=w1.pathFaultPoints+w2.pathFaultPoints;
    		w3.timeFaultPoints=w1.timeFaultPoints+w2.timeFaultPoints;
    		w3.Calification=getCalification(w3, false);
    		list3.add(w3);
    	}
    	//ahora ordeno la lista de ganadores
		Collections.sort(list3,new Winner());
		
    	return list3;

    }

    /**
     * Función que busca los datos de puntuación de un participante en una lsita.
     * @param list
     * @param winner
     * @return
     */
    private Winner getWinnerFromList(List<Winner> list, Roundresults rr)
    {
    	for(int i=0;i<list.size();i++)
    	{
    		Winner w=list.get(i);
    		if(w.results.equals(rr))
    		{
    			return w;
    		}
    	}
    	return null;
    }
	
	
}


