package org.palaciego.cipion.webapp.util;

import java.util.Comparator;

import org.palaciego.cipion.model.Calification;
import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Roundresults;

public class Winner implements Comparator<Winner>
{
	/**
	 * Penalizaciones por tiempo superado del TRM.
	 */
	public double timeFaultPoints;
	/**
	 * Penalizaciones por faltas y rehuses en recorrido.
	 */
	public double pathFaultPoints;

	/**
	 * Resultados de la ronda.
	 */
	public Roundresults results;

	/**
	 * Participante.
	 */
	public Participants participants;

	/**
	 * {@link Calification}
	 */
	public Calification Calification;
	
	/**
	 * Devuelve el total de puntos de falta.
	 * @return double
	 */
	public double getTotalFault()
	{
		return timeFaultPoints+pathFaultPoints;
	}

	/**
	 * Compara dos winners y dice quién es el menor y quién es el mayor.
	 * @return int
	 */
	public int compare(Winner o1, Winner o2) {
		if(o1.getTotalFault()==o2.getTotalFault())
		{
			if(o1.pathFaultPoints<o2.pathFaultPoints)
			{
				return -1;
			}
			else if(o1.pathFaultPoints>o2.pathFaultPoints)
			{
				return 1;
			}
			
			return 0;
		}
		else
		{
			if(o1.getTotalFault()<o2.getTotalFault())
			{
				return -1;
			}
			return 1;
		}
	}
}
